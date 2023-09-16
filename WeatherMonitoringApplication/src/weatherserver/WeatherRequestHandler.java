package weatherserver;

import com.google.gson.JsonElement;
import java.io.*;
import java.net.Socket;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherRequestHandler implements Runnable {
    private final Socket clientSocket;

    public WeatherRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            
            String location = in.readLine();
            String weatherData = fetchWeatherData(location);
            
            out.println(weatherData);
            
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String fetchWeatherData(String location) {
        try {
            String apiKey = "11b02517b219f8dca7a92c63cec40bdb"; // Replace with your OpenWeatherMap API key
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=metric" +"&appid=" + apiKey;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response = new StringBuilder();
                try (Scanner scanner = new Scanner(connection.getInputStream())) {
                    while (scanner.hasNextLine()) {
                        response.append(scanner.nextLine());
                    }
                }
                
                 JsonParser parser = new JsonParser();
                 JsonObject json = parser.parse(response.toString()).getAsJsonObject();// Parse the JSON response
               
                
                // Extract relevant weather data from the JSON response
                String temperature = json.getAsJsonObject("main").get("temp").getAsString();
                String humidity    = json.getAsJsonObject("main").get("humidity").getAsString();
                
                String rainfall = "N/A"; // Default value if "rain" is missing or "1h" is missing
                JsonElement rainElement = json.getAsJsonObject("rain");
                if (rainElement != null && rainElement.isJsonObject()) {
                    JsonObject rainObject = rainElement.getAsJsonObject();
                    JsonElement oneHourRain = rainObject.get("1h");
                        if (oneHourRain != null && oneHourRain.isJsonPrimitive()) {
                            rainfall = oneHourRain.getAsString();
                        }
                    }

                String airPressure = json.getAsJsonObject("main").get("pressure").getAsString();

                return "Temperature: " + temperature + "°C\nHumidity: " + humidity + "%\nRainfall (last hour): " + rainfall+"mm\nAir Pressure: " + airPressure + " hPa";
            } else {
                return "Error: Unable to fetch weather data. HTTP error code: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Exception occurred while fetching weather data.";
        }
    }
}

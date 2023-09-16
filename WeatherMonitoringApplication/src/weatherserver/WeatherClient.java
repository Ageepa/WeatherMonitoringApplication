package weatherserver;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class WeatherClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            Scanner loc = new Scanner(System.in);
            System.out.println("Enter a location to get weather data: ");      
            String location = loc.next(); 
            
            out.println(location);
            
            String weatherData  = in.readLine();
            String weatherData1 = in.readLine();
            String weatherData2 = in.readLine();
            String weatherData3 = in.readLine();
            System.out.println("Weather Data for " + location + ":\n" + weatherData + "\n" + weatherData1 + "\n" + weatherData2 + "\n" + weatherData3);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

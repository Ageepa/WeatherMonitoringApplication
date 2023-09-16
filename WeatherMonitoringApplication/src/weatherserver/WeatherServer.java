package weatherserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherServer {
    private static final int PORT = 5000;
    private static final ExecutorService executor = Executors.newFixedThreadPool(5); // Adjust the number of threads as needed

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Listening on port " + PORT);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.execute(new WeatherRequestHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

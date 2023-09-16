# WeatherMonitoringApplication
The Weather Monitoring Application is a Java-based client-server system that allows users to fetch real-time weather data for a specified location. This repository hosts the complete source code for the application, including client-side components (GUI and CLI) and a multi-threaded server.

**Key Features**

User-Friendly Interface: The application offers two user interfaces. The first is a graphical user interface (GUI) built with Swing, providing an intuitive way to input a location and view weather data. The second is a command-line interface (CLI) for users who prefer text-based interactions.

-Concurrent Client Requests: The server component (WeatherServer) is designed to handle multiple client requests concurrently, ensuring that users can access weather data simultaneously without delays or blocking.

-OpenWeatherMap Integration: The application fetches weather data from the OpenWeatherMap API, providing accurate and up-to-date information on temperature, humidity, rainfall, and air pressure.

-Error Handling: Robust error handling is implemented throughout the application to handle exceptions gracefully. Users are informed of any errors that occur during data retrieval or processing.

**Getting Started**

Prerequisites:

Before running the Weather Monitoring Application, ensure that you have the following prerequisites installed on your system:

-Java Development Kit (JDK)

-Gson library for JSON parsing

-An API key from OpenWeatherMap (required for API access)

**Installation**

Clone this repository to your local machine using the following command:

"git clone https://github.com/your-username/weather-monitoring-app.git"

Compile the source code using a Java compiler:

"javac -cp .:lib/gson.jar weatherserver/*.java weatherserver/WeatherServer.java"

**Usage**

Running the Server

To start the server component, run the following command:

"java -cp .:lib/gson.jar weatherserver.WeatherServer"

**Client Interfaces**

-Weather GUI (Graphical User Interface): Launch the Weather GUI by running the weatherserver.Weather class. This GUI allows you to enter a location and fetch weather data interactively.

-WeatherClient CLI (Command-Line Interface): Launch the WeatherClient CLI by running the weatherserver.WeatherClient class. This interface is suitable for users who prefer a text-based interaction.

**License**

This Weather Monitoring Application is open-source software distributed under the MIT License. You are free to use, modify, and distribute this codebase for both personal and commercial purposes. See the LICENSE file for more details.

**Disclaimer**

This application is provided as-is and is not affiliated with OpenWeatherMap. Users are responsible for obtaining their own API key and adhering to OpenWeatherMap's terms of use.

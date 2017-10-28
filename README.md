Simple weather forecast web application
==================
Simple application for displaying info from [http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng](http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng)
It parses xml from ilmateenistus.ee and shows info on webpage in readable form. 

## Run option 1 (Pivotal Server in IDE)
If you run via Spring Tool Suite via Pivotal tc Server (Developer Edition v3.2), url is following:  
[http://localhost:8080/WeatherForecast/](http://localhost:8080/WeatherForecast/)

## Run option 2 (Jetty Runner)
If you want to avoid IDE, in cmd go to root path of the project (where pom.xml is located) and run: 
 **java -jar target/dependency/jetty-runner.jar target/WeatherForecast.war**. 
 In this case, url is following: 
[http://localhost:8080/](http://localhost:8080/)

## Screenshot
![alt text](https://github.com/PoukisaOufesa/WeatherForecast/blob/master/readme/weather_forecast.png)

@PoukisaOufesa

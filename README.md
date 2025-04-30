# Overview
Weather App is a web application that allows users to track current weather conditions in different locations.

# Technologies

**Frontend:** Thymeleaf, HTML/CSS  
**Backend:** Java, Spring MVC  
**Database:** PostrgeSQL   
**ORM:** Hibernate  
**Tests:** JUnit5, H2  
**Building:** Gradle  
**Deploy:** Docker  
**Other:** Lombok, MapStruct 

# How to Run
1. Clone the repository  
   ```git clone https://github.com/Dimas-Ukimas/Weather.git```
2. Obtain OpenWeatherAPI Key at https://openweathermap.org/ and add it in env.example
3. Build war artifact:  
   ```gradlew clean build```
4. Build docker image:  
```docker compose --profile prod --env-file .env.example up --build -d```
5. Go to http://localhost:8080/

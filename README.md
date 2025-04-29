# Weather
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
   ```git clone https://github.com/Dimas-Ukimas/Tennis-Scoreboard.git```
2. Build war artifact:  
   ```mvnw.cmd clean package```
3. Build docker image:  
```docker build -t tennis-image .```
4. Run docker container:  
```docker run -d -p 8080:8080 --name tennis-container tennis-image```
5. Go to http://localhost:8080/

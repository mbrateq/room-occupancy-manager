# room-occupancy-manager
* author - Marcin Bratek marcin.bratek@yahoo.com
* requrements : Java 17, Maven, default 8080 port, not occupied by other services

* The main objective of the app is to locacte people in the rooms divided into two cathegories based on the requested price. 
The app is created in springBoot v3.1.5 and Java 17, as a simple rest service. 
The core algorithm of the application exists in the SimpleOccupancyService.java class. 
The Threshold value which indicates obtaining an economy or premium room is parameterised and defaultly set as 100E. The 'promotion' feature to provide a better offer for the client with higest request above the threshold is optional, and enabled by default.
The app exposes the API endpoint at ```http://localhost:8080/occupancy``` which is consuming a request object and providin the Response object in return. The attributes of the object are defined according to the requirements of the task.

* The tests are build using Spock framework, as it provides easy configuration, and plain readable code, which can help to focus on the business logic of the feature.
There are three test classes within the app. First one to test the controller layer, second one to test the service layer and the last one for validation.

* One test case was removed and improved as I've found it impossible to reach the requested value with those input data.

* To run the app localy one need to build the .jar file locally first using ```mvn install``` and then run the file with ```java -jar room-occupancy-manager-0.0.1-SNAPSHOT.jar```


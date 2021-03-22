
**Customer Farm Spring Boot application**

Installation requirements:

Java JDK 1.8

Git  2.21.0

Spring Boot 2.3.0

IntelliJ IDEA 2018.1.8

Maven 3.6.0

Google Chrome

Installations steps

1. Download or clone entire project into local repository

2. Navigate to folder named customer-farm-app and run: mvn clean install & mvn spring-boot:run using Command Prompt, this will start Spring Boot application

3. The Spring login form will appear after starting the application at: `http://localhost:8080/customerfarm/api/login`. 
Use the following login credentials:
**username: bmaric
password: Pass1234**
or
**username: sasam0320
password:
Pass1234**

4. After login use the following links to access the API resources:

   a) Get a list of all users: `http://localhost:8080/customerfarm/api/users`
   
   b) Get a list of  accounts for the user with id = 1 `http://localhost:8080/customerfarm/api/users/1`
   
   c) Get a list of all accounts: `http://localhost:8080/customerfarm/api/accounts`
   
   d) Get a list of user farms for the account with id = 2: `http://localhost:8080/customerfarm/api/accounts/2/farm`
   
   e) Get a list of all customers: `http://localhost:8080/customerfarm/api/customers`

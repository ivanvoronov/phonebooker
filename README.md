# Phonebooker
Phone booking application test assignment. The proposed application is the Spring Boot Java 17 REST API service which is intended to be used by the QA engineers team to automatize the process of booking and returning the phones used to test the software

## Prerequisites
1. Installed Maven
2. Installed and launched Docker
3. Postman for the requests to the REST API service

### How to run the application. Run the following commands in your terminal one by one:
1. `git clone https://github.com/ivanvoronov/phonebooker.git`
2. `cd phonebooker`
3. `mvn clean package`
4. `docker build -t phonebooking:latest .`
5. `docker run --name phonebooking -d -p 8080:8080 phonebooking`

Now you should be able to query the application server with one of the following requests. Here we are using phone "Samsung Galaxy S8" for reference:

- GET: localhost:8080/phones/Samsung Galaxy S8
- POST (book):  localhost:8080/phones/Samsung Galaxy S8/book
- POST (return): localhost:8080/phones/Samsung Galaxy S8/return

Note: the first request just retreives the information related to the specified phone, the second request makes the phone status to be booked and requires the @RequestBody which is just a string with a name of the QA engineer, the last one request is needed to be able to return a phone and to set the properties of the phone to the initial state.

## The UML structure of the application:
<pre> ___________________________________________________
|                       Phone                       |
|---------------------------------------------------|
| - model: String                                   |
| - available: boolean                              |
| - bookedAt: LocalDateTime                         |
| - bookedBy: String                                |
|---------------------------------------------------|
| + Phone(model: String)                            |
| + Getters and setters                             |
| + toString()                                      |
|___________________________________________________|
              ^
              |
 ______________________________________________________
|           PhoneService                               |  
|------------------------------------------------------|
| - phoneManager: PhoneManager                         |
|------------------------------------------------------|
| + PhoneService(phoneManager: PhoneManager)           |
| + getPhone(model: String): Phone                     |
| + bookPhone(model: String, bookedBy: String): String |
| + returnPhone(model: String): String                 |
|______________________________________________________|
                |                                   ^
                |                                   | 
                v                                   | 
  _______________________________________           |
|           PhoneManager                 |          |
|----------------------------------------|          |
| - phones: List<Phone>                  |          |
|----------------------------------------|          |
| + getInstance(): PhoneManager          |          |
| + getAllPhones(): List<Phone>          |          |
| + getPhone(model: String): Phone       |          |
| + bookPhone(model: String, bookedBy: String): String |
| + returnPhone(model: String): String   |          |
|________________________________________|          |
                                                    |
 _______________________________________________________________________
|            PhoneController                                            |
|-----------------------------------------------------------------------|
| - phoneService: PhoneService                                          |
|-----------------------------------------------------------------------|
| + PhoneController(phoneService: PhoneService)                         |
| + getPhone(model: String): ResponseEntity<Phone>                      |
| + bookPhone(model: String, bookedBy: String): ResponseEntity<String>  |
| + returnPhone(model: String): ResponseEntity<String>                  |
|_______________________________________________________________________|</pre>


## REFLECTION

### What aspect of this exercise did you find most interesting?
The most exciting parts of the exercise for me were deploying the application with Docker, testing it, and enjoying the final result.  

### What did you find most cumbersome?
The most cumbersome part of this assignment is the processing of the same models of phones (Samsung Galaxy S8). It was a bit tricky to find a way how to make this in a elegant way. However, the final solution lacks of the efficiency, but it should be ok as far as we don't have a huge number of phones to process.

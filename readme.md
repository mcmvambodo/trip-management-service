#  Trip Management Service

This service handles **trip search and booking**. It is the central service of a distributed system whose aim is to enable the user to travel anywhere in Cameroon with several means of transport. It communicates with the **RegistryService**, the **CustomerService** to manage customers, the **PaymentService** to manage payment and billing and the **NotificationService** (via Kafka) to send email or sms notifications to the user.


## Some of the technologies used:

- Spring Boot 3.3.5
- Spring Core 6.1.14
- Java 21
- Spring Security (JWT)
- Spring Data JPA
- Reactive Programming
- MySql Database
- Liquibase
- OpenApi/Swagger
- Apache Kafka
- Microservice architecture
- Eureka Server/Client
- Java Stripe Api (For payment)
- JUnit5
- Mockito

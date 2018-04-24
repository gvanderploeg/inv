# Inventory management
REST interface for managing products and its stock.

## Getting started
It's a Spring Boot application, so:

    mvn clean spring-boot:run

## Access the interface using curl

    curl -v -u handelaar1:correctbatteryhorsestaple http://localhost:8080/products/1
    curl -v -u handelaar1:correctbatteryhorsestaple http://localhost:8080/products/1/stock

## Design

* Main setup based on Spring Boot
* Security features based on Spring Security
* Java packaging along functionality lines
* Schema / data definitions using Flyway database versioning
* Scheduled tasks for out of band calculations like promotion decisions
* Unit tests using JUnit, Mockito
* Integration tests with running application using Spring test frameworks

## Security

* Authentication using Basic Auth.
* Passwords are hashed using BCrypt
* For sake of simplicity, Merchant entities are users themselves
* All requests will be scoped to the authenticated users (i.e. 'GET /products' will get only the products for the user)

## Operations

    GET /products
    PUT /products
    DELETE /products/{id}
    GET /products/{id}/stock

## Other

* Scheduled task for setting the promotion eligability of product stock.

## Feature backlog

* Batch job for updating forecast (projected out of stock date)
* Externalize scheduled tasks in some sort of batch application
* Data modelling improvements of product vs. stock, and merchants vs users, etc.
* OAuth, CORS, roles, etc..

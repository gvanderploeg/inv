# Inventory management
REST interface for managing products and its stock.

## Getting started
It's a Spring Boot application, so:

    mvn clean bootRun

## Security

* Authentication using Basic Auth.
* For sake of simplicity, Merchant entities are users themselves
* All requests will be scoped to the authenticated users (i.e. 'GET /products' will get only the products for the user)

## Operations

    GET /products
    PUT /products
    POST /products/{id}
    GET /products/{id}/variations/{id}/stock
    GET /stock/products/{name}

## Feature backlog

* Batch job for updating forecast, promotion eligability

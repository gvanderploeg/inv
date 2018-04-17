# Inventory management
REST interface for managing products and its stock.

## Getting started
It's a Spring Boot application, so:

    mvn clean bootRun

## Operations

    GET /products
    PUT /products
    POST /products/{id}
    GET /products/{id}/variations/{id}/stock
    GET /stock/products/{name}

## Feature backlog

* Batch job for updating forecast, promotion eligability

<h1 align="center">Reading is good Sample Project👋</h1>

## Install

```sh
mvn clean install
```
## Steps to containerize

* pre-requisite: install docker

* step 1 build: docker build -t springio/getir-spring-boot-docker .

* step 2 run: docker run -p 8080:8080 springio/getir-spring-boot-docker

## Postman Collection

* You will find "readingisgood.postman_collection.json" collection in project structure inside resource/dev folder.

## Step to test the project
* create books
* create customer
* create order
* check statistics

## Technology stack

* java 1.8
* Junit5
* Springboot version 2.6.0
* Swagger ui version 3.0.0
* Lombok
* Spring security
* H2 db
* Liquibase
* Docker

## Author

👤 **Rahul Singh**
* Github: (https://github.com/rahul-chainthat-com/)

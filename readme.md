# Spring Boot Meter Readings Project
REST APIs implemented using Spring Boot Maven Project

## Requirements
Building the project requires:

1. Java 1.8+
2. Maven

## How to Run

* Run project using the following command (in project root)
```
mvn spring-boot:run
```

## REST APIs Endpoints

### Retrieve a list of clients
```
Get /clients
Content-Type: application/json

```

### Retrive meter readings for client for one year
```
GET /client-meter-readings
Content-Type: application/json
```

### Retrive meter readings for client for one year and month
```
GET /client-meter-readings?addressStreetName=Zagrebačka&addressStreetNumber=4A&addressCity=Zagreb&year=2020&month=JANUARY
Content-Type: application/json
```

### Retrive sum meter readings for client for one year
```
GET /client-meter-readings/summary?addressStreetName=Zagrebačka&addressStreetNumber=4A&addressCity=Zagreb&year=2020
Content-Type: application/json
```

### Insert meter reading for client for one year and month
```
POST /client-meter-readings
Accept: application/json
Content-Type: application/json

{
"addressStreetName" : "Zagrebačka",
"addressStreetNumber" : "4A",
"addressCity" : "Zagreb",
"year" : 2020,
"month" : FEBRUARY,
"value" : 20.20
}

```

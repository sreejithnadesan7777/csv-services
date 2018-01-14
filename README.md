About csv-service
=========================

This is a Spring Boot application for the below mentioned API's 

* Csv File Upload Api
* Csv file Listing Api

What Csv-Service do?
=========================

1. This Api will help  import deals details from files(CSV) into DB.
2. File format is CSV contains the following fields (Deal Unique Id, From Currency ISO    Code "Ordering Currency", To Currency ISO Code, Deal timestamp, Deal Amount in ordering 	currency).
3. Empty field or null validations added for all fields and added validations for ISO Currency format.
4. The valid rows will be stored to table with name csv_valid_data with reference of file      name.
5. Invalid rows will be saved to table with name csv_invalid_data with reference to file name
5. DB contains another table with name (csv_currency_exchange_count) to maintain accumulative count of deals per Ordering Currency "Columns : Currency ISO Code, CountOfDeals ", so upon completion of importing process the system should increase count of deals per currency.
6. System won’t import the same file twice.
7. Find the sample CSV file from the project directory (SampleData.csv)
4. Logging has been implemented using log4j


Code Contribution Guidelines
----------------------------
```
1. Sonar Statistics & Test Coverage -
       * Sonar Violations should not increase than present. Test Coverage should not drop than present.
2. Test Coverage has been added using Power Mickitto.
3. Code changes should  follow the google check style guidlines.
    
```

Prerequisites
=============

* Java 1.8
* Maven 3.1.1+
* MySql DB
* Added Docker deployment support

Logging 
=============
System will provide log file called csv-service-event.txt for tracing the process.

Building
========

Generating IDE (Intellij)
----------------------------

mvn clean idea:idea

From Command Line
-----------------

    mvn clean package

Running the Application
======================

mvn spring-boot:run

Open a browser and visit [http://localhost:8080/](http://localhost:8080/) for Swagger documentation

Docker
======

Docker Prerequisites
--------------------

https://github.com/spring-guides/gs-spring-boot-docker

Building the Docker Image
-------------------------

```
mvn -DchangeNumber=$(git rev-parse HEAD) -DbuildBranch=origin/master clean package
```

```
docker build -t csv-service .
```


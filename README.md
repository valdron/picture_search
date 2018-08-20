# Picture Search #
Picture Search is an application which stores pictures and some metadata about them making them available 
for searching through them. 
This project was done to learn how to integrate Elasticsearch into a spring boot application aswell as to learn about docker and other cloud technologies.

## Introduction ##
The application uses a microservice architecture. Currently all microservices are implemented using Spring Boot.
All microservices can be found in subfolders of this repository. 

The following microservices are used:
1. [picture_store](picture_store/): This microservice provides a REST-Api to upload pictures, 
stores them and makes them available for download.
2. [picture_info_store](picture_info_store/): This provides a REST-Api to store metadata about pictures. It also provides a method to search through the metadata.
3. [picture-search-gui](picture-search-gui/): This is a WebApp which uses both of the other microservices to provide a GUI to use the application.


## Building #
```bash
./build.sh
```
## Running with docker ## 
This includes an elasticsearch instance
```bash
docker-compose build
docker-compose up
```
## Usage ##
### GUI ###
Entrypoint: http://localhost:8080/index.html

### API ###
picture_info_store: http://localhost:8082/pictures/

picture_store: http://localhost:8081/pictures/

### Example-pictures ###
```bash
./copy_examples.sh
```



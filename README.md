# Picture Search #
## Building #
```bash
mvn install -Dmaven.test.skip=true #Tests currently not working but there are none anyways
```
## Running Locally ##
1. change application.properties to a valid elasticsearch host
2. ```bash
mvn spring-boot:run
```
## Running with docker ## 
This includes an elasticsearch instance
```bash
docker-compose build
docker-compose up
```


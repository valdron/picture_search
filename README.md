# Picture Search #
## Building #
```bash
mvn install
```
## Running Locally ##
```bash
mvn spring-boot:run
```
## Running with docker ##
1. Build Image 
```bash
docker build -t picture_search . 
```
2. Run
```bash
docker run --rm -it picture_search
```


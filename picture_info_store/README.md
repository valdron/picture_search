# picture_info_store #
picture_info_store is a service which stores data about pictures.

## Implementation ##
The service uses elasticsearch to store the data and make it easily searchable. The service is implemented using spring boot and spring-data-elasticsearch.
The service provides a REST-API to store picture data and to query it.
## API methods ##

### Create ###
Request:
* Method: POST
* Path: pictures/
* Headers:
    * Content-Type: application/json
* Body: JSON-Object ->
```json
{
    "pictureId": "GUID",
    "descriptionText": "String",
    "tags": ["String"]
}
```

Response:
* Status: 201 Created
* Body: JSON-Object containing the newly created object including the Elasticsearch id of the document.

Example:
```bash
curl -X POST \
  http://<host>/pictures/ \
  -H 'Content-Type: application/json' \
  --data '{...}'
```

### Get ###
Request:
* Method: Get
* Path: pictures/{Elasticsearch-id}

Response:
* Status: 200 OK / 404 Not Found
* Body: JSON-Object:
```json
{
    "id": "String",
    "pictureId": "GUID",
    "descriptionText": "String",
    "tags": ["String"]
}
```
### Searching ###
Request:
* Method: POST
* Path: pictures/search
* Headers:
    * Content-Type: text/plain
* Body: Search string

Response:
* Status: 200 OK
* Body: List of JSON-Objects sorted by relevance to the search term:
```json
{
    "id": "String",
    "pictureId": "GUID",
    "descriptionText": "String",
    "tags": ["String"]
}
```
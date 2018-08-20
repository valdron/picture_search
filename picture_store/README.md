# picture_store # 
picture_store is a microservice which provides an HTTP-API to store pictures.

## Implementation ##
The pictures uploaded to this service currently are store in the local filesystem. For each upload a new GUID is generated and used as the filename. This GUID is returned from the upload request. It can then be used to retrieve the picture at a later time. 

For more info on specific methods see below.

## API-methods ##
### Upload ###
Request:
* Method: POST
* Path: pictures/
* Headers:
    * Content-Type: image/* (e.g.: image/png, image/jpg, ...)
* Body: The raw picture

Response:
* Status: 201 Created
* Body: JSON-Encoded String containing the GUID which was generated for the picture

Example:
```bash
curl -X POST \
  http://<host>/pictures/ \
  -H 'Content-Type: image/png' \
  --data-binary "@/path/to/png/file"
```

### Download ### 
Request:
* Method: GET
* Path: pictures/{guid}

Response:
* Status: 200 OK / 404 Not Found
* Content-Type: image/*
* Body: The raw image data.

### Check ### 
Request:
* Method: HEAD
* Path: pictures/{guid}

Response:
* Status: 200 OK / 404 Not Found
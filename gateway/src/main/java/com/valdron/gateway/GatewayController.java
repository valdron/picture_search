package com.valdron.gateway;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * GatewayController
 */
@RestController
public class GatewayController {
    private String pictureStoreServiceBaseUrl = "http://picture_store:8080/pictures/";
    private String pictureDataStore = "http://picture_data:8080/pictures/";

    @RequestMapping(path = "formUpload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void multipartFormUpload(/* TODO */) {
        // TODO: Implement Method
    }

    @RequestMapping(path = "pictures", method = RequestMethod.POST, consumes = "image/*")
    public ResponseEntity<UUID> pictureUpload(HttpServletRequest request) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        String[] mediaTypes = request.getContentType().split("/");
        headers.setContentType(new MediaType(mediaTypes[0], mediaTypes[1]));
        headers.setContentLength(request.getContentLengthLong());
        byte[] media = new byte[request.getContentLength()];
        try {
            IOUtils.readFully(request.getInputStream(), media);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        RequestEntity<byte[]> requestEntity = new RequestEntity<>(media, headers, HttpMethod.POST,
                new URI(pictureStoreServiceBaseUrl));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UUID> uuidResponse = restTemplate.exchange(requestEntity, UUID.class);

        return uuidResponse;
    }

    @RequestMapping(path = "picturedata", method = RequestMethod.POST, consumes = "application/json")
    // This method requires that the picture already exists.
    public ResponseEntity<Void> pictureDataUpload(@RequestBody String json, HttpServletRequest request)
            throws URISyntaxException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        String uuid = node.get("pictureId").textValue();

        RestTemplate restTemplate = new RestTemplate();

        try {
            RequestEntity<Void> existsCheck = new RequestEntity<>(HttpMethod.HEAD,
                    new URI(pictureStoreServiceBaseUrl + uuid));
            ResponseEntity<Void> exists = restTemplate.exchange(existsCheck, Void.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                throw e;
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json"));
        headers.setContentLength(request.getContentLengthLong());

        RequestEntity<String> requestEntity = new RequestEntity<>(json, headers, HttpMethod.POST,
                new URI(pictureDataStore));

        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestEntity, Void.class);
        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
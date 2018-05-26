package com.valdron.gateway;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class GatewayApplication {

	private String pictureStoreServiceBaseUrl = "picture_store:8081/pictures/";
	private String pictureDataStore = "picture_data:8080/pictures/";

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@RequestMapping(path = "formUpload", method = RequestMethod.POST, consumes = "multipart/form-data")
	public void multipartFormUpload(/* TODO */) {
		// TODO: Implement Method
	}

	@RequestMapping(path = "pictures", method = RequestMethod.POST, consumes = "image/*")
	public ResponseEntity<UUID> pictureUpload(HttpServletRequest request) throws URISyntaxException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(request.getContentType()));
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
			throws URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType(request.getContentType()));
		headers.setContentLength(request.getContentLengthLong());

		RequestEntity<String> requestEntity = new RequestEntity<>(json, headers, HttpMethod.POST,
				new URI(pictureDataStore));

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> responseEntity = restTemplate.exchange(requestEntity, Void.class);
		return responseEntity;
	}
}

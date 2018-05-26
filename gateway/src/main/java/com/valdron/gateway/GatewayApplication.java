package com.valdron.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@RequestMapping(path = "formUpload", method = RequestMethod.POST, consumes = "multipart/form-data")
	public void multipartFormUpload(/*TODO*/) {
		// TODO: Implement Method
	}

	@RequestMapping(path = "pictures", method = RequestMethod.POST, consumes = "image/*")
	public void pictureUpload(/*TODO*/) {
		// TODO: Implement Method
	}

	@RequestMapping(path = "picturedata", method = RequestMethod.POST, consumes = "application/json")
	// This method requires that the picture already exists.
	public void pictureDataUpload(/*TODO*/) {
		// TODO: Implement Method

	}
}

package com.valdron.studienprojekt;

import java.util.Optional;
import com.valdron.studienprojekt.model.PictureData;
import com.valdron.studienprojekt.service.PictureDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@RestController
@RequestMapping("/pictures")
public class StudienprojektApplication {

	@Autowired
	private PictureDataService pictureDataService;

	@GetMapping("/{pictureId}")
	public ResponseEntity<PictureData> getById(@PathVariable String pictureId) {

		Optional<PictureData> optionalPictureData = pictureDataService.findOne(pictureId);

		return optionalPictureData.map(picData -> {
			return new ResponseEntity<>(picData, HttpStatus.OK);
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Void> uploadPicture(@RequestParam("picture") MultipartFile file,
			@RequestParam("tags") String tags, @RequestParam("description") String description) {

		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	public static void main(String[] args) {
		SpringApplication.run(StudienprojektApplication.class, args);
	}
}

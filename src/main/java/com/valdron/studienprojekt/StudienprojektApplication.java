package com.valdron.studienprojekt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Optional;
import com.valdron.studienprojekt.model.PictureData;
import com.valdron.studienprojekt.service.PictureDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<PictureData> uploadPicture(@RequestParam("picture") MultipartFile file,
			@RequestParam("tags") String tags, @RequestParam("description") String description) {

		PictureData pictureData = new PictureData();
		pictureData.setDescriptionText(description);

		ArrayList<String> tagsSplit = new ArrayList<String>(Arrays.asList(tags.split(",|\\s")));
		tagsSplit.removeIf(tag -> tag.isEmpty());
		pictureData.setTags(tagsSplit);

		pictureData = pictureDataService.save(pictureData);
		return new ResponseEntity<>(pictureData, HttpStatus.CREATED);
	}

	@GetMapping("/query")
	public Page<PictureData> getByQuery(@RequestParam("queryString") String query) {
		return pictureDataService.findByQuery(query, PageRequest.of(0, 10));
	}

	public static void main(String[] args) {
		SpringApplication.run(StudienprojektApplication.class, args);
	}
}

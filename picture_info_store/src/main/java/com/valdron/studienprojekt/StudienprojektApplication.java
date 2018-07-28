package com.valdron.studienprojekt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.valdron.studienprojekt.model.NewPictureData;
import com.valdron.studienprojekt.model.PictureData;
import com.valdron.studienprojekt.service.PictureDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping("/")
	public ResponseEntity<PictureData> uploadPictureData(@Valid @RequestBody NewPictureData newPictureData) {
		PictureData pictureData = pictureDataService.save(new PictureData(newPictureData));
		return new ResponseEntity<>(pictureData, HttpStatus.CREATED);
	}

	@GetMapping("/query")
	public List<PictureData> getByQuery(@RequestParam("queryString") String query) {
		Iterable<PictureData> pictureDatas = pictureDataService.findByQuery(query, PageRequest.of(0, 10));
		List<PictureData> list = new ArrayList<PictureData>();
		for (PictureData data : pictureDatas) {
			list.add(data);
		}
		return list;
	}

	public static void main(String[] args) {
		SpringApplication.run(StudienprojektApplication.class, args);
	}
}

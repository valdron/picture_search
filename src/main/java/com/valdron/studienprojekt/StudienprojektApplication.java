package com.valdron.studienprojekt;

import java.util.Optional;
import java.util.UUID;

import com.valdron.studienprojekt.model.PictureData;
import com.valdron.studienprojekt.service.PictureDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/pictures")
public class StudienprojektApplication {

	@Autowired
	private PictureDataService pictureDataService;

	@GetMapping("/{pictureId}")
	public Optional<PictureData> getById(@PathVariable String pictureId) {
		return pictureDataService.findOne(pictureId);
	}

	@PostMapping("/")
	public PictureData uploadPicture(@RequestBody PictureData pictureData) {
		System.out.println(pictureData);
		return pictureDataService.save(pictureData);
	}

	public static void main(String[] args) {
		SpringApplication.run(StudienprojektApplication.class, args);
	}
}

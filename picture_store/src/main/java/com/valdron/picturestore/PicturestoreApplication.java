package com.valdron.picturestore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("pictures")
public class PicturestoreApplication {

	private static final String PICTURES = "./pictures/";

	public PicturestoreApplication() {
		File dir = new File(PICTURES);
		dir.mkdirs();
	}

	@RequestMapping(path = "/", method = RequestMethod.POST, consumes = "image/*")
	public UUID storeFile(@RequestBody byte[] file, HttpServletResponse response, HttpServletRequest request) throws IOException {
		UUID newUuid = UUID.randomUUID();

		File newFile = new File("./pictures/" + newUuid + request.getContentType().split("/")[1]);
		newFile.createNewFile();

		FileOutputStream fileOutputStream = new FileOutputStream(newFile);

		fileOutputStream.write(file);
		fileOutputStream.close();
		response.setStatus(201);

		return newUuid;
	}

	@RequestMapping(path = "/{pictureId}")
	public void getPicture(@PathVariable UUID pictureId, HttpServletResponse response) throws IOException {
		File dir = new File(PICTURES);

		String[] files = dir.list(new FilenameFilter(){
		
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith(pictureId.toString());
			}
		});

		if(files.length == 0) {
			response.setStatus(404);
			return;
		}

		File picture = new File(PICTURES + files[0]);

		try {
			FileInputStream pictureInputStream = new FileInputStream(picture);

			response.setStatus(200);
			response.addHeader("Content-disposition", "attachment;filename=" + picture.getName());
			response.setContentType("image/" + picture.getName().split(".")[1]);
			IOUtils.copy(pictureInputStream, response.getOutputStream());
			response.flushBuffer();
		} catch (FileNotFoundException e) {
			response.setStatus(404);
		}
		
	}

	public static void main(String[] args) {	
		SpringApplication.run(PicturestoreApplication.class, args);
	}
}

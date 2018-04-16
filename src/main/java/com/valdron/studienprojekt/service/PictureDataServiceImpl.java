package com.valdron.studienprojekt.service;

import java.util.Optional;
import java.util.UUID;

import com.valdron.studienprojekt.model.PictureData;
import com.valdron.studienprojekt.repository.PictureDataRepository;

import org.elasticsearch.index.query.SimpleQueryStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * PictureDataServiceImpl
 */
@Service
public class PictureDataServiceImpl implements PictureDataService {

    @Autowired
    private PictureDataRepository pictureDataRepository;

	@Override
	public PictureData save(PictureData pictureData) {
		return pictureDataRepository.save(pictureData);
	}

	@Override
	public void delete(PictureData pictureData) {
		pictureDataRepository.delete(pictureData);
	}

	@Override
	public Optional<PictureData> findOne(String id) {
		return pictureDataRepository.findById(id);
	}

	@Override
	public Iterable<PictureData> findAll() {
		return pictureDataRepository.findAll();
	}

	@Override
	public Page<PictureData> findByQuery(String query, PageRequest pageRequest) {
        return pictureDataRepository.search(new SimpleQueryStringBuilder(query), pageRequest);
	}

    
}
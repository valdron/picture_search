package com.valdron.studienprojekt.service;

import java.util.Optional;
import java.util.UUID;

import com.valdron.studienprojekt.model.PictureData;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


/**
 * PictureDataService
 */
public interface PictureDataService {
    PictureData save(PictureData pictureData);
    void delete(PictureData pictureData);
    Optional<PictureData> findOne(String id);
    Iterable<PictureData> findAll();
    Page<PictureData> findByQuery(String query, PageRequest pageRequest);
}
package com.valdron.studienprojekt.repository;

import java.util.UUID;

import com.valdron.studienprojekt.model.PictureData;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * PictureDataRepository
 */
public interface PictureDataRepository extends ElasticsearchRepository<PictureData, String> {
    PictureData findByPictureId(UUID pictureIUuid);
}
package com.valdron.studienprojekt.model;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * PictureData
 */
@Document(indexName = "pics", type = "picture")
public class PictureData {
    @Id
    private String id;
    private ArrayList<String> tags;
    private String descriptionText;
    private UUID pictureId;

    public PictureData() {
    }

    /**
    * @return the descriptionText
    */
    public String getDescriptionText() {
        return descriptionText;
    }

    /**
     * @param descriptionText the descriptionText to set
     */
    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the pictureId
     */
    public UUID getPictureId() {
        return pictureId;
    }

    /**
     * @param pictureId the pictureId to set
     */
    public void setPictureId(UUID pictureId) {
        this.pictureId = pictureId;
    }

    /**
     * @return the tags
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "" + id + " " + tags + " " + descriptionText + " " + pictureId;
    }
}
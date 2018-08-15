package com.valdron.studienprojekt.model;

import java.util.ArrayList;
import java.util.UUID;

import javax.validation.constraints.NotNull;

/**
 * NewPictureData
 */
public class NewPictureData {

    private String descriptionText;
    @NotNull
    private ArrayList<String> tags;
    @NotNull
    private UUID pictureId;

    /**
     * @return the descriptionText
     */
    public String getDescriptionText() {
        return descriptionText;
    }

    /**
     * @return the pictureId
     */
    public UUID getPictureId() {
        return pictureId;
    }

    /**
     * @return the tags
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * @param descriptionText the descriptionText to set
     */
    public void setdescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    /**
     * @param pictureId the pictureId to set
     */
    public void setPictureId(UUID pictureId) {
        this.pictureId = pictureId;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

}
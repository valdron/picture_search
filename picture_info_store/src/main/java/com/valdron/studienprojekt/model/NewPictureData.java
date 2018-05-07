package com.valdron.studienprojekt.model;

import java.util.ArrayList;
import java.util.UUID;

import javax.validation.constraints.NotNull;

/**
 * NewPictureData
 */
public class NewPictureData {

    private String descriptionString;
    @NotNull
    private ArrayList<String> tags;
    @NotNull
    private UUID pictureId;

    /**
     * @return the descriptionString
     */
    public String getDescriptionString() {
        return descriptionString;
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
     * @param descriptionString the descriptionString to set
     */
    public void setDescriptionString(String descriptionString) {
        this.descriptionString = descriptionString;
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
package com.byte_sized.byte_sized.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;

public class AssignedImageTypeId implements Serializable {

    private Integer imagesTypeId;

    private Integer imagesId;

    public Integer getImagesTypeId() {
        return imagesTypeId;
    }

    public void setImagesTypeId(Integer imagesTypeId) {
        this.imagesTypeId = imagesTypeId;
    }

    public Integer getImagesId() {
        return imagesId;
    }

    public void setImagesId(Integer imagesId) {
        this.imagesId = imagesId;
    }

}
package com.FOOD.Dto_data_transfer_object;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.List;


@Embeddable
public class RestaurantDto {

    private Long id;
    private String title;
    private String description;

    @Column(length = 1000)
    private List<String> photos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}

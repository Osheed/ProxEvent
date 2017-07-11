package com.train.proxevent.Objects;

/**
 * Created by David on 11.07.2017.
 */

public class Course {
    int id;
    String title,image_url,description;

    public Course() {
    }

    public Course(int id, String title, String image_url, String description) {
        this.id = id;
        this.title = title;
        this.image_url = image_url;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.train.proxevent.Objects;

/**
 * Created by David on 11.07.2017.
 */

public class Course_Chapters {
    int id,course_id,number;
    String title,image_url,video,description;

    public Course_Chapters() {
    }

    public Course_Chapters(int id, int course_id, int number, String title,
                           String image_url, String video, String description) {
        this.id = id;
        this.course_id = course_id;
        this.number = number;
        this.title = title;
        this.image_url = image_url;
        this.video = video;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

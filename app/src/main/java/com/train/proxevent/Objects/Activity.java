package com.train.proxevent.Objects;

import java.util.Date;

/**
 * Created by David on 14.07.2017.
 */

public class Activity {

    int id,addressId,userOwnerId;
    boolean permanent;
    Date dateCreation, dateEnd;
    String topic, title, content, location, picture;

    public Activity() {
    }

    public Activity(int id, int addressId, int userOwnerId, boolean permanent,
                    Date dateCreation, Date dateEnd, String topic, String title,
                    String content, String location, String picture) {
        this.id = id;
        this.addressId = addressId;
        this.userOwnerId = userOwnerId;
        this.permanent = permanent;
        this.dateCreation = dateCreation;
        this.dateEnd = dateEnd;
        this.topic = topic;
        this.title = title;
        this.content = content;
        this.location = location;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(int userOwnerId) {
        this.userOwnerId = userOwnerId;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}

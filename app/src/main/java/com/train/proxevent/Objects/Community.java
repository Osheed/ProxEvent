package com.train.proxevent.Objects;

import java.util.Date;

/**
 * Created by David on 11.07.2017.
 */

public class Community {
    int id,user_id,approval_required,points_enabled;
    Date datetime_added;
    String name,address,city,postal_code,country,description,
    talent,image_url;
    double longitude,latitude;

    public Community() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getApproval_required() {
        return approval_required;
    }

    public void setApproval_required(int approval_required) {
        this.approval_required = approval_required;
    }

    public int getPoints_enabled() {
        return points_enabled;
    }

    public void setPoints_enabled(int points_enabled) {
        this.points_enabled = points_enabled;
    }

    public Date getDatetime_added() {
        return datetime_added;
    }

    public void setDatetime_added(Date datetime_added) {
        this.datetime_added = datetime_added;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTalent() {
        return talent;
    }

    public void setTalent(String talent) {
        this.talent = talent;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Community(int id, int user_id, int approval_required, int points_enabled, Date datetime_added, String name,
                     String address, String city, String postal_code, String country, String description, String talent,
                     String image_url, double longitude, double latitude) {
        this.id = id;
        this.user_id = user_id;
        this.approval_required = approval_required;
        this.points_enabled = points_enabled;
        this.datetime_added = datetime_added;
        this.name = name;
        this.address = address;
        this.city = city;
        this.postal_code = postal_code;
        this.country = country;
        this.description = description;
        this.talent = talent;
        this.image_url = image_url;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}

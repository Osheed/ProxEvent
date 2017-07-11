package com.train.proxevent.Objects;

import java.util.Date;

/**
 * Created by David on 11.07.2017.
 */

public class Post {
    int id,community_id,user_id,postpoints,points_req,participants_min,
            participants_max,return_favor,return_favor_points_amount,
            language;
    String username,title,talent,comment,datetime,active_from,image_url,
            image_type,image_size,image_ctgy,location,post_title,
    address,city,country,postal_code,tags,image,video;
    Date expiration;
    double latitude,longitude;

    public Post() {
    }

    public Post(int id, int community_id, int user_id, int postpoints, int points_req,
                int participants_min, int participants_max, int return_favor,
                int return_favor_points_amount, int language, String username, String title,
                String talent, String comment, String datetime, String active_from, String image_url,
                String image_type, String image_size, String image_ctgy, String location, String post_title,
                String address, String city, String country, String postal_code, String tags, String image, String video,
                Date expiration, double latitude, double longitude) {
        this.id = id;
        this.community_id = community_id;
        this.user_id = user_id;
        this.postpoints = postpoints;
        this.points_req = points_req;
        this.participants_min = participants_min;
        this.participants_max = participants_max;
        this.return_favor = return_favor;
        this.return_favor_points_amount = return_favor_points_amount;
        this.language = language;
        this.username = username;
        this.title = title;
        this.talent = talent;
        this.comment = comment;
        this.datetime = datetime;
        this.active_from = active_from;
        this.image_url = image_url;
        this.image_type = image_type;
        this.image_size = image_size;
        this.image_ctgy = image_ctgy;
        this.location = location;
        this.post_title = post_title;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postal_code = postal_code;
        this.tags = tags;
        this.image = image;
        this.video = video;
        this.expiration = expiration;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(int community_id) {
        this.community_id = community_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPostpoints() {
        return postpoints;
    }

    public void setPostpoints(int postpoints) {
        this.postpoints = postpoints;
    }

    public int getPoints_req() {
        return points_req;
    }

    public void setPoints_req(int points_req) {
        this.points_req = points_req;
    }

    public int getParticipants_min() {
        return participants_min;
    }

    public void setParticipants_min(int participants_min) {
        this.participants_min = participants_min;
    }

    public int getParticipants_max() {
        return participants_max;
    }

    public void setParticipants_max(int participants_max) {
        this.participants_max = participants_max;
    }

    public int getReturn_favor() {
        return return_favor;
    }

    public void setReturn_favor(int return_favor) {
        this.return_favor = return_favor;
    }

    public int getReturn_favor_points_amount() {
        return return_favor_points_amount;
    }

    public void setReturn_favor_points_amount(int return_favor_points_amount) {
        this.return_favor_points_amount = return_favor_points_amount;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTalent() {
        return talent;
    }

    public void setTalent(String talent) {
        this.talent = talent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getActive_from() {
        return active_from;
    }

    public void setActive_from(String active_from) {
        this.active_from = active_from;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public String getImage_size() {
        return image_size;
    }

    public void setImage_size(String image_size) {
        this.image_size = image_size;
    }

    public String getImage_ctgy() {
        return image_ctgy;
    }

    public void setImage_ctgy(String image_ctgy) {
        this.image_ctgy = image_ctgy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

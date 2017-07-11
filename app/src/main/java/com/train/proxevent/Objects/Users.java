package com.train.proxevent.Objects;

/**
 * Created by David on 11.07.2017.
 */

public class Users {
    int id,points,last_activation_request,lost_passpord_request,
            lost_password_timestamp,active,sign_up_stamp,last_sign_in_stamp,
            enabled,primary_group_id,points_enabled;
    String user_name,display_name,profile_pic,password,
            email,location,talent,lookingfor,about_me,activation_token,
            title;

    public Users() {
    }

    public Users(int id, int points, int last_activation_request, int lost_passpord_request, int lost_password_timestamp, int active, int sign_up_stamp, int last_sign_in_stamp, int enabled, int primary_group_id, int points_enabled, String user_name, String display_name, String profile_pic, String password, String email, String location, String talent, String lookingfor, String about_me, String activation_token, String title) {
        this.id = id;
        this.points = points;
        this.last_activation_request = last_activation_request;
        this.lost_passpord_request = lost_passpord_request;
        this.lost_password_timestamp = lost_password_timestamp;
        this.active = active;
        this.sign_up_stamp = sign_up_stamp;
        this.last_sign_in_stamp = last_sign_in_stamp;
        this.enabled = enabled;
        this.primary_group_id = primary_group_id;
        this.points_enabled = points_enabled;
        this.user_name = user_name;
        this.display_name = display_name;
        this.profile_pic = profile_pic;
        this.password = password;
        this.email = email;
        this.location = location;
        this.talent = talent;
        this.lookingfor = lookingfor;
        this.about_me = about_me;
        this.activation_token = activation_token;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLast_activation_request() {
        return last_activation_request;
    }

    public void setLast_activation_request(int last_activation_request) {
        this.last_activation_request = last_activation_request;
    }

    public int getLost_passpord_request() {
        return lost_passpord_request;
    }

    public void setLost_passpord_request(int lost_passpord_request) {
        this.lost_passpord_request = lost_passpord_request;
    }

    public int getLost_password_timestamp() {
        return lost_password_timestamp;
    }

    public void setLost_password_timestamp(int lost_password_timestamp) {
        this.lost_password_timestamp = lost_password_timestamp;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getSign_up_stamp() {
        return sign_up_stamp;
    }

    public void setSign_up_stamp(int sign_up_stamp) {
        this.sign_up_stamp = sign_up_stamp;
    }

    public int getLast_sign_in_stamp() {
        return last_sign_in_stamp;
    }

    public void setLast_sign_in_stamp(int last_sign_in_stamp) {
        this.last_sign_in_stamp = last_sign_in_stamp;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getPrimary_group_id() {
        return primary_group_id;
    }

    public void setPrimary_group_id(int primary_group_id) {
        this.primary_group_id = primary_group_id;
    }

    public int getPoints_enabled() {
        return points_enabled;
    }

    public void setPoints_enabled(int points_enabled) {
        this.points_enabled = points_enabled;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTalent() {
        return talent;
    }

    public void setTalent(String talent) {
        this.talent = talent;
    }

    public String getLookingfor() {
        return lookingfor;
    }

    public void setLookingfor(String lookingfor) {
        this.lookingfor = lookingfor;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public String getActivation_token() {
        return activation_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

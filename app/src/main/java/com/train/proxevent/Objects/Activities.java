package com.train.proxevent.Objects;

import java.util.Date;

/**
 * Created by David on 14.07.2017.
 */

public class Activities {

    public String Act_adresse;
    public String Act_content;
    public String Act_date_crea;
    public String Act_date_end;
    public String Act_latitude;
    public String Act_longitude;
    public String Act_owner;
    public String Act_title;
    public String Act_topic;
    public String Act_image;

    public Activities(){}

    public Activities(String act_adresse, String act_content, String act_date_crea, String act_date_end, String act_latitude, String act_longitude, String act_owner, String act_title, String act_topic, String act_image) {
        Act_adresse = act_adresse;
        Act_content = act_content;
        Act_date_crea = act_date_crea;
        Act_date_end = act_date_end;
        Act_latitude = act_latitude;
        Act_longitude = act_longitude;
        Act_owner = act_owner;
        Act_title = act_title;
        Act_topic = act_topic;
        Act_image = act_image;
    }

    public String getAct_adresse() {
        return Act_adresse;
    }

    public void setAct_adresse(String act_adresse) {
        Act_adresse = act_adresse;
    }

    public String getAct_content() {
        return Act_content;
    }

    public void setAct_content(String act_content) {
        Act_content = act_content;
    }

    public String getAct_date_crea() {
        return Act_date_crea;
    }

    public void setAct_date_crea(String act_date_crea) {
        Act_date_crea = act_date_crea;
    }

    public String getAct_date_end() {
        return Act_date_end;
    }

    public void setAct_date_end(String act_date_end) {
        Act_date_end = act_date_end;
    }

    public String getAct_latitude() {
        return Act_latitude;
    }

    public void setAct_latitude(String act_latitude) {
        Act_latitude = act_latitude;
    }

    public String getAct_longitude() {
        return Act_longitude;
    }

    public void setAct_longitude(String act_longitude) {
        Act_longitude = act_longitude;
    }

    public String getAct_owner() {
        return Act_owner;
    }

    public void setAct_owner(String act_owner) {
        Act_owner = act_owner;
    }

    public String getAct_title() {
        return Act_title;
    }

    public void setAct_title(String act_title) {
        Act_title = act_title;
    }

    public String getAct_topic() {
        return Act_topic;
    }

    public void setAct_topic(String act_topic) {
        Act_topic = act_topic;
    }

    public String getAct_image() {
        return Act_image;
    }

    public void setAct_image(String act_image) {
        Act_image = act_image;
    }
}


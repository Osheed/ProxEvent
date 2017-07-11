package com.train.proxevent.Objects;

import java.util.Date;

/**
 * Created by David on 11.07.2017.
 */

public class Post_Participant {
    int id,user_id,post_id;
    Date join_date,paid_date;

    public Post_Participant() {
    }

    public Post_Participant(int id, int user_id, int post_id, Date join_date, Date paid_date) {
        this.id = id;
        this.user_id = user_id;
        this.post_id = post_id;
        this.join_date = join_date;
        this.paid_date = paid_date;
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

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }

    public Date getPaid_date() {
        return paid_date;
    }

    public void setPaid_date(Date paid_date) {
        this.paid_date = paid_date;
    }
}

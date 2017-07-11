package com.train.proxevent.Objects;

import java.sql.Timestamp;

/**
 * Created by David on 11.07.2017.
 */

public class Community_Participants {
    int id,user_id,community_id,status;
    Timestamp join_date;

    public Community_Participants() {
    }

    public Community_Participants(int id, int user_id, int community_id,
                                  int status, Timestamp join_date) {
        this.id = id;
        this.user_id = user_id;
        this.community_id = community_id;
        this.status = status;
        this.join_date = join_date;
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

    public int getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(int community_id) {
        this.community_id = community_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Timestamp join_date) {
        this.join_date = join_date;
    }
}

package com.train.proxevent.Objects;

/**
 * Created by David on 11.07.2017.
 */

public class User_Action_Permits {
    int id,user_id;
    String action,permits;

    public User_Action_Permits() {
    }

    public User_Action_Permits(int id, int user_id, String action, String permits) {
        this.id = id;
        this.user_id = user_id;
        this.action = action;
        this.permits = permits;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPermits() {
        return permits;
    }

    public void setPermits(String permits) {
        this.permits = permits;
    }
}

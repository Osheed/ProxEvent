package com.train.proxevent.Objects;

/**
 * Created by David on 11.07.2017.
 */

public class Group_Action_Permits {
    int id,group_id;
    String action,permits;

    public Group_Action_Permits() {
    }

    public Group_Action_Permits(int id, int group_id, String action, String permits) {
        this.id = id;
        this.group_id = group_id;
        this.action = action;
        this.permits = permits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
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

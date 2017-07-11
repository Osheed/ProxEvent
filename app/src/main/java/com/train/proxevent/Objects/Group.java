package com.train.proxevent.Objects;

/**
 * Created by David on 11.07.2017.
 */

public class Group {
    int id,is_default,can_delete,home_page_id;
    String name;

    public Group() {
    }

    public Group(int id, int is_default, int can_delete, int home_page_id, String name) {
        this.id = id;
        this.is_default = is_default;
        this.can_delete = can_delete;
        this.home_page_id = home_page_id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }

    public int getCan_delete() {
        return can_delete;
    }

    public void setCan_delete(int can_delete) {
        this.can_delete = can_delete;
    }

    public int getHome_page_id() {
        return home_page_id;
    }

    public void setHome_page_id(int home_page_id) {
        this.home_page_id = home_page_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

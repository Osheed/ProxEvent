package com.train.proxevent.Objects;

/**
 * Created by David on 11.07.2017.
 */

public class Course_Participants {
    int id,user_id,course_id;

    public Course_Participants() {
    }

    public Course_Participants(int id, int user_id, int course_id) {
        this.id = id;
        this.user_id = user_id;
        this.course_id = course_id;
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

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}

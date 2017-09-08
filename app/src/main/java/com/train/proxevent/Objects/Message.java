package com.train.proxevent.Objects;

import java.util.Date;


public class Message {
    int id, idSender, idActivity;
    String content, title;
    Date date;
    boolean open;

    public Message() {
    }

    public Message(int id, int idSender, int idActivity, String content,
                   String title, Date date, boolean open) {
        this.id = id;
        this.idSender = idSender;
        this.idActivity = idActivity;
        this.content = content;
        this.title = title;
        this.date = date;
        this.open = open;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}

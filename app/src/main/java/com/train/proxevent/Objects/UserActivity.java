package com.train.proxevent.Objects;


public class UserActivity {
    int idUser, idActivity;

    public UserActivity() {
    }

    public UserActivity(int idUser, int idActivity) {
        this.idUser = idUser;
        this.idActivity = idActivity;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }
}

package com.train.proxevent.Objects;

/**
 * Created by David on 14.07.2017.
 */

public class UserActivity {
    int idUser,idActivity;

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

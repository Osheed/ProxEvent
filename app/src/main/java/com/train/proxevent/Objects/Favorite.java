package com.train.proxevent.Objects;

/**
 * Created by David on 14.07.2017.
 */

public class Favorite {
    int id,idUser,idActivity;

    public Favorite() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Favorite(int id, int idUser, int idActivity) {
        this.id = id;
        this.idUser = idUser;
        this.idActivity = idActivity;

    }
}

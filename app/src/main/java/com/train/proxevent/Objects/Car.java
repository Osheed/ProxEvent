package com.train.proxevent.Objects;

/**
 * Created by David on 11.07.2017.
 */

public class Car {
    String mark,owner;
    int doors,wheels;

    public Car() {
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public Car(String mark, String owner, int doors, int wheels) {

        this.mark = mark;
        this.owner = owner;
        this.doors = doors;
        this.wheels = wheels;
    }
}

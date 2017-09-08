package com.train.proxevent.Objects;


public class Address {
    int id;
    String city, npa, country;

    public Address() {
    }

    public Address(int id, String city, String npa, String country) {
        this.id = id;
        this.city = city;
        this.npa = npa;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNpa() {
        return npa;
    }

    public void setNpa(String npa) {
        this.npa = npa;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

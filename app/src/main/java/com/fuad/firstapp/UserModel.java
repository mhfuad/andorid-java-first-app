package com.fuad.firstapp;

public class UserModel {
    Integer id;
    String name;

    public UserModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

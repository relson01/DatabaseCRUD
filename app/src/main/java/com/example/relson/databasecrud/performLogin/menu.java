package com.example.relson.databasecrud.performLogin;

import com.google.gson.annotations.SerializedName;

public class menu {

    @SerializedName("ID")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private String price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public menu(String id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

}

package com.example.androidprototype.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserAllergenList {

    @SerializedName("$id")
    @Expose
    private String id;

    @SerializedName("$values")
    @Expose
    private ArrayList<UserAllergen> userallergenlist;

    public UserAllergenList() {
        super();
    }

    public UserAllergenList(String id, ArrayList<UserAllergen> userallergenlist) {
        this.id = id;
        this.userallergenlist = userallergenlist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<UserAllergen> getUserallergenlist() {
        return userallergenlist;
    }

    public void setUserallergenlist(ArrayList<UserAllergen> userallergenlist) {
        this.userallergenlist = userallergenlist;
    }
}

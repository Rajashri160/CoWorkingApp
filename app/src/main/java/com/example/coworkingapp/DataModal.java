package com.example.coworkingapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataModal {
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    String name;
    String email;

    ArrayList<DataModal>  lstSlots = new ArrayList<>();

    public DataModal(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<DataModal> getLstSlots() {
        return lstSlots;
    }

    public void setLstSlots(ArrayList<DataModal> lstSlots) {
        this.lstSlots = lstSlots;
    }
}

package com.example.ewallet;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "changes")
public class Changes {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String longitude, latitude;
    private double amount;
    private String date;
    private int usersId;

    public Changes(int id, String name, String longitude, String latitude, double amount, String date, int usersId) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.amount = amount;
        this.date=date;
        this.usersId = usersId;
    }

    @Ignore
    public Changes(String name, String longitude, String latitude, double amount, String date, int usersId) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.amount = amount;
        this.date =date;
        this.usersId = usersId;
    }

    public int getId() {
        return id;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsersId(int id) {
        this.usersId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
/*
    @Override
    public String toString() {
        return this.getName()+" "+this.getLongitude()+" "+this.getLatitude()+" "+this.getAmount()+" "+this.getDate();
    } */
    @Override
    public String toString() {
        return this.name+" "+this.longitude+" "+this.amount+" "+this.date;
    }
}

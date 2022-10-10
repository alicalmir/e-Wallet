package com.example.ewallet;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class Users {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String fullname, email, password;
    private String dob;
    private double money=0;

    public Users(int id, String fullname, String email, String password, String dob) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    @Ignore
    public Users(String fullname, String email, String password, String dob) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

}

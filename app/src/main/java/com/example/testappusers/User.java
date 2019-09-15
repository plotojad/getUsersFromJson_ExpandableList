package com.example.testappusers;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String guid;
    private boolean isActive;
    private String balance;
    private int age;
    private String eyeColor;
    private String name;
    private String gender;
    private String company;
    private String email;
    private String phone;
    private String address;
    private String about;
    private String registered;
    private double latitude;
    private double longitude;
    private String[] tags;
    private List<Map<String, Integer>> friends;
    private String favoriteFruit;


    public User(long id, String guid, boolean isActive, String balance, int age, String eyeColor,
                String name, String gender, String company, String email, String phone,
                String address, String about, String registered, double latitude, double longitude,
                String[] tags, List<Map<String, Integer>> friends, String favoriteFruit) {
        this.id = id;
        this.guid = guid;
        this.isActive = isActive;
        this.balance = balance;
        this.age = age;
        this.eyeColor = eyeColor;
        this.name = name;
        this.gender = gender;
        this.company = company;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.about = about;
        this.registered = registered;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tags = tags;
        this.friends = friends;
        this.favoriteFruit = favoriteFruit;
    }

    public long getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getBalance() {
        return balance;
    }

    public int getAge() {
        return age;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getAbout() {
        return about;
    }

    public String getRegistered() {


        return registered;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String[] getTags() {
        return tags;
    }

    public List<Map<String, Integer>> getFriends() {
        return friends;
    }

    public String getFavoriteFruit() {
        return favoriteFruit;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", guid='" + guid + '\'' +
                ", isActive=" + isActive +
                ", balance='" + balance + '\'' +
                ", age=" + age +
                ", eyeColor='" + eyeColor + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", company='" + company + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", about='" + about + '\'' +
                ", registered='" + registered + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", tags=" + Arrays.toString(tags) +
                ", friends=" + friends +
                ", favoriteFruit='" + favoriteFruit + '\'' +
                '}';
    }
}

package com.auction.model;

import java.sql.Timestamp;

public class Auction {
    private int id;
    private String itemName;
    private double startPrice;
    private Timestamp endTime;
    private int userId;

    public Auction() {}

    public Auction(int id, String itemName, double startPrice, Timestamp endTime, int userId) {
        this.id = id;
        this.itemName = itemName;
        this.startPrice = startPrice;
        this.endTime = endTime;
        this.userId = userId;
    }

    public Auction(String itemName, double startPrice, int userId) {
        this.itemName = itemName;
        this.startPrice = startPrice;
        this.userId = userId;
        this.endTime = new Timestamp(System.currentTimeMillis() + (24 * 60 * 60 * 1000)); // Default 24-hour auction
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

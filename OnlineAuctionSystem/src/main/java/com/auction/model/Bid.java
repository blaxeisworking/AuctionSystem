package com.auction.model;

import java.sql.Timestamp;

public class Bid {
    private int id;
    private int auctionId;
    private int userId;
    private double bidAmount;
    private Timestamp bidTime;

    public Bid() {}

    public Bid(int id, int auctionId, int userId, double bidAmount, Timestamp bidTime) {
        this.id = id;
        this.auctionId = auctionId;
        this.userId = userId;
        this.bidAmount = bidAmount;
        this.bidTime = bidTime;
    }

    public Bid(int auctionId, int userId, double bidAmount) {
        this.auctionId = auctionId;
        this.userId = userId;
        this.bidAmount = bidAmount;
        this.bidTime = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Timestamp getBidTime() {
        return bidTime;
    }

    public void setBidTime(Timestamp bidTime) {
        this.bidTime = bidTime;
    }
}

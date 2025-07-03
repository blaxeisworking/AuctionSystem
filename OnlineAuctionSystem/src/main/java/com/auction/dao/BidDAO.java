package com.auction.dao;

import com.auction.model.Bid;
import com.auction.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BidDAO {
    public static boolean placeBid(Bid bid) {
        String sql = "INSERT INTO bids (auction_id, bid_amount, bidder_id, timestamp) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bid.getAuctionId());
            stmt.setDouble(2, bid.getBidAmount());
            stmt.setInt(3, bid.getUserId());
            stmt.setTimestamp(4, bid.getBidTime());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

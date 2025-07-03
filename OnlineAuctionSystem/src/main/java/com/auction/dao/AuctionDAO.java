package com.auction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.auction.model.Auction;
import com.auction.utils.DBConnection;

public class  AuctionDAO {

    public static boolean createAuction(Auction auction) {
        String query = "INSERT INTO auctions (item_name, start_price, end_time, user_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, auction.getItemName());
            stmt.setDouble(2, auction.getStartPrice());
            stmt.setTimestamp(3, auction.getEndTime());
            stmt.setInt(4, auction.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Auction> searchAuctions(String keyword) {
        List<Auction> auctions = new ArrayList<>();
        String query = "SELECT * FROM auctions WHERE item_name LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Auction auction = new Auction(
                        rs.getInt("id"),
                        rs.getString("item_name"),
                        rs.getDouble("start_price"),
                        rs.getTimestamp("end_time"),
                        rs.getInt("user_id")
                );
                auctions.add(auction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auctions;
    }

    public static List<Auction> getAuctionsByUser(int userId) {
        List<Auction> auctions = new ArrayList<>();
        String query = "SELECT * FROM auctions WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Auction auction = new Auction(
                        rs.getInt("id"),
                        rs.getString("item_name"),
                        rs.getDouble("start_price"),
                        rs.getTimestamp("end_time"),
                        rs.getInt("user_id")
                );
                auctions.add(auction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auctions;
    }

    public static List<Auction> getAllAuctions() {
        List<Auction> auctions = new ArrayList<>();
        String query = "SELECT * FROM auctions";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Auction auction = new Auction(
                        rs.getInt("id"),
                        rs.getString("item_name"),
                        rs.getDouble("start_price"),
                        rs.getTimestamp("end_time"),
                        rs.getInt("user_id")
                );
                auctions.add(auction);
            }
            return auctions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auctions;
    }

    public static Auction getAuctionById(int id) {
        String query = "SELECT * FROM auctions WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Auction(
                            rs.getInt("id"),
                            rs.getString("item_name"),
                            rs.getDouble("start_price"),
                            rs.getTimestamp("end_time"),
                            rs.getInt("user_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

package com.crowdfund.dao;

import com.crowdfund.config.DBConnection;
import com.crowdfund.model.Campaign;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CampaignDAO {
    public List<Campaign> getApprovedCampaigns() throws SQLException {
        List<Campaign> campaigns = new ArrayList<>();
        String sql = "SELECT * FROM campaigns WHERE status = ? ORDER BY created_at DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "APPROVED");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    campaigns.add(new Campaign(
                        rs.getInt("id"), rs.getString("title"), rs.getString("description"),
                        rs.getString("creator_name"), rs.getBigDecimal("target_amount"),
                        rs.getBigDecimal("raised_amount"), rs.getString("status")));
                }
            }
        }
        return campaigns;
    }

    public void createCampaign(String title, String description, String creatorName,
                               BigDecimal targetAmount) throws SQLException {
        String sql = "INSERT INTO campaigns(title, description, creator_name, target_amount, status) VALUES(?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, creatorName);
            ps.setBigDecimal(4, targetAmount);
            ps.setString(5, "PENDING");
            ps.executeUpdate();
        }
    }

    public boolean contribute(int campaignId, BigDecimal amount) throws SQLException {
        if (campaignId <= 0 || amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) return false;
        String check = "SELECT id FROM campaigns WHERE id = ? AND status = 'APPROVED'";
        String addContribution = "INSERT INTO contributions(campaign_id, amount) VALUES(?,?)";
        String updateRaised = "UPDATE campaigns SET raised_amount = raised_amount + ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try {
                try (PreparedStatement ps = con.prepareStatement(check)) {
                    ps.setInt(1, campaignId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) { con.rollback(); return false; }
                    }
                }
                try (PreparedStatement ps = con.prepareStatement(addContribution)) {
                    ps.setInt(1, campaignId);
                    ps.setBigDecimal(2, amount);
                    ps.executeUpdate();
                }
                try (PreparedStatement ps = con.prepareStatement(updateRaised)) {
                    ps.setBigDecimal(1, amount);
                    ps.setInt(2, campaignId);
                    ps.executeUpdate();
                }
                con.commit();
                return true;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }
}

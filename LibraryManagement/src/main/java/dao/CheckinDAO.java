/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.Connect.connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Checkin;

/**
 *
 * @author Admin
 */
public class CheckinDAO extends Connect{
    

    public Checkin checkUserByCheckin(int userid) throws SQLException {
        String sql = "SELECT * FROM `checkin` WHERE userid = ? ORDER BY id DESC LIMIT 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userid);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()){
                    Checkin c = mapCheckin(rs);
                    return c;
                }
            }
        }
        return null;
    }

    public List<Checkin> getAllCheckin() throws SQLException {
        List<Checkin> list = new ArrayList<>();
        String sql = "SELECT * FROM `checkin` ORDER BY id DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    Checkin c = mapCheckin(rs);
                    list.add(c);
                }
            }
        }
        return list;
    }

    public List<Checkin> getCheckinByEndNull() throws SQLException {
        List<Checkin> list = new ArrayList<>();
        String sql = "SELECT * FROM `checkin` WHERE end IS NULL";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    Checkin c = mapCheckin(rs);
                    list.add(c);
                }
            }
        }
        return list;
    }

    public List<Checkin> getCheckinByDate(Timestamp startTimestamp, Timestamp endTimestamp) throws SQLException {
        List<Checkin> list = new ArrayList<>();
        String sql = "SELECT * FROM `checkin` WHERE start >= ? AND start <= ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setTimestamp(1, startTimestamp);
            ps.setTimestamp(2, endTimestamp);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    Checkin c = mapCheckin(rs);
                    list.add(c);
                }
            }
        }
        return list;
    }

    private Checkin mapCheckin(ResultSet rs) throws SQLException {
        return new Checkin(rs.getInt("id"), rs.getTimestamp("start"), rs.getTimestamp("end"), new UserDAO().getUserById(rs.getInt("userid")));
    }

    public void insertCheckin(int userid, Timestamp start) throws SQLException {
        String sql = "INSERT INTO `checkin`(`userid`, `start`) VALUES (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userid);
            ps.setTimestamp(2, start);
            ps.executeUpdate();
        }
    }

    public void updateCheckin(int id, Timestamp end) throws SQLException {
        String sql = "UPDATE `checkin` SET `end`= ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setTimestamp(1, end);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }
}

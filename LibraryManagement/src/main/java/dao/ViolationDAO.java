/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.BorrowReturn1;
import model.Violation;

/**
 *
 * @author Admin
 */
public class ViolationDAO extends Connect{


    public List<Violation> getAllViolation() throws SQLException {
        List<Violation> list = new ArrayList<>();
        String sql = "SELECT * FROM `violation`";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Violation v = mapViolation(rs);
                list.add(v);
            }
        }
        return list;
    }

    public List<Violation> getViolationByDate(Date dateStart, Date dateEnd) throws SQLException {
        List<Violation> list = new ArrayList<>();
        String sql = "SELECT * FROM `violation` WHERE date >= ? AND date <= ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, dateStart);
            ps.setDate(2, dateEnd);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Violation v = mapViolation(rs);
                    list.add(v);
                }
            }
        }
        return list;
    }

    public List<Violation> getViolationByKey(String keyString) throws SQLException {
        List<Violation> list = new ArrayList<>();
        String sql = "SELECT violation.`id`, violation.`readerid`, violation.`userid`, violation.`date`, violation.`note` FROM `violation`, reader WHERE violation.readerid = reader.id AND reader.fullname LIKE '%" + keyString + "%'";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Violation v = mapViolation(rs);
                list.add(v);
            }
        }
        return list;
    }

    public void addViolation(Violation v) throws SQLException {
        int id = this.insertViolation(v);
        for(BorrowReturn1 br : v.getBorrowReturn()){
            new BorrowReturnDAO().updateViolation(id, br.getId());
            new BorrowReturnDAO().updateErrorBookReturn(br);
        }
    }

    private int insertViolation(Violation v) throws SQLException {
        String sql = "INSERT INTO `violation`(`readerid`, `userid`, `date`, `note`) "
                + "VALUES (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, v.getReader().getId());
            ps.setInt(2, v.getUser().getId());
            ps.setDate(3, v.getDate());
            ps.setString(4, v.getNote());
            int affectedRows = ps.executeUpdate();
        
            // Check if any row was inserted
            if (affectedRows > 0) {
                // Retrieve the generated keys
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Use the column index to retrieve the generated key
                    }
                }
            }
        }
        return -1;
    }
    
    
    private Violation mapViolation(ResultSet rs) throws SQLException {
        return new Violation(rs.getInt("id"), rs.getDate("date"), rs.getString("note"), 
                new UserDAO().getUserById(rs.getInt("readerid")), 
                new UserDAO().getUserById(rs.getInt("userid")), 
                new BorrowReturnDAO().getBorrowReturnByViolationId(rs.getInt("id")));
    }
    
}

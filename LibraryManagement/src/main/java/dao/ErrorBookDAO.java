/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ErrorBook;

/**
 *
 * @author Admin
 */
public class ErrorBookDAO extends Connect {
    

    public ErrorBook getErrorBookById(int id) throws SQLException {
        String sql = "SELECT * FROM `errorbook` WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapErrorBook(rs);
                }
            }
        }
        return null;
    }

    public List<ErrorBook> getAllErrorBook() throws SQLException {
        String sql = "SELECT * FROM `errorbook`";
        List<ErrorBook> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ErrorBook eb = mapErrorBook(rs);
                list.add(eb);
            }
        }
        return list;
    }

    private ErrorBook mapErrorBook(ResultSet rs) throws SQLException {
        return new ErrorBook(rs.getInt("id"), rs.getInt("percentage"), rs.getString("name"));
    }

    public void addErrorBook(ErrorBook eb) throws SQLException {
        String sql = "INSERT INTO `errorbook`(`name`, `percentage`) VALUES (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, eb.getName());
            ps.setInt(2, eb.getPercentage());
            ps.executeUpdate();
        }
    }
}

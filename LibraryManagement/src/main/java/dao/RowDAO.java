/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.Connect.connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Row;

/**
 *
 * @author Admin
 */
public class RowDAO extends Connect{

    public List<Row> getAllRow() throws SQLException {
        List<Row> list = new ArrayList<>();
        String sql = "SELECT * FROM `row`";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Row r = mapRow(rs);
                    list.add(r);
                }
            }
        }
        return list;
    }
    
    public Row getRowById(int id) throws SQLException {
        String sql = "SELECT * FROM `row` WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Row r = mapRow(rs);
                    return r;
                }
            }
        }
        return null;
    }

    private Row mapRow(ResultSet rs) throws SQLException {
        return new Row(rs.getInt("id"), rs.getString("name"));
    }
    
    
}

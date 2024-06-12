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
import model.Level;

/**
 *
 * @author Admin
 */
public class LevelDAO extends Connect{

    public List<Level> getAllLevel() throws SQLException {
        List<Level> list = new ArrayList<>();
        String sql = "SELECT * FROM `level`";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Level l = mapLevel(rs);
                    list.add(l);
                }
            }
        }
        return list;
    }

    private Level mapLevel(ResultSet rs) throws SQLException {
        return new Level(rs.getInt("id"), rs.getInt("name"));
    }

    public Level getLevelById(int id) throws SQLException {
        String sql = "SELECT * FROM `level` where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Level l = mapLevel(rs);
                    return l;
                }
            }
        }
        return null;
    }
    
    
}

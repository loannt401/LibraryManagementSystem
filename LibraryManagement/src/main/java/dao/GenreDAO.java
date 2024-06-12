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
import model.Genre;

/**
 *
 * @author Admin
 */
public class GenreDAO extends Connect{
    public Genre getGenreById(int id) throws SQLException{
        String sql = "SELECT * FROM `genre` WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapGenre(rs);
                }
            }
        }
        return null;
    }
    
    public List<Genre> getAllGenre() throws SQLException{
        List<Genre> list = new ArrayList<>();
        String sql = "SELECT * FROM `genre`";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Genre g = mapGenre(rs);
                list.add(g);
            }
        }
        return list;
    }

    public int getGenreByName(String nameGenreString) throws SQLException {
        String sql = "SELECT count(*) FROM `genre` WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nameGenreString);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()){
                    return rs.getInt("count(*)");
                }
            }
        }
        return 0;
    }
    
    public void addGenre(String nameGenre) throws SQLException{
        String sql = "INSERT INTO `genre`(`name`) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nameGenre);
            ps.executeUpdate();
        }
    }

    public void updateGenre(int id, String name) throws SQLException {
        String sql = "update genre set name = ? where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public boolean deleteGenre(int id) {
        String sql = "delete from genre where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    private Genre mapGenre(ResultSet rs) throws SQLException {
        return new Genre(rs.getInt("id"), rs.getString("name"));
    }
}

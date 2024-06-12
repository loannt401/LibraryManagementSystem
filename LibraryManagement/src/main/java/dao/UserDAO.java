/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.Connect.connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Role1;
import model.User1;

/**
 *
 * @author Admin
 */
public class UserDAO extends Connect{

    private User1 mapUser(ResultSet rs) throws SQLException {
        return new User1(rs.getInt("id"), rs.getString("userid"), rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getDate("date"), new RoleDAO().getRoleByUserId(rs.getInt("id")));
    }
    
    public User1 getUserById(int id) throws SQLException{
        String sql = "SELECT * FROM `user` WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapUser(rs);
                }
            }
        }
        return null;
    }

    public User1 getUserByUserId(String userid) throws SQLException {
        String sql = "SELECT * FROM `user` WHERE userid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapUser(rs);
                }
            }
        }
        return null;
    }


    public List<User1> getUserByRoleid(int roleid) throws SQLException {
        String sql = "SELECT * FROM `roleuser` WHERE roleid = ?";
        List<User1> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, roleid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User1 u = this.getUserById(rs.getInt("userid"));
                    list.add(u);
                }
            }
        }
        return list;
    }

    public List<User1> getUserByName(String key, int roleid)  throws SQLException {
        String sql = "SELECT user.id FROM `user`, roleuser "
                + "WHERE user.id = roleuser.userid "
                + "AND roleuser.roleid = ? "
                + "and (user.fullname like '%" + key + "%' or user.userid like '%" + key + "%');";
        List<User1> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, roleid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User1 u = this.getUserById(rs.getInt("id"));
                    list.add(u);
                }
            }
        }
        return list;
    }

    public List<User1> getUserByDate(int roleid, Date datestart, Date dateend) throws SQLException {
        List<User1> list = new ArrayList<>();
        String sql = "SELECT user.id FROM `user`, `roleuser` "
                + "WHERE roleuser.userid = user.id AND roleuser.roleid = ? "
                + "AND user.date >= ? AND user.date <= ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, roleid);
            ps.setDate(2, datestart);
            ps.setDate(3, dateend);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User1 r = this.getUserById(rs.getInt("id"));
                    list.add(r);
                }
            }
        }
        return list;
    }

    public int addUser(User1 u) throws SQLException {
        int id = this.insertUser(u);
        String useridString = "ND" + String.format("%03d", id);
        this.updateUserId(useridString, id);
        for(Role1 r : u.getRole()){
            this.updateRole(r.getId(), id);
        }
        
        return id;
    }
    
    public int insertUser(User1 u) throws SQLException{
        String sql = "INSERT INTO `user`"
                + "(`fullname`, `email`, `phone`, `address`, `date`) "
                + "VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getFullname());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPhone());
            ps.setString(4, u.getAddress());
            ps.setDate(5, u.getDate());
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

    private void updateUserId(String userid, int id) throws SQLException {
        String sql = "UPDATE `librarymanagement`.`user` SET `userid` = ? WHERE `id` = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userid);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void updateUser(User1 u) throws SQLException {
        String sql = "UPDATE `librarymanagement`.`user` "
                + "SET  `fullname` = ?, `email` = ?, `phone` = ?, "
                + "`address` = ?,`date`= ? "
                + "WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, u.getFullname());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPhone());
            ps.setString(4, u.getAddress());
            ps.setDate(5, u.getDate());
            ps.setInt(6, u.getId());
            ps.executeUpdate();
        }
    }

    public void updatePassword(int id, String passnewString) throws SQLException {
        String sql = "UPDATE `librarymanagement`.`user` SET `password` = ? WHERE `id` = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, passnewString);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void updateRole(int roleid, int userid) throws SQLException {
        String sql = "INSERT INTO `roleuser`(`roleid`, `userid`) VALUES (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, roleid);
            ps.setInt(2, userid);
            ps.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        this.deleteRoleUser(id);
        String sql = "DELETE FROM `user` WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private void deleteRoleUser(int userid) throws SQLException {
        String sql = "DELETE FROM `roleuser` WHERE userid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userid);
            ps.executeUpdate();
        }
    }
}

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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role1;

/**
 *
 * @author Admin
 */
public class RoleDAO extends Connect{
    public Role1 getRoleById(int id){
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM `role` WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRole(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Role1> getRoleByUserId(int userid){
        List<Role1> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM `roleuser` WHERE userid = ?")) {
            ps.setInt(1, userid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Role1 role = getRoleById(rs.getInt("roleid"));
                    list.add(role);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private Role1 mapRole(ResultSet rs) throws SQLException{
        return new Role1(rs.getInt("id"), rs.getString("name"));
    }
    
//    public static void main(String[] args) {
//        Role r = new RoleDAO().getRole(1);
//        System.out.println(r.getName());
//    }

}

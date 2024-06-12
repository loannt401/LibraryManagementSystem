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
import model.Employee;
import model.User1;

/**
 *
 * @author Admin
 */
public class EmployeeDAO extends Connect{

    public Employee getEmployeeByUsernameAndPassword(String username, String password) {
        
        String sql = "SELECT * FROM `employee` WHERE username = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapEmployee(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Employee mapEmployee(ResultSet rs) throws SQLException {
        return new Employee(new UserDAO().getUserById(rs.getInt("userid")), rs.getString("username"), rs.getString("password"), rs.getBoolean("status"));
    }

    public void addEmployee(Employee e) throws SQLException {
        int id = new UserDAO().addUser(e);
        this.insertEmployee(id, e.getUsername(), e.getPassword(), e.isStatus());
    }

    private void insertEmployee(int id, String username, String password, boolean status) throws SQLException {
        String sql = "INSERT INTO `employee`"
                + "(`userid`, `username`, `password`, `status`) "
                + "VALUES (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setBoolean(4, status);
            ps.executeUpdate();
        }
    }

    public Employee getEmployeeByUserid(int id) throws SQLException {
        String sql = "SELECT * FROM `employee` WHERE userid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapEmployee(rs);
                }
            }
        }
        return null;
    }

    public void updatePassword(int id, String passnewString) throws SQLException {
        String sql = "UPDATE `employee` SET `password`='?' WHERE userid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, passnewString);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void editEmployee(Employee e) throws SQLException {
        new UserDAO().updateUser(e);
        this.updateEmployee(e);
    }

    private void updateEmployee(Employee e) throws SQLException {
        String sql = "UPDATE `employee` SET "
                + "`username`= ?,`password`= ?,`status`= ? "
                + "WHERE userid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, e.getUsername());
            ps.setString(2, e.getPassword());
            ps.setBoolean(3, e.isStatus());
            ps.setInt(4, e.getId());
        }
    }

    public List<Employee> getAllEmployee() throws SQLException {
        String sql = "SELECT user.id FROM user, roleuser "
                + "WHERE user.id = roleuser.userid AND roleuser.roleid = 2";
        List<Employee> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employee e = this.getEmployeeByUserid(rs.getInt("id"));
                    list.add(e);
                }
            }
        }
        return list;
    }

    public List<Employee> getEmployeeByName(String key) throws SQLException {
        String sql = "SELECT user.id FROM user, roleuser "
                + "WHERE user.id = roleuser.userid "
                + "AND roleuser.roleid = 2 "
                + "and (user.fullname like '%" + key + "%' or user.userid like '%" + key + "%')";
        List<Employee> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employee e = this.getEmployeeByUserid(rs.getInt("id"));
                    list.add(e);
                }
            }
        }
        return list;
        
    }
    
}

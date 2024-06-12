/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class User1 {
    private int id;
    private String userid, fullname, email, phone, address;
    private Date date;
    private List<Role1> role;

    public User1() {
    }

    public User1(int id, String userid, String fullname, String email, String phone, String address, Date date, List<Role1> role) {
        this.id = id;
        this.userid = userid;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.date = date;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Role1> getRole() {
        return role;
    }

    public void setRole(List<Role1> role) {
        this.role = role;
    }

    
    
    
}

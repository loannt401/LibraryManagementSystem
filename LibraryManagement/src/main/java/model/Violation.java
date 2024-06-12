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
public class Violation {
    private int id;
    private Date date;
    private String note;
    private User1 reader;
    private User1 user;
    private List<BorrowReturn1> borrowReturn;
    public Violation() {
    }

    public Violation(int id, Date date, String note, User1 reader, User1 user, List<BorrowReturn1> borrowReturn) {
        this.id = id;
        this.date = date;
        this.note = note;
        this.reader = reader;
        this.user = user;
        this.borrowReturn = borrowReturn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User1 getReader() {
        return reader;
    }

    public void setReader(User1 reader) {
        this.reader = reader;
    }

    public User1 getUser() {
        return user;
    }

    public void setUser(User1 user) {
        this.user = user;
    }

    public List<BorrowReturn1> getBorrowReturn() {
        return borrowReturn;
    }

    public void setBorrowReturn(List<BorrowReturn1> borrowReturn) {
        this.borrowReturn = borrowReturn;
    }

    
    
}

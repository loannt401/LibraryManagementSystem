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
public class BorrowReturn1 {
    private int id;
    private Date duedate;
    private Date borrowdate, returndate;
    private Abook abook;
    private User1 reader;
    private List<ErrorBookReturn> errorBookReturn;

    public BorrowReturn1() {
    }

    public BorrowReturn1(int id, Date duedate, Date borrowdate, Date returndate, Abook abook, User1 reader, List<ErrorBookReturn> errorBookReturn) {
        this.id = id;
        this.duedate = duedate;
        this.borrowdate = borrowdate;
        this.returndate = returndate;
        this.abook = abook;
        this.reader = reader;
        this.errorBookReturn = errorBookReturn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public Date getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(Date borrowdate) {
        this.borrowdate = borrowdate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public Abook getAbook() {
        return abook;
    }

    public void setAbook(Abook abook) {
        this.abook = abook;
    }

    public User1 getReader() {
        return reader;
    }

    public void setReader(User1 reader) {
        this.reader = reader;
    }

    public List<ErrorBookReturn> getErrorBookReturn() {
        return errorBookReturn;
    }

    public void setErrorBookReturn(List<ErrorBookReturn> errorBookReturn) {
        this.errorBookReturn = errorBookReturn;
    }

      
    
}

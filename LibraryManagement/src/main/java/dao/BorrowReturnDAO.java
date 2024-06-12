/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BorrowReturn1;
import model.ErrorBook;
import model.ErrorBookReturn;

/**
 *
 * @author Admin
 */
public class BorrowReturnDAO extends Connect {

    private BorrowReturn1 mapBorrowReturn(ResultSet rs) throws SQLException {
        return new BorrowReturn1(rs.getInt("id"), rs.getDate("duedate"), rs.getDate("borrowdate"), 
                rs.getDate("returndate"), new AbookDAO().getAbookById(rs.getInt("abookid")), 
                new UserDAO().getUserById(rs.getInt("readerid")), 
                new ErrorBookReturnDAO().getErrorBookByBorrowReturnId(rs.getInt("id")));
    }

    public List<BorrowReturn1> getBorrowReturnNotReturnByReaderId(int readerid) throws SQLException {
        List<BorrowReturn1> list = new ArrayList<>();
        String sql = "SELECT * FROM `borrowreturn` WHERE readerid = ? AND returndate IS NULL";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, readerid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BorrowReturn1 br = mapBorrowReturn(rs);
                    list.add(br);
                }
            }
        }
        return list;
    }

    public BorrowReturn1 getBorrowReturnById(int id) throws SQLException {
        String sql = "SELECT * FROM `borrowreturn` WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapBorrowReturn(rs);
                }
            }
        }
        return null;
    }

    public List<BorrowReturn1> getBorrowReturnByDateReturnNull() throws SQLException {
        List<BorrowReturn1> list = new ArrayList<>();
        String sql = "SELECT * FROM `borrowreturn` WHERE returndate IS NULL";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                BorrowReturn1 br = mapBorrowReturn(rs);
                list.add(br);
            }
        }
        return list;
    }

    public List<BorrowReturn1> getBorrowReturnByDateReturnNotNull() throws SQLException {
        List<BorrowReturn1> list = new ArrayList<>();
        String sql = "SELECT * FROM `borrowreturn` WHERE returndate IS NOT NULL";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                BorrowReturn1 br = mapBorrowReturn(rs);
                list.add(br);
            }
        }
        return list;
    }

    public List<BorrowReturn1> getBorrowReturnByDateReturnNullAndDateBorrow(Date dateStartSql, Date dateEndSql) throws SQLException {
        List<BorrowReturn1> list = new ArrayList<>();
        String sql = "SELECT * FROM `borrowreturn` WHERE returndate IS NULL AND borrowdate >= ? AND borrowdate <= ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, dateStartSql);
            ps.setDate(2, dateEndSql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BorrowReturn1 br = mapBorrowReturn(rs);
                    list.add(br);
                }
            }
        }
        return list;
    }

    public List<BorrowReturn1> getBorrowReturnByDateReturnNullAndDateDue(Date currentDate) throws SQLException {
        List<BorrowReturn1> list = new ArrayList<>();
        String sql = "SELECT * FROM `borrowreturn` WHERE returndate IS NULL AND duedate < ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, currentDate);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BorrowReturn1 br = mapBorrowReturn(rs);
                    list.add(br);
                }
            }
        }
        return list;
    }

    List<BorrowReturn1> getBorrowReturnByViolationId(int violationid) throws SQLException {
        List<BorrowReturn1> list = new ArrayList<>();
        String sql = "SELECT * FROM `borrowreturn` WHERE violationid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, violationid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BorrowReturn1 br = mapBorrowReturn(rs);
                    list.add(br);
                }
            }
        }
        return list;
    }

    public List<BorrowReturn1> getBorrowReturnByDateReturnNullAndDateReturn(Date dateStart, Date dateEnd) throws SQLException {
        List<BorrowReturn1> list = new ArrayList<>();
        String sql = "SELECT * FROM `borrowreturn` WHERE returndate IS NOT NULL AND returndate >= ? AND returndate <= ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, dateStart);
            ps.setDate(2, dateEnd);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BorrowReturn1 br = mapBorrowReturn(rs);
                    list.add(br);
                }
            }
        }
        return list;
    }

    public List<BorrowReturn1> getBorrowReturnByDateReturnNotNullAndErrorBook() throws SQLException {
        List<BorrowReturn1> list = new ArrayList<>();
        String sql = "SELECT * FROM `borrowreturn` WHERE violationid IS NOT NULL";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BorrowReturn1 br = mapBorrowReturn(rs);
                    list.add(br);
                }
            }
        }
        return list;
    }
    
    
    public int getCountBorrowReturnByReaderid(int readerid) throws SQLException {
        String sql = "SELECT count(*) FROM librarymanagement.borrowreturn where readerid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, readerid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count(*)");
                }
            }
        }
        return 0;
    }

    public List<BorrowReturn1> getBorrowReturnByUserid(int userid) throws SQLException {
        List<BorrowReturn1> list = new ArrayList<>();
        String sql = "SELECT * FROM librarymanagement.borrowreturn where readerid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BorrowReturn1 br = mapBorrowReturn(rs);
                    list.add(br);
                }
            }
        }
        return list;
        
    }

    public List<BorrowReturn1> getBorrowReturnByReaderidAndNotReturn(int readerid)  throws SQLException {
        List<BorrowReturn1> list = new ArrayList<>();
        String sql = "SELECT * FROM librarymanagement.borrowreturn where readerid = ? and returndate is null";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, readerid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BorrowReturn1 br = mapBorrowReturn(rs);
                    list.add(br);
                }
            }
        }
        return list;
    }

    public List<BorrowReturn1> getBorrowReturnByReaderidAndReturn(int readerid)   throws SQLException {
        List<BorrowReturn1> list = new ArrayList<>();
        String sql = "SELECT * FROM librarymanagement.borrowreturn where readerid = ? and returndate is not null";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, readerid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BorrowReturn1 br = mapBorrowReturn(rs);
                    list.add(br);
                }
            }
        }
        return list;
    }


    public void addBorrowReturn(BorrowReturn1 br) throws SQLException {
        new AbookDAO().updateAbookStatusFalse(br.getAbook().getId());
        String sql = "INSERT INTO `borrowreturn`(`readerid`, `abookid`, `borrowdate`, `duedate`) VALUES (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, br.getReader().getId());
            ps.setInt(2, br.getAbook().getId());
            ps.setDate(3, br.getBorrowdate());
            ps.setDate(4, br.getDuedate());
            ps.executeUpdate();
        }
    }

    public void updateBorrowReturnDueDate(Date dueDateSql, int id) throws SQLException {
        String sql = "UPDATE `borrowreturn` SET `duedate`= ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, dueDateSql);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void updateBorrowReturnDateReturn(Date returnDate, int id) throws SQLException {
        BorrowReturn1 br = this.getBorrowReturnById(id);
        int abookid = br.getAbook().getId();
        new AbookDAO().updateAbookStatusTrue(abookid);
        String sql = "UPDATE `borrowreturn` SET `returndate`= ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, returnDate);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void updateErrorBookReturn(BorrowReturn1 br) throws SQLException {
        for (ErrorBookReturn eb : br.getErrorBookReturn()) {
            this.insertErrorBookReturn(br.getId(), eb.getErrorBook().getId(), eb.getPrice());
        }
    }

    private void insertErrorBookReturn(int borrowreturnid, int errorbookid, int price) throws SQLException {
        String sql = "INSERT INTO `errorbookreturn`"
                + "(`borrowreturnid`, `errorbookid`, `price`) "
                + "VALUES (?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, borrowreturnid);
            ps.setInt(2, errorbookid);
            ps.setInt(3, price);
            ps.executeUpdate();
        }
    }

    void updateViolation(int violationid, int borrowreturnid) throws SQLException {
        String sql = "UPDATE `borrowreturn` SET `violationid`= ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, violationid);
            ps.setInt(2, borrowreturnid);
            ps.executeUpdate();
        }
    }
}

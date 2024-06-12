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
import model.ErrorBookReturn;

/**
 *
 * @author Admin
 */
public class ErrorBookReturnDAO extends Connect{
    public List<ErrorBookReturn> getErrorBookByBorrowReturnId(int borrowreturnid) throws SQLException {
        List<ErrorBookReturn> list = new ArrayList<>();
        String sql = "SELECT * FROM `errorbookreturn` WHERE borrowreturnid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, borrowreturnid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ErrorBookReturn eb = mapErrorBookReturn(rs);
                    list.add(eb);
                }
            }
        }
        return list;
    }

    private ErrorBookReturn mapErrorBookReturn(ResultSet rs) throws SQLException {
        return new ErrorBookReturn(new ErrorBookDAO().getErrorBookById(rs.getInt("errorbookid")), rs.getInt("price"));
    }
}

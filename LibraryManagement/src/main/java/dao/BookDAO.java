package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Book1;

public class BookDAO extends Connect {
    public Book1 getBookById(int id) throws SQLException {
        String sql = "SELECT * FROM `book` WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapBook(rs);
                }
            }
        }
        return null;
    }

    public List<Book1> getAllBook() throws SQLException {
        List<Book1> list = new ArrayList<>();
        String sql = "SELECT * FROM `book`";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Book1 b = mapBook(rs);
                list.add(b);
            }
        }
        return list;
    }

    public List<Book1> getBookByName(String searchString) throws SQLException {
        List<Book1> list = new ArrayList<>();
        String sql = "SELECT * FROM `book` WHERE name LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + searchString + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapBook(rs));
                }
            }
        }
        return list;
    }
    
    public int getCountBookById(int id) throws SQLException{
        String sql = "select count(*) from abook where bookid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("count(*)");
                }
            }
        }
        return 0;
    }

    public List<Book1> getBookByGenreId(int id) throws SQLException {
        List<Book1> list = new ArrayList<>();
        String sql = "select * from book where genreid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapBook(rs));
                }
            }
        }
        return list;
    }

    public List<Book1> getBookByRowId(int id) throws SQLException {
        List<Book1> list = new ArrayList<>();
        String sql = "select * from book where rowid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapBook(rs));
                }
            }
        }
        return list;
    }

    public List<Book1> getBookByLevelId(int id) throws SQLException {
        List<Book1> list = new ArrayList<>();
        String sql = "select * from book where levelid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapBook(rs));
                }
            }
        }
        return list;
    }

    public List<Book1> getBookByRowIdAndLevelId(int rowid, int levelid) throws SQLException {
        List<Book1> list = new ArrayList<>();
        String sql = "select * from book where rowid = ? and levelid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, rowid);
            ps.setInt(2, levelid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapBook(rs));
                }
            }
        }
        return list;
    }

    public void addBook(Book1 book) throws SQLException {
        String sql = "INSERT INTO `book`(`genreid`, `name`, `author`, `price`, "
                + "`numberpage`, `description`, `rowid`, `levelid`) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, book.getGenre().getId());
            ps.setString(2, book.getName());
            ps.setString(3, book.getAuthor());
            ps.setInt(4, book.getPrice());
            ps.setInt(5, book.getNumberpage());
            ps.setString(6, book.getDescription());
            ps.setInt(7, book.getRow().getId());
            ps.setInt(8, book.getLevel().getId());
            ps.executeUpdate();
        }
    }

    public void updateBook(Book1 book) throws SQLException {
        String sql = "UPDATE `librarymanagement`.`book` "
                + "SET `genreid` = ?, `name` = ?, `author` = ?, "
                + "`price` = ?, `numberpage` = ?, `description` = ?,"
                + "`rowid`= ?,`levelid`= ? "
                + "WHERE `id` = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, book.getGenre().getId());
            ps.setString(2, book.getName());
            ps.setString(3, book.getAuthor());
            
            ps.setInt(4, book.getPrice());
            ps.setInt(5, book.getNumberpage());
            ps.setString(6, book.getDescription());
            
            ps.setInt(7, book.getRow().getId());
            ps.setInt(8, book.getLevel().getId());
            
            ps.setInt(9, book.getId());
            
            ps.executeUpdate();
        }
    }

    public boolean deleteBook(int id) {
        String sql = "DELETE FROM `librarymanagement`.`book` WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    private Book1 mapBook(ResultSet rs) throws SQLException {
        return new Book1(rs.getInt("id"), rs.getInt("price"), rs.getInt("numberpage"), 
                rs.getString("name"), rs.getString("author"), rs.getString("description"),
                new GenreDAO().getGenreById(rs.getInt("genreid")), 
                new RowDAO().getRowById(rs.getInt("rowid")), 
                new LevelDAO().getLevelById(rs.getInt("levelid")));
    }
}

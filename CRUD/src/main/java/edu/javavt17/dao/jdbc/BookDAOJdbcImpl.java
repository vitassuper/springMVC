package edu.javavt17.dao.jdbc;

import edu.javavt17.dao.BookAuthorDAO;
import edu.javavt17.dao.BookDAO;
import edu.javavt17.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDAOJdbcImpl implements BookDAO {
    @Autowired
    @Qualifier("getBookAuthorJdbcDAO")
    private BookAuthorDAO bookAuthorDAO;

    private JdbcTemplate jdbcTemplate;

    public BookDAOJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(Book item) {
        if (item.getIdBook() > 0) {
            // update
            String sql = "UPDATE books SET AutorId=?, name=?, FoundedYear=?, Description=? WHERE id=?";
            jdbcTemplate.update(sql, item.getIdAuthor(), item.getBookName(),item.getFoundedYear(),
                    item.getDescription());
        } else {
            // insert
            String sql = "INSERT INTO books (AuthorId, name, FoundedYear, Description)"
                    + " VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, item.getIdAuthor(), item.getBookName(),item.getFoundedYear(),
                    item.getDescription());
        }
    }

    public void delete(int itemId) {
        String sql = "DELETE FROM books WHERE id=?";
        jdbcTemplate.update(sql, itemId);
    }

    public Book get(int itemId) {
        String sql = "SELECT * FROM books WHERE id=" + itemId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Book>() {

            public Book extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return getBookFromDB(rs);
                }
                return null;
            }
        });
    }

    public List<Book> list() {
        String sql = "SELECT * FROM books";
        List<Book> listBook = jdbcTemplate.query(sql, new RowMapper<Book>() {

            public Book mapRow(ResultSet rs, int i) throws SQLException {

                return getBookFromDB(rs);
            }
        });
        return listBook;
    }

    private Book getBookFromDB(ResultSet rs) throws SQLException{
        Book model = new Book();
        model.setId(rs.getInt("id"));
        model.setIdAuthor(rs.getInt("AuthorId"));
        model.setBookAuthor(bookAuthorDAO.get(rs.getInt("AuthorId")));
        model.setBookName(rs.getString("name"));
        model.setDescription(rs.getString("Description"));
        model.setFoundedYear(rs.getShort("FoundedYear"));
        return model;
    }
}

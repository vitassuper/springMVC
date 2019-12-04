package edu.javavt17.dao.jdbc;

import edu.javavt17.dao.BookAuthorDAO;
import edu.javavt17.model.BookAuthor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;


public class BookAuthorDAOJdbcImpl implements BookAuthorDAO {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public BookAuthorDAOJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(BookAuthor item) {
        if (item.getId() > 0) {
            // update
            System.out.println("Author update");
            String sql = "UPDATE authors SET name=?, BirthYear=?, Alias=? WHERE id=?";
            jdbcTemplate.update(sql, item.getName(), item.getBirthYear(),item.getAlias(), item.getId());
        } else {
            // insert
            System.out.println("BookAuthor insert");
            String sql = "INSERT INTO authors (name, BirthYear, Alias)"
                    + " VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, item.getName(), item.getBirthYear(), item.getAlias());
        }
    }

    public void delete(int itemId) {
        String sql = "DELETE FROM authors WHERE id=?";
        jdbcTemplate.update(sql, itemId);
    }

    public BookAuthor get(int itemId) {
        String sql = "SELECT * FROM authors WHERE id=" + itemId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<BookAuthor>() {

            public BookAuthor extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    return getBookAuthorFromDB(rs);
                }
                return null;
            }
        });
    }

    public List<BookAuthor> list() {
        String sql = "SELECT * FROM authors";
        List<BookAuthor> listBookAuthor = jdbcTemplate.query(sql, new RowMapper<BookAuthor>() {

            public BookAuthor mapRow(ResultSet rs, int i) throws SQLException {

                return getBookAuthorFromDB(rs);
            }
        });
        return listBookAuthor;
    }

    private BookAuthor getBookAuthorFromDB(ResultSet rs) throws SQLException{
        BookAuthor brand = new BookAuthor();
        brand.setId(rs.getInt("id"));
        brand.setName(rs.getString("name"));
        brand.setBirthYear(rs.getShort("BirthYear"));
        brand.setAlias(rs.getString("Alias"));
        return brand;
    }
}

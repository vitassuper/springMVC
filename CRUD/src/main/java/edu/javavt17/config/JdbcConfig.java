package edu.javavt17.config;

import edu.javavt17.dao.BookAuthorDAO;
import edu.javavt17.dao.BookDAO;
import edu.javavt17.dao.jdbc.BookAuthorDAOJdbcImpl;
import edu.javavt17.dao.jdbc.BookDAOJdbcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public BookAuthorDAO getBookAuthorJdbcDAO() {
        return new BookAuthorDAOJdbcImpl(dataSource);
    }
    @Bean
    public BookDAO getBookJdbcDAO() {
        return new BookDAOJdbcImpl(dataSource);
    }
}

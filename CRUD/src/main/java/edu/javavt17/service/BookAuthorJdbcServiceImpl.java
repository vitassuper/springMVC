package edu.javavt17.service;

import edu.javavt17.dao.BookAuthorDAO;
import edu.javavt17.model.BookAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("bookAuthorJdbcService")
public class BookAuthorJdbcServiceImpl implements BookAuthorService {
    @Autowired
    @Qualifier("getBookAuthorJdbcDAO")
    private BookAuthorDAO bookAuthorDAO;

    public void saveOrUpdate(BookAuthor item) {
        bookAuthorDAO.saveOrUpdate(item);
    }

    public void delete(int itemId) {
        bookAuthorDAO.delete(itemId);
    }

    public BookAuthor get(int itemId) {
        return bookAuthorDAO.get(itemId);
    }

    public List<BookAuthor> list() {
        return bookAuthorDAO.list();
    }
}

package edu.javavt17.service;

import edu.javavt17.dao.BookDAO;
import edu.javavt17.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("bookJdbcService")
public class BookJdbcServiceImpl implements BookService {
    @Autowired
    @Qualifier("getBookJdbcDAO")
    private BookDAO bookDAO;

    public void saveOrUpdate(Book item) {
        bookDAO.saveOrUpdate(item);
    }

    public void delete(int itemId) {
        bookDAO.delete(itemId);
    }

    public Book get(int itemId) {
        return bookDAO.get(itemId);
    }

    public List<Book> list() {
        return bookDAO.list();
    }
}

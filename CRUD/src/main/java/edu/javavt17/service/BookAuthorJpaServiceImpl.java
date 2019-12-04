package edu.javavt17.service;

import edu.javavt17.dao.BookAuthorDAO;
import edu.javavt17.model.BookAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("bookAuthorJpaService")
@Transactional(readOnly=false, value = "jpaTransactionManager")
public class BookAuthorJpaServiceImpl implements BookAuthorService {
    @Autowired
    @Qualifier("getBookAuthorJpaDAO")
    private BookAuthorDAO bookAuthorDAO;

    public List<BookAuthor> list() {
        return bookAuthorDAO.list();
    }

    public BookAuthor get(int itemId) {
        return bookAuthorDAO.get(itemId);
    }

    public void saveOrUpdate(BookAuthor item) {
        bookAuthorDAO.saveOrUpdate(item);
    }

    public void delete(int itemId) {
        bookAuthorDAO.delete(itemId);
    }
}

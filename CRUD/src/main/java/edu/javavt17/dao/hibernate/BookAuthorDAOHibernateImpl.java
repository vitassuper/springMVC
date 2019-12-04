package edu.javavt17.dao.hibernate;

import edu.javavt17.dao.BookAuthorDAO;
import edu.javavt17.model.BookAuthor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookAuthorDAOHibernateImpl implements BookAuthorDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void saveOrUpdate(BookAuthor item) {
        if (item.getId() > 0) {
            //update
            getCurrentSession().merge(item);
        } else {
            //insert
            getCurrentSession().save(item);
        }

    }

    public void delete(int itemId) {
        BookAuthor bookAuthor = get(itemId);
        if (bookAuthor != null)
            getCurrentSession().delete(bookAuthor);
    }

    public BookAuthor get(int itemId) {
        BookAuthor bookAuthor = (BookAuthor) getCurrentSession().get(BookAuthor.class, itemId);

        return bookAuthor;
    }

    public List<BookAuthor> list() {
        Criteria criteria = getCurrentSession().createCriteria(BookAuthor.class);

        return criteria.list();
    }
}

package edu.javavt17.dao.hibernate;

import edu.javavt17.dao.BookDAO;
import edu.javavt17.model.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookDAOHibernateImpl implements BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void saveOrUpdate(Book item) {
        if (item.getIdBook() > 0) {
            getCurrentSession().merge(item);
        } else {
            getCurrentSession().save(item);
        }

    }

    public void delete(int itemId) {
        Book book = get(itemId);
        if (book != null)
            getCurrentSession().delete(book);
    }

    public Book get(int itemId) {
        Book book = (Book) getCurrentSession().get(Book.class, itemId);

        return book;
    }

    public List<Book> list() {
        Criteria criteria = getCurrentSession().createCriteria(Book.class);

        return criteria.list();
    }
}
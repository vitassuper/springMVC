package edu.javavt17.dao.jpa;

import edu.javavt17.dao.BookDAO;
import edu.javavt17.model.Book;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookDAOJpaImpl implements BookDAO {
    @PersistenceContext
    private EntityManager em;

    public void saveOrUpdate(Book item) {
        if (item.getIdAuthor() > 0) {
            // update
            em.merge(item);
        } else {
            // insert
            em.persist(item);
        }
    }

    public void delete(int itemId) {
        em.remove(get(itemId));
    }

    public Book get(int itemId) {
        return em.find(Book.class, itemId);
    }

    public List<Book> list() {
        List<Book> books = em.createNamedQuery("Book.findAll", Book.class).getResultList();
        return books;
    }
}

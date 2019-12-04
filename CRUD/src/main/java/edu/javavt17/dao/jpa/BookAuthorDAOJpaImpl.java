package edu.javavt17.dao.jpa;

import edu.javavt17.dao.BookAuthorDAO;
import edu.javavt17.model.BookAuthor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookAuthorDAOJpaImpl implements BookAuthorDAO {
    @PersistenceContext
    private EntityManager em;

    public void saveOrUpdate(BookAuthor item) {
        if (item.getId() > 0) {
            //update
            em.merge(item);
        } else {
            //insert
            em.persist(item);
        }
    }

    public void delete(int itemId) {
        em.remove(get(itemId));

    }

    public BookAuthor get(int itemId) {
        return em.find(BookAuthor.class, itemId);
    }

    public List<BookAuthor> list() {
        List<BookAuthor> bookAuthors = em.createNamedQuery("BookAuthor.findAll", BookAuthor.class).getResultList();
        return bookAuthors;
    }
}

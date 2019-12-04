package edu.javavt17.service;

import edu.javavt17.model.BookAuthor;
import java.util.List;

public interface BookAuthorService {
    void saveOrUpdate(BookAuthor item);

    void delete(int itemId);

    BookAuthor get(int itemId);

    List<BookAuthor> list();
}
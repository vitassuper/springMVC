package edu.javavt17.dao;

import edu.javavt17.model.BookAuthor;

import java.util.List;

public interface BookAuthorDAO {

    void saveOrUpdate(BookAuthor item);

    void delete(int itemId);

    BookAuthor get(int itemId);

    List<BookAuthor> list();
}

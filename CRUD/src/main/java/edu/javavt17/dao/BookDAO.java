package edu.javavt17.dao;

import edu.javavt17.model.Book;

import java.util.List;

public interface BookDAO {

    void saveOrUpdate(Book item);

    void delete(int itemId);

    Book get(int itemId);

    List<Book> list();

}

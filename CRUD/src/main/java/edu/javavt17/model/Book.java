package edu.javavt17.model;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="books")
@NamedQuery(name="Book.findAll", query="select b from Book b")

public class Book implements Serializable {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(insertable = false, updatable = false)
    private int AuthorId;

    @ManyToOne
    @JoinColumn(name = "AuthorId")
    private BookAuthor bookAuthor;

    @NotEmpty @Column
    private String name;
    @Column private String Description;
    @Column private short FoundedYear;

    public Book() {}

    public int getIdBook() {
        return id;
    }

    public int getIdAuthor() {
        return AuthorId;
    }

    public BookAuthor getBookAuthor() {
        return bookAuthor;
    }

    public String getDescription(){return Description;}

    public short getFoundedYear() {
        return FoundedYear;
    }

    public String getBookName(){return name;}

    public void setId(int id) {
        this.id = id;
    }

    public void setIdAuthor(int idAuthor) {
        this.AuthorId = idAuthor;
    }

    public void setFoundedYear(short FoundedYear){this.FoundedYear=FoundedYear;}

    public void setBookAuthor(BookAuthor bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookName(String name) {
        this.name = name;
    }

    public void setDescription(String Description){this.Description=Description;}


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", BookAuthor=" + bookAuthor.getName() +
                ", AuthorId=" + AuthorId +
                ", bookName='" + name + '\'' +
                ", productionYear=" + FoundedYear +
                '}';
    }
}

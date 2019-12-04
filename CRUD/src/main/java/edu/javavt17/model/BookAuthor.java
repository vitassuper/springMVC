package edu.javavt17.model;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authors")
@NamedQuery(name = "BookAuthor.findAll", query = "select c from BookAuthor c")

public class BookAuthor implements Serializable{
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @NotEmpty @Column(unique=true, nullable=false)
    private String name;

    @Column private short BirthYear;

    @Column private String Alias;

    public BookAuthor() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthYear(short BirthYear) {
        this.BirthYear = BirthYear;
    }

    public void setAlias(String Alias) {
        this.Alias = Alias;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public short getBirthYear() {
        return BirthYear;
    }

    public String getAlias() {
        return Alias;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", BirthYear=" + BirthYear +
                ", Alias='" + Alias + '\'' +
                '}';
    }

}

package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    public Category() {
    }

    public Category(String name) {

        this.name = name;
    }

    public Set<Book> getBooks() {

        return books;
    }

    public long getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public void setBooks(Set<Book> books) {

        this.books = books;
    }

    public void setId(long id) {

        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<Book>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long id;

    private String name;
}

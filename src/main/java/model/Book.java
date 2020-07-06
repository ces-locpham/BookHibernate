package model;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;
    private String name;
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private Set<Author> authors = new HashSet<Author>();
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthor(Set<Author> author) {
        this.authors = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + authors +
                ", category=" + category +
                '}';
    }
}

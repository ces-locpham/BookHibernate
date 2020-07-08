package model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {

    public Book() {

    }

    public Set<Author> getAuthors() {

        return authors;
    }

    public Category getCategory() {

        return category;
    }

    public String getName() {

        return name;
    }

    public long getId() {

        return id;
    }

    public void setAuthor(Set<Author> author) {

        this.authors = author;
    }

    public void setCategory(Category category) {

        this.category = category;
    }

    public void setId(long id) {

        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", author=" + this.authors +
                ", category=" + this.category +
                '}';
    }

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private Set<Author> authors = new HashSet<Author>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long id;

    private String name;
}

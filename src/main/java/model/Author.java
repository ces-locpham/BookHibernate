package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {

    public Author() {

    }

    public Author(String name) {

        this.name = name;
    }

    public void addBook(Book book) {

        books.add(book);
        book.getAuthors().add(this);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {

            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {

            return false;
        }
        Author author = (Author) obj;
        return this.id == author.getId();
    }

    public String getName() {

        return name;
    }

    public Set<Book> getBooks() {

        return books;
    }

    public long getId() {

        return id;
    }

    @Override
    public int hashCode() {

        return (this.getId() + this.getName()).hashCode();
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.getAuthors().remove(this);
    }

    public void setBooks(Set<Book> books) {

        this.books = books;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setId(long id) {

        this.id = id;
    }

    @Override
    public String toString() {

        return "Author{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", books=" + this.books +
                '}';
    }

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.DETACH,
                    CascadeType.REFRESH,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<Book>();

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long id;


}

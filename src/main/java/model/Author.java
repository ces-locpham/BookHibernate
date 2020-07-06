package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long id;
    private String name;
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.DETACH,
                    CascadeType.REFRESH,
                    CascadeType.REMOVE,
            },
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name="books_authors",
            joinColumns = @JoinColumn(name="author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<Book>();

    public Author() {
    }
    public Author(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
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
    public void addBook(Book book) {
        books.add(book);
    }
    public void removeBook(Book book) {
        books.remove(book);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        Author author =(Author) obj;
        return this.id == author.getId();
    }

    @Override
    public int hashCode() {
        return (this.getId() + this.getName()).hashCode();
    }
}

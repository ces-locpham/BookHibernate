package DAO;


import model.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class BookDAO {
    public List<Book> getBookList() {
        Transaction transaction = null;
        List<Book> books = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            books = session.createQuery("from Book ").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        if (books == null) {
            throw new Error("Book is empty");
        }
        return books;
    }

    public Book getBook(long id) {
        Transaction transaction = null;
        Book book = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            book = (Book) session.get(Book.class, id);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        if (book == null) {
            throw new Error("Server cannot find this book id");
        }
        return book;
    }

    public void addBook(Book book) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new Error("Error when adding book");
        }
        return;
    }

    public Book updateBook(Book book) {
        Transaction transaction = null;
        Book updatedBook = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            updatedBook = (Book) session.merge(book);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new Error("Error when update book");
        }
        return updatedBook;
    }

    public void deleteBook(Book book) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(book);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new Error("Error when adding book");
        }
    }
}

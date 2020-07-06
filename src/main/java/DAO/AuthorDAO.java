package DAO;

import model.Author;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class AuthorDAO {
    @SuppressWarnings("unchecked")
    public List<Author> getAllAuthors() {
        Transaction transaction = null;
        List<Author> listOfAuthors = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            listOfAuthors = session.createQuery("from Author ",Author.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        if (listOfAuthors == null) {
            throw new Error("Author is empty");
        }
        return listOfAuthors;
    }

    public Author getAuthor(long id) {
        Transaction transaction = null;
        Author author = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            author = (Author) session.get(Author.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        if (author == null) {
            throw new Error("Server cannot find this author id");
        }
        return author;
    }

    public void addAuthor(Author author) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(author);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new Error("Error when adding author");
        }
    }

    public Author updateAuthor(Author author) {
        Transaction transaction = null;
        Author updatedAuthor = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            updatedAuthor = (Author) session.merge(author);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new Error("Error when adding author");
        }
        return updatedAuthor;
    }

    public void deleteAuthor(Author author) {
        Transaction transaction = null;
        Author deletedAuthor = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(author);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new Error("Error when adding author");
        }
    }

}

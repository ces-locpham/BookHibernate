package DAO;

import model.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class CategoryDAO {

    public void addCategory(Category category) {

        Transaction transaction = null;
        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {

                transaction.rollback();
            }
            e.printStackTrace();
            throw new Error("Server cannot add " + Category.class);
        }
    }

    public void deleteCategory(Category category) {

        Transaction transaction = null;
        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(category);
            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {

                transaction.rollback();
            }
            e.printStackTrace();
            throw new Error("Error when delete category");
        }
    }

    @SuppressWarnings("unchecked")
    public List<Category> getAllCategories() {

        Transaction transaction = null;
        List<Category> categories = null;
        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            categories = session.createQuery("from Category ").list();
            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {

                transaction.rollback();
            }
            e.printStackTrace();
        }
        if (categories == null) {

            throw new Error("Category is empty");
        }
        return categories;
    }

    public Category getCategory(long id) {

        Transaction transaction = null;
        Category category = null;
        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            category = (Category) session.get(Category.class, id);
            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {

                transaction.rollback();
            }
            e.printStackTrace();
        }
        if (category == null) {

            throw new Error("Server cannot find this " + Category.class + " id");
        }
        return category;
    }

    public Category updateCategory(Category category) {

        Transaction transaction = null;
        Category updatedCategory = null;
        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            updatedCategory = (Category) session.merge(category);
            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {

                transaction.rollback();
            }
            e.printStackTrace();
            throw new Error("Error when adding category");
        }
        return updatedCategory;
    }
}

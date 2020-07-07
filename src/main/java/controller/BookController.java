package controller;

import DAO.AuthorDAO;
import DAO.BookDAO;
import DAO.CategoryDAO;
import model.Author;
import model.Book;
import model.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = {"/book", "/book/delete", "/book/showAdd", "/book/add","/book/showEdit","/book/edit"})
public class BookController extends HttpServlet {
    private AuthorDAO authorDAO = new AuthorDAO();
    private BookDAO bookDAO = new BookDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    private List<Author> listOfAuthors = new ArrayList<>();
    private List<Category> listOfCategories   = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        listOfCategories =  categoryDAO.getAllCategories();
        listOfAuthors = authorDAO.getAllAuthors();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dialBook(req, resp);
    }

    private void dialBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getServletPath();
        switch (action) {
            case "/book/delete":
                deleteBook(req, resp);
                break;
            case "/book/showAdd":
                showAdd(req, resp);
                break;
            case "/book/add":
                addBook(req, resp);
                break;
            case "/book/showEdit":
                showEdit(req, resp);
                break;
            case "/book/edit":
                editBook(req, resp);
                break;
            default:
                listBook(req, resp);
                break;
        }
    }

    private void editBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        String name = req.getParameter("name");
        String[] authorIds = req.getParameterValues("authors");
        String categoryId = req.getParameter("category");
        Book updatedBook = bookDAO.getBook(Long.valueOf(id));
        Category category = categoryDAO.getCategory(Long.valueOf(categoryId));
        updatedBook.setName(name);
        updatedBook.setCategory(category);
        bookDAO.updateBook(updatedBook);
        // adding book in author
        Set<Author> selectedAuthors = authorDAO.getAuthorsByStringArray(authorIds);
        Set<Author> authors = new HashSet<>();
        authors.addAll(selectedAuthors);
        authors.addAll(updatedBook.getAuthors());
        Set<Author> removedBookAuthor =  updatedBook.getAuthors();
        removedBookAuthor.removeAll(selectedAuthors);
        for (Author author:authors) {
            if (removedBookAuthor.contains(author)) {
                author.removeBook(updatedBook);
            } else {
                author.addBook(updatedBook);
            }
            authorDAO.updateAuthor(author);
        }
        resp.sendRedirect("/book");
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookDAO.getBook(Long.valueOf(id));
        req.setAttribute("authors", this.listOfAuthors);
        req.setAttribute("categories", this.listOfCategories);
        req.setAttribute("book", book);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/FormBook.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void addBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String[] authorsId = req.getParameterValues("authors");
        String categoryId = req.getParameter("category");
        Category category = categoryDAO.getCategory(Long.valueOf(categoryId));
        Book newBook = new Book();
        newBook.setName(name);
        newBook.setCategory(category);
        bookDAO.addBook(newBook);
        for (int i = 0; i < authorsId.length; i++) {
            Author author = authorDAO.getAuthor(Long.valueOf(authorsId[i]));
            author.addBook(newBook);
            authorDAO.updateAuthor(author);
        }
        resp.sendRedirect("/book");
    }

    private void showAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("authors", this.listOfAuthors);
        req.setAttribute("categories", this.listOfCategories);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/FormBook.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void listBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("ListBook.jsp");
        List<Book> books = bookDAO.getBookList();
        req.setAttribute("listBook", books);
        requestDispatcher.forward(req, resp);
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long id = Long.valueOf(req.getParameter("id"));
        Book deletedBook = bookDAO.getBook(id);
        Set<Author> listAuthors = deletedBook.getAuthors();
        for (Author author : listAuthors) {
            author.removeBook(deletedBook);
            authorDAO.updateAuthor(author);
        }
        bookDAO.deleteBook(deletedBook);
        resp.sendRedirect("/book");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

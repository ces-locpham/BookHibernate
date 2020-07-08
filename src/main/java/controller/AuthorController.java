package controller;

import DAO.AuthorDAO;
import model.Author;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/author/showAdd", "/author/add"})
public class AuthorController extends HttpServlet {
    private AuthorDAO authorDAO = new AuthorDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dialAuthor(req, resp);
    }

    private void dialAuthor(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getServletPath();
        switch (action) {
            case "/author/showAdd":
                showAdd(req, resp);
                break;
            case "/author/add":
                addAuthor(req, resp);
                break;
        }
    }

    private void showAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/FormAuthor.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void addAuthor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        Author newAuthor = new Author();
        newAuthor.setName(name);
        authorDAO.addAuthor(newAuthor);
        resp.sendRedirect("/book");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dialAuthor(req, resp);
    }
}

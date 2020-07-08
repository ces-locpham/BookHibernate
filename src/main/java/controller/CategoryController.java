package controller;

import DAO.CategoryDAO;
import model.Author;
import model.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/category/showAdd", "/category/add"})
public class CategoryController extends HttpServlet {

    private void addCategory(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String name = req.getParameter("name");
        Category newCategory = new Category();
        newCategory.setName(name);
        categoryDAO.addCategory(newCategory);
        resp.sendRedirect("/book");
    }

    private void dialCategory(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String action = req.getServletPath();
        switch (action) {
            case "/category/showAdd":
                showAdd(req, resp);
                break;
            case "/category/add":
                addCategory(req, resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        dialCategory(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        dialCategory(req, resp);
    }

    private void showAdd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/FormCategory.jsp");
        requestDispatcher.forward(req, resp);
    }

    private CategoryDAO categoryDAO = new CategoryDAO();
}

package wap.news.controller;

import wap.news.dao.CategoryDao;
import wap.news.model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddCategoryServlet", value = "/add-category")
public class AddCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        boolean isActive = false;
        if (request.getParameter("isActive").toLowerCase().equals("on")) {
            isActive = true;
        }

        CategoryDao dao = new CategoryDao();
        dao.addCategory(name, isActive);
        HttpSession session = request.getSession();
        session.setAttribute("categoryList", dao.getAllCategories());
        response.sendRedirect("backend/category.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

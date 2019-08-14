package wap.news.controller;

import wap.news.dao.ArticleDao;
import wap.news.dao.CategoryDao;
import wap.news.model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UpdateArticleServlet", value = "/update-article")
public class UpdateArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String mainImage = request.getParameter("mainImage");
        int categoryId = 0;
        if (request.getParameter("categoryId") != null) {
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
        }

        boolean isActive = false;
        boolean isNaveBar = false;
        boolean isRotating = false;

        if (request.getParameter("isNaveBar").toLowerCase().equals("on")) {
            isNaveBar = true;
        }
        if (request.getParameter("isRotating").toLowerCase().equals("on")) {
            isRotating = true;
        }
        if (request.getParameter("isActive").toLowerCase().equals("on")) {
            isActive = true;
        }

        ArticleDao dao = new ArticleDao();
        dao.updateArticle(id, title, body, categoryId, mainImage, isNaveBar, isRotating, isActive);
        response.sendRedirect("article");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleDao dao = new ArticleDao();
        HttpSession session = request.getSession();
        session.setAttribute("articleById", dao.getArticleById(request.getParameter("id")));
        List<Category> listCatagory = new ArrayList<>(new CategoryDao().getActiveCategories().values());
        session.setAttribute("categoryList", listCatagory);
        response.sendRedirect("backend/article.jsp?update=true");
    }
}

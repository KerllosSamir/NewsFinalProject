package wap.news.controller;

import wap.news.dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)
@WebServlet(name = "AddArticleServlet", value = "/add-article")
public class AddArticleServlet extends HttpServlet {
    private static final String SAVE_DIR="articleimages";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        if (request.getParameter("isNaveBar") != null) {
            isNaveBar = true;
        }
        if (request.getParameter("isRotating") != null) {
            isRotating = true;
        }
        if (request.getParameter("isActive") != null) {
            isActive = true;
        }

        ArticleDao dao = new ArticleDao();
        dao.addArticle(title, body, categoryId, mainImage, isNaveBar, isRotating, isActive);
        HttpSession session = request.getSession();
        session.setAttribute("articleList", dao.getAllArticles());
        response.sendRedirect("backend/article.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

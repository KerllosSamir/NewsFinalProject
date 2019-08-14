package wap.news.controller;

import wap.news.dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)
@WebServlet(name = "AddArticleServlet", value = "/add-article")
public class AddArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get the file chosen by the user
        Part filePart = request.getPart("mainImage");
        //get the InputStream to store the file somewhere
        InputStream fileInputStream = filePart.getInputStream();

        File fileToSave = new File(request.getSession().getServletContext().getRealPath("articleImages") + "/" + filePart.getSubmittedFileName());
        Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

        //get the URL of the uploaded file
        String fileUrl = "articleImages/" + filePart.getSubmittedFileName();

        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String mainImage = fileUrl;
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

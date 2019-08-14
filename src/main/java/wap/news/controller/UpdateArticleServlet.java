package wap.news.controller;

import wap.news.dao.ArticleDao;
import wap.news.dao.CategoryDao;
import wap.news.model.Category;

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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)
@WebServlet(name = "UpdateArticleServlet", value = "/update-article")
public class UpdateArticleServlet extends HttpServlet {
    private static final String SAVE_DIR = "articleimages";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get the file chosen by the user
        Part filePart = request.getPart("mainImage");
        //get the InputStream to store the file somewhere
        InputStream fileInputStream = filePart.getInputStream();

        File fileToSave = new File(request.getSession().getServletContext().getRealPath("articleimages") + "/" + filePart.getSubmittedFileName());
        Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

        //get the URL of the uploaded file
        String fileUrl = "articleimages/" + filePart.getSubmittedFileName();

        String mainImage = fileUrl;

        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String body = request.getParameter("body");

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

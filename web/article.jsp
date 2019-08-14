<%--
  Created by IntelliJ IDEA.
  User: Kerll
  Date: 8/14/2019
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${farticleById.title}</title>
</head>
<body>
<div class="single_page">
    <ol class="breadcrumb">
        <li><a href="../index.html">Home</a></li>
        <li><a href="#">Technology</a></li>
        <li class="active">Mobile</li>
    </ol>
    <h1>${articleById.title}</h1>
    <div class="post_commentbox"><a href="#"><i class="fa fa-user"></i>Wpfreeware</a> <span><i
            class="fa fa-calendar"></i>6:49 AM</span> <a href="#"><i class="fa fa-tags"></i>Technology</a></div>
    <div class="single_page_content"><img class="img-center" src="${farticleById.mainImage}" alt="">
        <p>${farticleById.body}</p>

    </div>
</div>
</div>
</body>
</html>

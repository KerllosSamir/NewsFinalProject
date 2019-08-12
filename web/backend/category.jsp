<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Category</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <c:choose>
    <c:when test="${param.update == 'true'}">
    <h2>Update category information</h2>
    <form method="post" action="update-category" class="form-inline">
        <c:forEach items="${contactById}" var="val">
            <input type="hidden" name="id" value="${val.key}">
            <div class="form-group">
                <label for="uname">Name:</label>
                <input type="text" class="form-control" id="uname" name="name" value="${val.value.name}">
            </div>
            <div class="form-group">
                <label for="uisActive">Is active:</label>
                <input type="checkbox" class="form-control" id="uisActive" name="isActive"
                       value="${val.value.isActive}">
            </div>
            <button type="submit" class="btn btn-default" value="Update">Update</button>
        </c:forEach>
    </form>
    </c:when>

    <c:otherwise>
    <h2>Add new category information</h2>
    <form method="post" action="add-category" class="form-inline">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="Enter name">
        </div>
        <div class="checkbox">
            <label><input type="checkbox" id="isActive" name="isActive"> Is active:</label>
        </div>

        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    </c:otherwise>
    </c:choose>


    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Is active</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${categoryList}" var="val">
            <tr>
                <td><c:out value="${val.value.name}"/></td>
                <td><c:out value="${val.value.isActive}"/></td>
                <td><a href="update-category?id=${val.key}"
                       class="btn btn-info" role="button">Update</a></td>
                <td><a href="delete-category?id=${val.key}"
                       class="btn btn-info" role="button">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</body>
</html>

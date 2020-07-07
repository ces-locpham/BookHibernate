<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book</title>
</head>
<body>
<div align="center">
    <h1>Book Management</h1>
    <h2>
        <a href="/book/showAdd">Add new book</a>
    </h2>
    <h2>
        <a href="/category' /> ">Add new category</a>
    </h2>
    <h2>
        <a href="/author/showAdd">Add new author</a>
    </h2>
    <table cellpadding="4" border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Author</th>
            <th>Category</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${listBook}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>
                    <c:forEach items="${book.getAuthors()}" var="author">
                        ${author.name} &nbsp;
                    </c:forEach>
                </td>
                <td>${book.getCategory().name}</td>
                <td>
                    <a href="/book/showEdit?id=<c:out value='${book.id}'/> ">Edit</a>
                    &nbsp;
                    <a href="/book/delete?id=<c:out value='${book.id}'/> ">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

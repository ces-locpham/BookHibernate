<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Book Management</title>
</head>
<body>
<div align="center">
    <h1>
        <c:if test="${book==null}">Add</c:if>
        <c:if test="${book!=null}">Update</c:if>
        &nbsp; Management
    </h1>
    <c:if test="${book==null}">
        <form action="/book/add" method="post">
    </c:if>
    <c:if test="${book!=null}">
        <form action="/book/edit?id=<c:out value='${book.id}' />" method="post">
    </c:if>

            <table cellpadding="2" border="1">
                <tr>
                    <td>Name</td>
                    <td><input size="50" type="text" name="name" value="${book.name}"/></td>
                </tr>
                <tr>
                    <td>Author</td>
                    <td>
                        <select name="authors" id="authors" size="4" multiple required>
                            <c:forEach items="${authors}" var="author">
                                <c:choose>
                                    <c:when test="${book.getAuthors().contains(author) == true}">
                                        <option value="${author.id}" selected>${author.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${author.id}" >${author.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>

                </tr>
                <tr>
                    <td>Category</td>
                    <td>
                        <select name="category" id="category" required>
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.id}"
                                        selected="${book.category.id.equals(category.id)}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </td>

                </tr>
            </table>
            <input type="submit" value="Save">
        </form>

</div>

</body>
</html>

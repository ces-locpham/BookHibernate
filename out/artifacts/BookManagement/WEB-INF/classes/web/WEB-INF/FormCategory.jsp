<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Category Management</title>
</head>
<body>
<div align="center">
    <h1>
        Add Category
    </h1>
    <form action="/category/add" method="post">
        <table cellpadding="2" border="1">
            <tr>
                <td>Name</td>
                <td><input size="50" type="text" name="name"/></td>
            </tr>
        </table>
        <input type="submit" value="Save">
    </form>

</div>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html xmlns:c="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Top Java.</title>
</head>
<body>
<h2>Проект "<a href="https://github.com/JavaWebinar/topjava06" target="_blank">Top Java"</a></h2>
<form method="post" action="startpage">
    <label>User:
        <select id="user" name="userId">
            <c:forEach var="user" items="${users}">
                <jsp:useBean id="user" scope="page" type="ru.javawebinar.topjava.model.User"/>
                <option value="${user.id}" ${user.id == loggedUserId ? 'selected="selected"' : ''}>${user.name}</option>
            </c:forEach>
        </select>
    </label>
    <button type="submit" value="submit" name="Submit">Login</button>
    <br>
</form>
<hr>
<ul>
<li><a href="users">User List</a></li>
<li><a href="meals">Meal List</a></li>
</ul>
</body>
</html>

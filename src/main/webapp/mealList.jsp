<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.javawebinar.topjava.model.UserMealWithExceed" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals list</title>
    <link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>
<h3>Meal list</h3>
<a href="meals?action=create">Add meal</a>
<form method="post" action="meals">
<table>
    <th>Description</th>
    <th>Calories</th>
    <th>Date</th>
    <th></th> <!-- Колонка обновления еды из списка-->
    <th></th> <!-- Колонка удаления еды из списка-->
    <c:forEach var="meal" items="${mealListWithExceeded}">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMealWithExceed"/>
        <tr class= ${meal.exceed ? "exceed" : "notExceed"} >
            <%--<fmt:parseDate var="parseDate" value="${meal.dateTime}" pattern="dd MMM yyyy HH:mm" />--%>
                    <%--<td>${meal.formattedDateTime}</td>--%>
                <td><%= TimeUtil.toString(meal.getDateTime())%></td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
    </tr>
</c:forEach>
</table>
</form>
</body>
</html>

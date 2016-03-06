<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.javawebinar.topjava.model.UserMealWithExceed" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals list</title>
    <link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>
<h2>Meal list</h2>
<table>
    <th>Description</th>
    <th>Calories</th>
    <th>Date</th>
    <c:forEach var="meal" items="${mealListWithExceeded}">
        <c:if test="${meal.exceed}">
        <tr class="exceed">
        </c:if>
        <c:if test="${meal.exceed ne true}">
            <tr class="notExceed">
        </c:if>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>${meal.getFormattedDateTime()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

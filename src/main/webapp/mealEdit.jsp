<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal</title>
    <link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>
<section>
<h2><a href="">Home</a> </h2>
<h3>Edit meal</h3>
<hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.UserMeal" scope="request"/>
    <form method="post" action="meals">
    <input type="hidden" name="id" value="${meal.id}">
    <dl>
        <dt>DateTime:</dt>
        <dd><input type="datetime-local" name="dateTime" value="${meal.dateTime}"></dd>
    </dl>
    <dl>
        <dt>descrition:</dt>
        <dd><input type="text" name="description" value="${meal.description}"></dd>
    </dl>
    <dl>
        <dt>Calories:</dt>
        <dd><input type="number" name="calories" value="${meal.calories}"></dd>
    </dl>
    <button type="submit">Save</button>
    <button onclick="windows.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>

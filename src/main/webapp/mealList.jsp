<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="style.css" media="all" />
</head>
<body>
<section>
    <h2><a href="startpage">Home</a></h2>
    <h3>Meal list</h3>
    <%-- Filter by Date & Time --%>
    <form method="get" action="meals?action=filter">
        <input type="hidden" name="action" value="filter">
        <div class="form-group">
            <div class="control-label">From Date:</div>
            <div class="col-sm-2"><input type="date" value="<%= request.getParameter("fromDate") %>" name="fromDate"></div>
            <div class="control-label">To Date:</div>
            <div class="col-sm-2"><input type="date" value="<%= request.getParameter("toDate") %>" name="toDate"></div>
        </div>
        <div class="form-group">
            <div class="control-label">From Time:</div>
            <div class="col-sm-2"><input type="time" value="<%= request.getParameter("fromTime") %>" name="fromTime"></div>
            <div class="control-label">From Time:</div>
            <div class="col-sm-2"><input type="time" value="<%= request.getParameter("toTime") %>" name="toTime"></div>
        </div>
        <button type="submit">Filter</button>
    </form>
    <%-- --------------------- --%>

    <a href="meals?action=create">Add Meal</a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.UserMealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        ${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
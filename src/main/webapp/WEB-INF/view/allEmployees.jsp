<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Список работников</title>
</head>
<body>
<h2>Список всех работников на проекте</h2>

<table class="table table-bordered table-hover horizontal-align">
    <thead>
    <tr>
        <th style="width: 5%" bgcolor="#5f9ea0">ID</th>
        <th style="width: 30%" bgcolor="#5f9ea0">Имя</th>
        <th style="width: 30%" bgcolor="#5f9ea0">Фамилия</th>
        <th style="width: 8%" bgcolor="#5f9ea0">Роль</th>
    </tr>
    </thead>

    <c:forEach var="employee" items="${requestScope.employees}">
        <tbody>
        <td><c:out value="${employee.id}"/></td>
        <td><c:out value="${employee.firstName}"/></td>
        <td><c:out value="${employee.lastName}"/></td>
        <td><c:out value="${employee.role}"/></td>
        </tbody>
    </c:forEach>
</table>
<p><a href="add-employees">Add Employee</a></p>
</body>
</html>

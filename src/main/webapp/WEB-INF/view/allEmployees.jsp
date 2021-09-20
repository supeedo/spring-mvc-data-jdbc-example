<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.study.model.EmployeeDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Список работников</title>
</head>
<body>
<h2>Список всех работников на проекте</h2>

<c:forEach var="employee" items="${requestScope.employees}">
    <ul>

        ID:  <c:out value="${employee.id}"/>
        ФИО:  <c:out value="${employee.firstName}"/>
        Роль:  <c:out value="${employee.role}"/>

    </ul>
</c:forEach>

</body>
</html>

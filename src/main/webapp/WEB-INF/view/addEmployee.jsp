<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add new Employee</title>
</head>
<body>

<c:forEach var="error" items="${requestScope.errorsEmp}">
    <tbody>
    <c:if test="${error != null}">
    <td>
        <p style="color:red;size: 5px">  <c:out value="${error}"/><p>
    </td>
    </c:if>
    </tbody>
</c:forEach>

<fieldset>
    <form method="POST" action='add-employees' name="addEmployee" >
        First Name : <label>
        <input type="text" name="firstName"/>
    </label> <br/>

        Last Name : <label>
        <input type="text" name="lastName" />
    </label> <br/>

        Role : <label>
        <input type="text" name="role"/>
    </label> <br/>

        <input type="submit" value="Submit"/>
    </form>
</fieldset>
</body>
</html>

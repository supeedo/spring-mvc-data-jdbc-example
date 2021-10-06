<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add new Employee</title>
</head>
<body>
<fieldset>
    <form method="POST" action='add-employees' name="addEmployee" >
        First Name : <label>
        <input type="text" name="firstName"/>
    </label> <br/>
        <c:set var="errorFN" scope="session" value="${errorFirstName}"/>
        <c:if test="${errorFN != null}">
        <p style="color:red;size: 5px"><c:out value="${errorFN}"/><p>
        </c:if>

        Last Name : <label>
        <input type="text" name="lastName" />
    </label> <br/>
            <c:set var="errorLN" scope="session" value="${errorLastName}"/>
            <c:if test="${errorFN != null}">
        <p style="color:red;size: 5px"><c:out value="${errorLN}"/><p>
        </c:if>

        Role : <label>
        <input type="text" name="role"/>
    </label> <br/>
            <c:set var="errorR" scope="session" value="${errorRole}"/>
            <c:if test="${errorFN != null}">
        <p style="color:red;size: 5px"><c:out value="${errorR}"/><p>
        </c:if>

        <input type="submit" value="Submit"/>
    </form>
</fieldset>
</body>
</html>

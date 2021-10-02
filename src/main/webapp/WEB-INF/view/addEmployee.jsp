<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new Employee</title>
</head>
<body>
<fieldset>
    <form method="POST" action='add-employees' name="addEmployee" >
        First Name : <input
            type="text" name="firstName"/> <br/>
        Last Name : <input
            type="text" name="lastName" /> <br/>
        Role : <input
            type="text" name="role"/> <br/>
        <input type="submit" value="Submit"/>
    </form>
</fieldset>
</body>
</html>

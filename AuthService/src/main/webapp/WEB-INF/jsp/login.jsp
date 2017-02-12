<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Login</h1>
<form:form action="/login" method="post" commandName="userRequest">
    <p>Login: <form:input path="login" /></p>
    <p>Password: <form:password path="password" /></p>
    <p><input type="submit" value="Submit"></p>
</form:form>

</body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

<h1>Register</h1>
<form:form action="/registration" method="post" commandName="userRequest">
    <p>Login: <form:input path="login" /></p>
    <p>Password: <form:password path="password" /></p>
    <p>First name: <form:input path="firstName" /></p>
    <p>Last name: <form:input path="lastName" /></p>
    <p><input type="submit" value="Submit"></p>
</form:form>

</body>
</html>

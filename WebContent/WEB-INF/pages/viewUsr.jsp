<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View User</title>
</head>
<body>	
	<h1>User Detail</h1>
	Employee id: ${user.id }<br/>
	User Name: ${user.userName }<br/>
	Email: ${user.email }<br/>
	password: ***** <br/>
	Birthdate: ${user.DOB}<br/>
	Register Date: ${user.regDate}<br/>
	<img src="${pageContext.request.contextPath}/resources/${user.img}" alt="No imgae"/>
	
</body>
</html>
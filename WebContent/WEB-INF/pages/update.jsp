
<%@page import="com.test.app.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String msg = "";
	if(request.getAttribute("usr")==null)
		{
			msg="false";
			return ;
		}
	
	User usr = (User)request.getAttribute("usr");
	
%>
<h4><%=msg %>::<%=usr.getRegDate() %></h4>

	<form action="/SpringBean/update-<%=usr.getId()%>" method="post">	
		User Name: <input type="text" name="userName" id="txtusername" value="<%=usr.getUserName()%>"/><br/>
		Email: <input type="text" name="email" id="txtemail" value="<%=usr.getEmail()%>"><br/>
		Password: <input type="password" name="passwd" id="txtpasswd" value="<%=usr.getPasswd()%>"/><br/>
		Birthdate: <input type="date" name="DOB" id="txtDOB" value="<%=usr.getDOB()%>"/><br/>
		RegisterDate: <input type="date" name="regDate" id="txtregDate" value="<%=usr.getRegDate()%>"/><br/>
		
		<img src="<%=usr.getImg()%>" alt="No Image"/>
		<input type="file" name="img" id="txtimg" value="Upload"/><br/>
		
		<input type="submit" value="Update Me!" id="btnupdate"/>			
	</form>
</body>
</html>
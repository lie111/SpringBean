<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/SpringBean/add" method="post" enctype="multipart/form-data">
		
		User Name: <input type="text" name="userName" id="txtusername"/><br/>
		Email: <input type="text" name="email" id="txtEmail"><br/>
		Password: <input type="password" name="passwd" id="txtpasswd"/><br>
		Birthdate: <input type="date" name="DOB" id="txtDOB"/><br/>
		RegisterDate: <input type="date" name="regDate" id="txtregDate"/><br/>
		
		<label for="img">Image (in JPEG format only):</label>
		Image: <input type="file" name="img" id="txtimg"/><br/>
				
		<input type="submit" value="Insert Me!" id="btnadd"/>		
	
	</form>
</body>
</html>
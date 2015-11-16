
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 style="text-align:center">Use Rest controller</h1>
	
	<div>
		<input style="float:left" type="button" value="Add" onclick="window.location='/SpringBean/add'"/>
		<input style="float:right" type="text" name="txtsearch" id="txtsearch">
		<input style="float:right" type="button" value="Search" onclick="window.location='/SpringBean/search'"/>
		
	</div>
	
	
<table border="1" width="100%">
	<tr>
	   <th>Emp ID</th>
	   <th>User Name</th>
	   <th>Email</th>
	   <th>password</th>
	   <th>birthdate</th>
	   <th>Registerdate</th>
	   <th>Image</th>
	   <th>Action</th>
	</tr>
	<c:forEach var="row" items="${users}">
	<tr>
	   <td><c:out value="${row.id}"/></td>
	   <td><c:out value="${row.userName}"/></td>
	   <td><c:out value="${row.email}"/></td>
	   <td><c:out value="*****"/></td>
	   <td><c:out value="${row.DOB}"/></td>
	   <td><c:out value="${row.regDate}"/></td>
	   <td><c:out value="${row.img}"/></td>   
	   
	   <td><a href='${pageContext.request.contextPath}/view-${row.id}'>View</a>
	   <a href='${pageContext.request.contextPath}/update-${row.id}'>Update</a>
	   <a href="${pageContext.request.contextPath}/delete-${row.id}">Delete</a></td>
	</tr>
</c:forEach>
</table>
 
 	<script type="text/javascript">
 	
 		document.ready(function(){
 			
 			$.ajax({  
		          url:'/SpringBean/rest/users/',  
		          type:'get',
		          contentType: 'application/json;charset=utf-8', // type of data
		//           dataType: 'JSON',
		          //data: JSON.stringify(JSONObject), // make JSON string
		//           crossDomain: true,
		          success: function(data) { 
		//                    var jsonData = $.parseJSON(data); //if data is not json
		                   alert(data);
		                   console.log("Success..." + data);
		          },
		           
		      	error: function(data){
		      		alert("Unsuccess" + data);
		      		console.log("ERROR..." + data);
		      	}
		      });				
 		});
 	</script>
</body>
</html>
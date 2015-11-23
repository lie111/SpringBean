<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test</title>

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>

</head>
	<body>
	
		<form action="/SpringBean/add" method="post" enctype="multipart/form-data">
			
			User Name: <input type="text" name="userName" id="txtusername"/><br/>
			Email: <input type="text" name="email" id="txtemail"><br/>
			Password: <input type="password" name="passwd" id="txtpasswd"/><br>
			Birthdate: <input type="date" name="DOB" id="txtDOB"/><br/>
			RegisterDate: <input type="date" name="regDate" id="txtregDate"/><br/>
			
			<label for="img">Image (in JPEG format only):</label>
			Image: <input type="file" name="img" id="txtimg"/><br/>
					
			<input type="button" value="Insert Me!" id="btnadd" onclick="doAdd()"/>		
		
		</form>
		
	<script type="text/javascript">
		
		
		function doAdd(){
			
				var userName = $("txtusername").val();
				var email = $("txtemail").val();
				var passwd = $("txtpasswd").val();
				var DOB = $("txtDOB").val();
				var regDate = $("txtregDate").val();
				var img = $("txtimg").val();
								
				  var JSONObject= {
				            "userName":userName,
				            "email": email,
				            "passwd":passwd,
				            "DOB":"DOB",
				            "regDate":regDate,
				            "img":img
				    };
				  
				 $.ajax({  
		          url:'/SpringBean/rest/users/',  
		          type:'post',
		          contentType: 'application/json;charset=utf-8', // type of data
		//           dataType: 'JSON',
		          data: JSON.stringify(JSONObject), // make JSON string
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
		}

	</script>
		
	</body>
</html>
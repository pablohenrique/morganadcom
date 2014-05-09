<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Morgan State University | Undergraduate Admissions
	Communications Channel</title>
<link type="text/css" rel="stylesheet" href="bootstrap.min.css" />
<%
	UserService userService = UserServiceFactory.getUserService();
	User user = userService.getCurrentUser();
	if (user == null) {
%>
<meta http-equiv="refresh"
	content="0; url=<%=userService.createLoginURL(request.getRequestURI())%>">
<%
	}
%>
</head>
<body>
	<div class="header container" style="width:700px">
		<div class="header-top">
			<img src="images.png" style="width: 700px;" />
		</div>
	</div>
	<h3 align='center'>Do NOT use Internet Explorer. Use Google Chrome or Mozilla Firefox instead.</h3>
	<br/><h4 align='center'>Select File to Upload: </h4><br/>
	<div class='container' style="width:700px">
		<form action='/UploadFileServlet' method='POST' enctype='multipart/form-data'>
			<input type='file' name='file'/>
			<button class='btn btn-primary' style='width:230px'>Submit</button>
		</form>
	</div>
</body>
</html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件操作界面</title>
</head>
<body>
<div align="center">
单文件上传
<hr><br><br>
<form id="uploadFile" action="uploadFile" method="post" enctype="multipart/form-data">  
	<input type="file" name="file" /><br> 
	<input type="submit" value="上传" /><br>
</form>
<br><br>
多文件上传
<hr><br><br>
<form id="uploadFiles" action="uploadFiles" method="post" enctype="multipart/form-data">  
	<input type="file" name="files" /><br>
	<input type="file" name="files" /><br>
	<input type="file" name="files" /><br>  
	<input type="submit" value="上传" /><br>  
</form>
</div>
<script src="<%= request.getContextPath() %>/static/js/jQuery-core/jquery-1.9.1.min.js"></script>
<script >
	$(function() {
		
    });
</script>
</body>
</html>
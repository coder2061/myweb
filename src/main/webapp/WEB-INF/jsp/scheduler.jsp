<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通用测试界面</title>
</head>
<body>
<div align="center">
计划任务
<hr>
<table border="1" style="padding: 5px;margin: 5px;width: 80%;">
	<tr>
	  <td>任务名称</td>
	  <td>任务分组</td>
	  <td>corn表达式</td>
	  <td>调度类</td>
	  <td>操作</td>
	</tr>
	<c:forEach var="item" items="${jobs}">
	<tr>
	  <td class="jobName">${item.jobName}</td>
	  <td class="jobGroup">${item.jobGroup}</td>
	  <td class="cronExpression">${item.cronExpression}</td>
	  <td class="jobClassName">${item.jobClassName}</td>
	  <td>
	  <input type="button" id="edit" value="编辑">
	  <input type="button" id="pause" value="暂停">
	  <input type="button" id="resume" value="恢复">
	  <input type="button" id="del" value="删除">
	  <input type="button" id="clear" value="清空">
	  </td>
	</tr>
</c:forEach>
</table>
</div>
<script src="<%= request.getContextPath() %>/static/js/jQuery-core/jquery-1.9.1.min.js"></script>
<script >
	$(function() {
        $("#pause").click(function() {
            var jobName = $(this).siblings("td.jobName").val();
            var jobGroup = $(this).siblings("td.jobGroup").val();
            alert(jobGroup)
        });
    });
</script>
</body>
</html>
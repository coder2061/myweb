<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Socket JavaScript Echo Client</title>
</head>
<body>
	<h1>Echo Server</h1>

	<div style="text-align: left;">
		<form action="">
			<input onclick="sendMessage();" value="Press to send" type="button">
			<input id="textID" name="message" value="Hello Web Sockets" type="text">
			<br>
		</form>
	</div>

	<div id="output"></div>
</body>

<script>
	var mywebsocket;
	var output;

	function init() {
		output = document.getElementById("output");
	}

	function sendMessage() {
		// 定义websocket的端点地址
		var wsUri = "ws://localhost/myweb/echo";
		showMessage("Connecting to " + wsUri);
		// 创建websocket对象
		mywebsocket = new WebSocket(wsUri);

		// 打开websocket
		mywebsocket.onopen = function(evt) {
			showMessage("connected!");
			// 发送文本框中的内容到服务端
			send_message(textID.value);
		}

		// 接收到服务端处理的数据
		mywebsocket.onmessage = function(evt) {
			showMessage("Received message: " + evt.data);
			// 关闭websocket
			mywebsocket.close();
			showMessage("Closed!");
		}

		mywebsocket.onerror = function(evt) {
			showMessage("<span style='color: red'>ERROR:</span>" + evt.data);
			mywebsocket.close();
		}
	}

	function send_message(message) {
		mywebsocket.send(message);
		showMessage("Sent message:" + message);
	}

	function showMessage(message) {
		var pre = document.createElement("p");
		pre.style.wordWrap = "break-word";
		pre.innerHTML = message;
		output.appendChild(pre);
	}

	window.addEventListener("load", init, false);
</script>
</html>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>WebSocket-JAVA STRING</h1>
		<div class="row">
			<div class="col-md-12">
				<p>
					<button type="button" class="btn btn-primary" onclick="openClient()">openClient</button>
					<button type="button" class="btn btn-danger" onclick="closeServer()">closeServer</button>
					<button type="button" class="btn btn-danger" onclick="closeClient()">closeClient</button>
				</p>
			</div>
		</div>
		<div class="row">
			<form class="form-inline" role="form">
				<div class="col-md-12">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group">
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-send"></span>
								</span>
								<input id="txtMessage" type="text" class="form-control" placeholder="Send Message">
							</div>
							<button type="button" class="btn btn-info" onclick="sendMessage()">sendMessage</button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="row">
			<div class="col-md-12">
				<p id="output">
					<br>
				</p>
			</div>
		</div>
	</div>
</body>
<script>
	var mywebsocket = null;

	function init() {
		output = document.getElementById("output");
	}
	
	function dispose() {
		mywebsocket.close();
		mywebsocket = null;
	}

	function openClient() {
		if (mywebsocket == null) {
			mywebsocket = new WebSocket("ws://localhost/myweb/message");
			mywebsocket.onmessage = function(evt) {
				showMessage(evt.data);
			}
			mywebsocket.onclose = function(evt) {
				showMessage(evt.data);
			}
			mywebsocket.onerror = function(evt) {
				showMessage(evt.data);
			}
		}
	}

	function sendMessage() {
		var msg = document.getElementById("txtMessage").value;
		document.getElementById("txtMessage").value = null;
		send_message(msg);
	}

	function closeServer() {
		send_message("close");
	}

	function closeClient() {
		mywebsocket.close();
		showMessage("danger:client closed.")
	}

	function send_message(message) {
		mywebsocket.send(message);
	}

	function showMessage(message) {
		var flag = message.substring(0, message.indexOf(':'));
		var data = message.substring(message.indexOf(':') + 1);

		var pre = document.createElement("p");
		// 调用bootstrap样式
		pre.className = "text-" + flag;
		pre.innerHTML = data;
		output.appendChild(pre);
	}

	window.addEventListener("load", init, false);
	window.addEventListener("unload", dispose, false);
</script>
</html>
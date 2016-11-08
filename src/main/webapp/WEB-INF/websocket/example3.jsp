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
		<h1>WebSocket-JAVA OBJECT</h1>
		<div class="row">

			<div class="col-md-12">
				<p>
					<button type="button" class="btn btn-primary" onclick="openClient()">openClient</button>
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
			mywebsocket = new WebSocket( "ws://localhost/myweb/obj");

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
		var content = document.getElementById("txtMessage").value;
		document.getElementById("txtMessage").value = null;
		var id = getRandomNum(1, 100);
		var message = {
			id: id,
			content: content
		}
		send_message(JSON.stringify(message));
	}

	function closeClient() {
		mywebsocket.close();
		showMessage("danger:client closed.")
	}

	function send_message(message) {
		mywebsocket.send(message);
	}

	function showMessage(message) {
		var pre = document.createElement("p");
		if(message.indexOf('{') != -1) {
			var obj = JSON.parse(message);
			// 调用bootstrap样式
			pre.className = "text-" + obj.id;
			pre.innerHTML = obj.content;
			output.appendChild(pre);
		} else {
			// 调用bootstrap样式
			pre.className = "text-info";
			pre.innerHTML = message;
			output.appendChild(pre);
		}
	}

	function getRandomNum(Min,Max) {   
		var Range = Max - Min;   
		var Rand = Math.random();   
		return(Min + Math.round(Rand * Range));   
	}

	/**
	* 判断是否为json对象
	* @param obj
	* @return isjson
	*/
	function isJson(obj) {
		var isjson = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;    
		return isjson;
	}
	
	window.addEventListener("load", init, false);
	window.addEventListener("unload", dispose, false);
</script>
</html>
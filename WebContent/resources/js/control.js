		var mid = document.getElementById("mid").value;
		var iid = document.getElementById("iid").value;
		
		var wsUrl = "ws://localhost:8081/DrVirual/ws/"+mid+"/"+iid;
		console.log(wsUrl)
		var webSocket;

		function init() {
			webSocket = new WebSocket(wsUrl);
			webSocket.onopen = function(evt) {
				onOpen(event)
			};
			webSocket.onclose = function(evt) {
				onClose(event)
			};
			webSocket.onmessage = function(evt) {
				onMessage(event)
			};
			webSocket.onerror = function(evt) {
				onError(event)
			};
		}
		
		function onOpen(event){
			console.log("OnOpen Event");
		}

		function onClose(event) {
			console.log("OnClose Event");
		}

		function onError(event) {
			console.log("OnError Event");
		}

		function sendMessage() {
			//DOM MANİP
			var x= document.getElementById("text").value;
			console.log(x);
			
			var div = document.getElementById("div");
			var span = document.createElement("SPAN");
			span.textContent = x;
			span.style.backgroundColor = "yellow";
			div.appendChild(span);
			var br = document.createElement("BR");
			div.appendChild(br);
			document.getElementById("text").value = "";
			
			//SERVER
			webSocket.send(x);
		}

		function onMessage(event) {
			//DOM MANİP
			var div = document.getElementById("div");
			var span = document.createElement("SPAN");
			span.textContent = event.data;
			span.style.backgroundColor = "green";
			div.appendChild(span);
			var br = document.createElement("BR");
			div.appendChild(br);
		}

		window.addEventListener("load", init, false);

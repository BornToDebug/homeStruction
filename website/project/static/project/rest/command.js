function reqListener () {
	console.log(this.responseText);
}

function command(command) {
	var commandReq = new XMLHttpRequest();
	commandReq.addEventListener("load", reqListener);
	commandReq.open("GET", "/command?command=" + command);
	commandReq.send();
}

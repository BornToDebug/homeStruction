function reqListener () {
	console.log(this.responseText);
}

function command(command) {
	var commandReq = new XMLHttpRequest();
	commandReq.addEventListener("load", reqListener);
	commandReq.open("GET", "/command?command=" + command);
	commandReq.send();
}

	

var button2 = document.getElementById("myopclswitch");
var lamp1button=document.getElementById("myonoffswitch");
var lamp2button=document.getElementById("myonoff2switch");
var lamp3button=document.getElementById("myonoff3switch");


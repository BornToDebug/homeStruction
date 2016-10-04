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
function myFunction(){
	if(button2.checked==false) {command("opendoor")};
    if (button2.checked==true) {command("closedoor");}
}
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

function myFunction(){
	if(button2.checked==false) {command("opendoor")};
    if (button2.checked==true) {command("closedoor");}
}

function lamp1Func(){
	if(lamp1button.checked==false) {command("1lampon")};
    if (lamp1button.checked==true) {command("1lampoff");}
	
}

function lamp2Func(){
	if(lamp2button.checked==false) {command("2lampon")};
    if (lamp2button.checked==true) {command("2lampoff");}
	
}

function lamp3Func(){
	if(lamp3button.checked==false) {command("3lampon")};
    if (lamp3button.checked==true) {command("3lampoff");}
	
}
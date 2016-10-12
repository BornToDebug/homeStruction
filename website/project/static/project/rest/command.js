function reqListener () {
	console.log(this.responseText);
}

function command(command) {
	var commandReq = new XMLHttpRequest();
	commandReq.addEventListener("load", reqListener);
	commandReq.open("GET", "/command?command=" + command);
	commandReq.send();
}

	function objectToHTML(jsonObj) {
	// generate HTML from JSON object
	var timeRec= jsonObj['time_recorded'];
	var myDate=timeRec.substring(0,10);
	var myTime=timeRec.substring(11,19);
	var myValue=jsonObj['value'];
	var tableVar=myValue;
	if(myValue == "1on_c"){tableVar="Lamp #1 successfully switched on";}
	if(myValue == "1off_c"){tableVar="Lamp #1 successfully switched off";}
	if(myValue == "1on_uc"){tableVar="Could not switch light #1 on";}
	if(myValue == "1off_uc"){tableVar="Could not switch light #1 off";}
	
	if(myValue == "2on_c"){tableVar="Lamp #2 successfully switched on";}
	if(myValue == "2off_c"){tableVar="Lamp #2 successfully switched off";}
	if(myValue == "2on_uc"){tableVar="Could not switch light #2 on";}
	if(myValue == "2off_uc"){tableVar="Could not switch light #2 off";}
	
	if(myValue == "3on_c"){tableVar="Lamp #3 successfully switched on";}
	if(myValue == "3off_c"){tableVar="Lamp #3 successfully switched off";}
	if(myValue == "3on_uc"){tableVar="Could not switch light #3 on";}
	if(myValue == "3off_uc"){tableVar="Could not switch light #3 off";}
	
	if(myValue == "do_c" ) { tableVar="Unlocked";}
	if(myValue == "dc_c") { tableVar ="Locked";}
	if(myValue == "do_uc") {tableVar="Unlocking failed";}
	if(myValue == "dc_uc") {tableVar="Locking failed";}
	if(myValue == "opened") {tableVar="Open";}
	if(myValue == "closed") {tableVar="Closed";}
	
	var tableRow = '<tr> \n' +
		'<td>' +  tableVar + '</td> \n' +
		'<td>' + myDate + '</td> \n' +
		'<td>' +  myTime + '</td> \n' +
		'</tr> \n';
	return tableRow;
	
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
	if(lamp1button.checked==false) {command("1lampoff")};
    if (lamp1button.checked==true) {command("1lampon");}
	
}

function lamp2Func(){
	if(lamp2button.checked==false) {command("2lampoff")};
    if (lamp2button.checked==true) {command("2lampon");}
	
}

function lamp3Func(){
	if(lamp3button.checked==false) {command("3lampoff")};
    if (lamp3button.checked==true) {command("3lampon");}
	
}
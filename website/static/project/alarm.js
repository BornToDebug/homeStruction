

function genURL(timeValue, monday, tuesday, wednesday, thursday, friday, saturday, sunday) {
	var url = "/setalarm/?";

	var HourMin = timeValue.split(":");
	var hour = HourMin[0].trim();
	console.log("hour:" + hour);
	var minute = HourMin[1].trim().split(" ")[0];
	if(HourMin[1].trim().split(" ")[1] === "PM") {
		hour = (parseInt(hour) + 12).toString();
		}




	console.log("minute:" + minute);
	url += "hour=" + hour + "&";
	url += "minute=" + minute + "&";
	if(monday === true) {
		url +="monday=True&";
	}
	if(tuesday === true) {
		url += "tuesday=True&";
	}
	if(wednesday === true) {
		url += "wednesday=True&";
	}
	if(thursday === true) {
		url += "thursday=True&";
	}
	if(friday === true) {
		url += "friday=True&";
	}
	if(saturday === true) {
		url += "saturday=True&";
	}
	if(sunday === true) {
		url += "sunday=True&";
	}
	return url;
}

function setAlarm(){

	var timePickerValue = document.getElementById('timepicker').value;
	console.log(timePickerValue);
	var monday = document.getElementById('test1').checked;
	console.log(monday);
	var tuesday = document.getElementById('test2').checked;
	console.log(tuesday);
	var wednesday = document.getElementById('test3').checked;
	console.log(wednesday);
	var thursday = document.getElementById('test4').checked;
	console.log(thursday);
	var friday = document.getElementById('test5').checked;
	console.log(friday);
	var saturday = document.getElementById('test6').checked;
	console.log(saturday);
	var sunday = document.getElementById('test7').checked;
	console.log(sunday);


	var xhttp = new XMLHttpRequest();
	var url = genURL(timePickerValue, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
	console.log(url);
	xhttp.open("GET", url);
	xhttp.send();

}

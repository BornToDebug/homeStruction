
function getJSON(url, objectConverter, table, button,posVal,milliseconds) {
	// try to fetch JSON data from url and call event
	var json =$.getJSON(url, function(data) {
		console.log('success');
		generateRows(data['results'], table, objectConverter);
		if(milliseconds == undefined){
		
		if(button != undefined) {
			button.checked = data['results'][0].value === posVal;
		}
		}	
		else if(milliseconds != undefined){	
		var currentTime = (new Date).getTime();
		if(currentTime-milliseconds >= 6000){
			
		if(button != undefined) {
			button.checked = data['results'][0].value === posVal;
		}
		}
	}


	})
	.done(function() {
		console.log('second success');

	})
	.fail(function() {
		//TODO implement error message
		console.log('error');
	});


}
function lampJSON (url, objectConverter, table){
	// try to fetch JSON data from url and call event
	var json =$.getJSON(url, function(data) {
		console.log('success');
		generateLamp(data['results'], table, objectConverter);
	})
	.done(function() {
		console.log('second success');

	})
	.fail(function() {
		//TODO implement error message
		console.log('error');
	});
	
}

function newJSON(url,objectConverter,_status) {
	// try to fetch JSON data from url and call events

	var json =$.getJSON(url, function(data) {
		console.log('success');
		generateStatus(data['results'],_status,objectConverter);

	})
	.done(function() {
		console.log('second success');

	})
	.fail(function() {
		//TODO implement error message
		console.log('error');
	});


}

function buttonJSON(url, button,posVal,milliseconds) {
	// try to fetch JSON data from url and call events

	var json = $.getJSON(url, function(data) {
		console.log('success');
	if(milliseconds == undefined){
		
		if(button != undefined) {
			button.checked = data['results'][0].value === posVal;
		}
	}	
	else if(milliseconds != undefined){	
		var currentTime = (new Date).getTime();
		if(currentTime-milliseconds >= 6000){
			
		if(button != undefined) {
			button.checked = data['results'][0].value === posVal;
		}
		}
	}
	})
	.done(function() {
		console.log('second success');

	})
	.fail(function() {
		//TODO implement error message
		console.log('error');
	});

}

function generateRows(json, table, objectConverter) {
	//delete current entries in table and populate it with data
	var tableRow;
	table.innerHTML = '';
	for (i=0;i<json.length;i++) {
		tableRow = objectConverter(json[i]);
		table.innerHTML += tableRow;
	}
}
function generateStatus(json,_status,objectConverter){
	var tableState;

	_status.innerHTML = '';

		tableState = objectConverter(json[0]);
		_status.innerHTML = tableState;


}
function generateLamp(json, table,objectConverter){
		var tableRow;
	table.innerHTML = '';
	for (i=0;i<json.length;i++) {
		tableRow = objectConverter(json[i]);
		if (tableRow != '<tr> \n' +
		'<td>' +  "nope" + '</td> \n' +
		'<td>' + "nope" + '</td> \n' +
		'<td>' +  "nope" + '</td> \n' +
		'</tr> \n'){
		table.innerHTML += tableRow;}
	}
}


function arrayJSON (url,stateConverter, buttonDoor,idDoor, buttonLamp,idLamp, buttonNight,idNight, buttonFan,idFan, milliDoor, milliLamp, milliNight,milliFan,l1status,l2status,l3status,wstatus,dstatus,tstatus,hstatus,lstatus,l1status,l2status,l3status){
	
	var json =$.getJSON(url, function(data) {
		console.log('success');
		dataState(data['values'][0],stateConverter,tsatus);
		dataState(data['values'][1],stateConverter,lstatus);
		buttonState(data['values'][2],buttonLamp,idLamp,milliLamp);
		buttonState(data['values'][3],buttonNight, idNight,milliNight);
		buttonState(data['values'][4],buttonFan,idFan,milliFan);
		buttonState(data['values'][5],buttonDoor,idDoor,milliDoor);
		dataState(data['values'][6],stateConverter,dstatus);
		dataState(data['values'][7],stateConverter,wstatus);
		dataState(data['values'][8],stateConverter,hstatus);
		dataState(data['values'][2],stateConverter,l1status);
		dataState(data['values'][3],stateConverter,l2status);
		dataState(data['values'][4],stateConverter,l3status);
	})
	.done(function() {
		console.log('second success');

	})
	.fail(function() {
		
		console.log('error');
	});
	
}
 function dataState(data, stateConverter,_status){
	 generateData(data,stateConverter,outp);

 }
 function buttonState(data,button,posVal,milliseconds){
	 if(milliseconds == undefined){
		
		if(button != undefined) {
			button.checked = data === posVal;
		}
	}	
	else {
		var currentTime = (new Date).getTime();
		if(currentTime-milliseconds >= 6000){
			
		
			button.checked = data === posVal;
		
		}
	}
 }
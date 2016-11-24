
function getJSON(url, objectConverter, table, button,posVal,milliseconds) {
	// try to fetch JSON data from url and call event
	var json =$.getJSON(url, function(data) {
		console.log('success');
		generateRows(data['results'], table, objectConverter);
		var currentTime = (new Date).getTime();
		if(milliseconds!= undefined && currentTime-milliseconds >= 6000){
			if(button != undefined) {
				button.checked = data['results'][0].value === posVal;
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
		generateRows(data['results'], table, objectConverter);
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
function generateStatus(json,_status,objectConverter){
	var tableState;

	_status.innerHTML = '';

		tableState = objectConverter(json[0]);
		_status.innerHTML = tableState;


}
function buttonJSON(url, button,posVal,milliseconds) {
	// try to fetch JSON data from url and call events

	var json = $.getJSON(url, function(data) {
		console.log('success');
		var currentTime = (new Date).getTime();
		if(currentTime-milliseconds >= 6000){
			
		if(button != undefined) {
			button.checked = data['results'][0].value === posVal;
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

function getJSON(url, objectConverter, table, button,posVal) {
	// try to fetch JSON data from url and call events
	var json =function(){ $.getJSON(url, function(data) {
		console.log('success');
		generateRows(data['results'], table, objectConverter);
		if(button != undefined) {
			button.checked = data['results'][0].value === posVal;
		}


	})
	.done(function() {
		console.log('second success')
	})
	.fail(function() {
		//TODO implement error message
		console.log('error');
	});
}
    setInterval(json,table.reload(),3000);
}

function newJSON(url,objectConverter,_status) {
	// try to fetch JSON data from url and call events
	var json = function(){$.getJSON(url, function(data) {
		console.log('success');
		generateStatus(data['results'],_status,objectConverter);

	})
	.done(function() {
		console.log('second success')
	})
	.fail(function() {
		//TODO implement error message
		console.log('error');
	});
}
    setInterval(json,_status.reload(),3000);
	
}
function generateStatus(json,_status,objectConverter){
	var tableState;
	_status.innerHTML = '';

		tableState = objectConverter(json[0]);
		_status.innerHTML = tableState;

	
}
function buttonJSON(url, button,posVal) {
	// try to fetch JSON data from url and call events
	var json = $.getJSON(url, function(data) {
		console.log('success');
		if(button != undefined) {
			button.checked = data['results'][0].value === posVal;
		}

	})
	.done(function() {
		console.log('second success')
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


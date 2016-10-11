function getJSON(url, objectConverter, table, button,posVal) {
	// try to fetch JSON data from url and call events
	var json = $.getJSON(url, function(data) {
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

function generateRows(json, table, objectConverter) {
	//delete current entries in table and populate it with data
	var tableRow;
	table.innerHTML = '';

	for (i=0;i<json.length;i++) {
		tableRow = objectConverter(json[i]);
		table.innerHTML += tableRow;
	}
}


function getJSON(url, objectConverter, table) {
	// try to fetch JSON data from url and call events
	var json = $.getJSON(url, function(data) {
		console.log('success');
		generateRows(data['results'], table, objectConverter);
	})
	.done(function() {
		console.log('second success')
	})
	.fail(function() {
		//TODO implement error message
		console.log('error');
	});
}
function objectToHTML(jsonObj) {
	// generate HTML from JSON object
	var tableRow = '<tr> \n' +
		'<td>' +  jsonObj['value'] + '</td> \n' +
		'<td>' + jsonObj['time_recorded'] + '</td> \n' +
		'</tr> \n';
	return tableRow;
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
doortable = document.getElementById('doortable');
windowtable = document.getElementById('windowtable');
getJSON('/api/door', objectToHTML, doortable);
getJSON('/api/window', objectToHTML, windowtable);

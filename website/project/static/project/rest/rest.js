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


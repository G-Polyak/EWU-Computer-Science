$(window).on("load", init());

function init() {

	globalUrl = '../v1/all/';
	var edit = $('#edit');
	var add = $('#add');
	var cancel = $('#cancel');
	var addEnt = $('#addEntry');
	var tableEnt = $('#tableEntry');
	tableEnt.hide();
	edit.hide();
	cancel.hide();
	addEnt.click(unhide);
	addEnt.click(showCancel);
	add.click(addEntry);
	add.click(function () {
		cancel.hide();
	});
	edit.click(makeEdit);
	edit.click(function () {
		cancel.hide();
	});
	cancel.click(function () {
		tableEnt.hide();
		cancel.hide();
	});
	var all = $('#all');
	var noodles = $('#noodles');
	noodles.hide();
	var shapes = $('#shapes');
	shapes.hide();
	$('#allPasta').click(function () {

		globalUrl = '../v1/all/';
		all.show();
		noodles.hide();
		shapes.hide();
		populate();

	});
	$('#noodlePasta').click(function () {

		globalUrl = '../v1/noodles/';
		noodles.show();
		all.hide();
		shapes.hide();
		populate();

	});
	$('#shapePasta').click(function () {

		globalUrl = '../v1/shapes/';
		shapes.show();
		all.hide();
		noodles.hide();
		populate();

	});
	populate();

}

var globalID;
var globalUrl;

function populate() {

	var table = $('#tableData');
	table.find("tr:gt(0)").remove();
	$.get(globalUrl, function (result) {

		console.log(result);
		var parsed = JSON.parse(result);
		for (var i = 0; i < parsed.length; i++) {

			var id = parsed[i]['id'];
			table.append('<tr><td style="display:none;" class="rowID">' + id + '</td><td>' + parsed[i]['name']
				+ '</td><td>' + parsed[i]['description'] + '</td>' +
				'<td>' + parsed[i]['uses'] + '</td><td>' + parsed[i]['classification'] + '</td>' +
				'<td><button class="edit" onclick="editEntry(\'' + id + '\'); showCancel();">' +
				'Edit</button>' + '<button class="delete" onclick="delEntry(\'' + id + '\');">' + 'Delete' +
				'</button> </td></tr>');

		}

	});

}

function showCancel() {
	$('#cancel').show();
}

function editEntry(id) {

	unhide();
	$('#add').hide();
	$('#edit').show();
	globalID = id;
	var row = $("td").filter(function () {
		return $(this).text() === id;
	}).closest('tr');
	$('#inputName').val(row.find('td')[1].innerHTML.toString());
	$('#inputDescript').val(row.find('td')[2].innerHTML).toString();
	$('#inputUses').val(row.find('td')[3].innerHTML.toString());
	$('#inputClass').val(row.find('td')[4].innerHTML.toString());

}

function makeEdit() {

	var data = getData(true);
	$.post(globalUrl, data, function (ok) {
		populate();
	});
	$('#add').show();
	$('#edit').hide();
	$('#tableEntry').slideUp();
	globalID = null;

}

function delEntry(id) {

	var data = { id: id, delete: "true" };
	$.get(globalUrl, data, function (ok) {
		populate();
	});

}

function addEntry() {

	var data = getData(false);
	$.post(globalUrl, data, function (ok) {
		populate();
	});
	$('#tableEntry').slideUp();

}

function getData(id) {

	var name = $('#inputName').val();
	var description = $('#inputDescript').val().toString();
	var uses = $('#inputUses').val();
	var classification = $('#inputClass').val();
	if(id) {
		return {id: globalID, name: name, description: description, uses: uses, classification: classification};
	} else {
		return {name: name, description: description, uses: uses, classification: classification};
	}

}

function unhide() {
	$('#tableEntry').slideDown();
}
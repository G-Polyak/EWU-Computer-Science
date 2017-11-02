$(window).on("load", init());

function init() {

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
    populate();
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

}

var globalID;

function populate() {

    var table = $('#tableData');
    table.find("tr:gt(0)").remove();
    var url = 'http://gpolyak.xyz/movie_database/toolkit.php';
    $.get(url, function (result) {

        var parsed = JSON.parse(result);
        for (var i = 0; i < parsed.length; i++) {

            var id = parsed[i]['id'];
            table.append('<tr><td style="display:none;" class="rowID">' + id + '</td><td>' + parsed[i]['title']
                + '</td><td>' + parsed[i]['year'] + '</td>' +
                '<td>' + parsed[i]['studio'] + '</td><td>' + parsed[i]['description'] + '</td><td>' +
                parsed[i]['price'] + '</td><td><button class="edit" onclick="editEntry(\'' + id + '\'); showCancel();">' +
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
        return $(this).text() == id;
    }).closest('tr');
    $('#inputTitle').val(row.find('td')[1].innerHTML.toString());
    $('#inputYear').val(row.find('td')[2].innerHTML).toString();
    $('#inputStudio').val(row.find('td')[3].innerHTML.toString());
    $('#inputDesc').val(row.find('td')[4].innerHTML.toString());
    $('#inputPrice').val(row.find('td')[5].innerHTML.toString());

}

function makeEdit() {

    var url = 'http://gpolyak.xyz/movie_database/toolkit.php';
    var title = $('#inputTitle').val();
    var year = $('#inputYear').val().toString();
    var studio = $('#inputStudio').val();
    var descripton = $('#inputDesc').val();
    var price = $('#inputPrice').val();
    var fullUrl = url + '?id=' + globalID + '&title=' + title + '&year=' + year + '&studio=' + studio + '&description=' +
        descripton + '&price=' + price;
    $.post(fullUrl, function (ok) {
        populate();
    });
    $('#add').show();
    $('#edit').hide();
    $('#tableEntry').slideUp();
    globalID = null;

}

function delEntry(id) {

    var url = 'http://gpolyak.xyz/movie_database/toolkit.php';
    $.get(url + '?id=' + id, function (ok) {
        populate();
    });

}

function addEntry() {

    var url = 'http://gpolyak.xyz/movie_database/toolkit.php';
    var title = $('#inputTitle').val();
    var year = $('#inputYear').val().toString();
    var studio = $('#inputStudio').val();
    var descripton = $('#inputDesc').val();
    var price = $('#inputPrice').val();
    var fullUrl = url + '?title=' + title + '&year=' + year + '&studio=' + studio + '&description=' +
        descripton + '&price=' + price;
    $.post(fullUrl, function (ok) {
        populate();
    });
    $('#tableEntry').slideUp();

}

function unhide() {
    $('#tableEntry').slideDown();
}
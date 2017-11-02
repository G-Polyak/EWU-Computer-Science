function calculate() {

    var p = parseFloat(document.getElementById("sp").value);
    var ia = parseFloat(document.getElementById("i").value);
    var pay = parseFloat(document.getElementById("p").value);
    var table = document.getElementById("t");

    while(table.rows.length > 1) {
        table.deleteRow(1);
    }

    var balance = p;
    var i = 1;
    //While principal > 0
    while (p > 0) {

        //compute monthly interest - principal * ((rate / 12) / 100)
        var mi = p * ((ia / 12) / 100);
        //if principal - monthly pay + interest less than zero (loan paid off - last line of table)
        if (p - pay + mi <= 0) {

            //monthly pay becomes principal + interest
            pay = p + mi;
            //ending balance becomes zero
            insertRows(table, i, p, mi, pay, 0);
            p = 0;

        } else {

            balance = p + mi - pay;
            //print columns
            insertRows(table, i, p, mi, pay, balance);
            //update principle
            p = balance;

        }
        i++;

    }

}

function insertRows(table, i, p, mi, pay, balance) {

    var row = table.insertRow(i);
    row.insertCell(0).innerHTML = "$" + p.toFixed(4).toString();
    row.insertCell(1).innerHTML = "$" + mi.toFixed(4).toString();
    row.insertCell(2).innerHTML = "$" + pay.toFixed(4).toString();
    row.insertCell(3).innerHTML = "$" + balance.toFixed(4).toString();

}


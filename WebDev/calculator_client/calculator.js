$(window).on("load", init());

var out = $("#readout");
sessionStorage.register = "";
sessionStorage.regOps = "";

function init() {

    $(".num").click(nums);
    $(".op").click(opers);
    $(".special").click(specials);
    $(".clears").click(clears);

}

function nums() {

    var btn = $(this).text().toString();
    if(btn === ".") {

        if(out.val().toString().indexOf(".") < 0 || out.val().toString().length < 1) {
            out.get(0).value += btn;
        }

    } else {
        out.get(0).value += btn;
    }

}

function opers() {

    if (out.val().length > 0) {
        if (sessionStorage.regOps.length < 1) {

            sessionStorage.register = out.val();
            out.val("");
            sessionStorage.regOps = $(this).text().toString();

        } else {

            calculate();
            sessionStorage.register = out.val();
            out.val("");
            sessionStorage.regOps = $(this).text().toString();

        }
    }

}

function calculate() {

    out.val(round(eval(sessionStorage.register.toString() + sessionStorage.regOps.toString()
        +out.val().toString())));

}

function round(value) {
    return Number(Math.round(value + 'e8') + 'e-8');
}

function specials() {

    switch ($(this).text().toString()) {

        case "+-": var math1 = -1 * parseFloat(out.val().toString());
            out.val(round(math1).toString());
            break;

        case "Sq": var math2 = Math.sqrt(parseFloat(out.val().toString()));
            out.val(round(math2).toString());
            sessionStorage.register = math2.toString();
            break;

        case "1/x": var math3 = 1 / parseFloat(out.val().toString());
            out.val(round(math3).toString());
            sessionStorage.register = math3.toString();
            break;

        case "=": if(sessionStorage.register.toString() !== "") {
            calculate();
            sessionStorage.register = "";
            sessionStorage.regOps = "";
            }
            break;

    }

}

function clears() {

    var clear = $(this).val().toString();
    if(clear === "C"){
        sessionStorage.register = "";
        sessionStorage.regOps = "";
    }
    out.val("");

}

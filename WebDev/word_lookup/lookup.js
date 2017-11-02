$(window).on("load", init());


function init() {

    $("#btnSearch").click(search);
    $("#btnYodaPhrase").click(yodaPhrase);

}

function search() {

    $(".table").find("tr:gt(0)").remove();
    var word = $("#input").val().toString();
    var client = new HttpClient();
    var proxy = "http://cors-anywhere.herokuapp.com/";
    $("#word").text(word);
    client.get(proxy + 'http://api.datamuse.com/words?sp=' + word + '&qe=sp&md=d',
        function (response) {
            console.log(response[0]["defs"]);
            var defins = response[0]["defs"];
            var table = document.getElementById("defTable");
            for(var i = 0; i < defins.length; i++) {
                if (i < 6) {
                    table.insertRow(i + 1).insertCell(0).innerHTML = defins[i];
                }
            }

        });

    client.get(proxy + 'http://api.datamuse.com/words?rel_syn=' + word,
        function (response) {
            console.log(response[0]["word"]);
            var table = document.getElementById("synTable");
            for(var i = 0; i < response.length; i++) {
                if (i < 6) {
                    table.insertRow(i + 1).insertCell(0).innerHTML = response[i]["word"];
                }
            }
        });

    client.get(proxy + 'http://api.datamuse.com/words?rel_ant=' + word,
        function (response) {
            console.log(response);
            var table = document.getElementById("antTable");
            for(var i = 0; i < response.length; i++) {
                if (i < 6) {
                    table.insertRow(i + 1).insertCell(0).innerHTML = response[i]["word"];
                }
            }
        });

        client.get(proxy + 'http://www.anagramica.com/all/:' + word,
        function (response) {
            console.log(response);
            var table = document.getElementById("anaTable");
            for(var i = 0; i < response["all"].length; i++) {

                if (i < 6) {
                    if (response["all"][i].length === word.length) {
                        table.insertRow(i + 1).insertCell(0).innerHTML = response["all"][i];
                    }
                }

            }
        });

    translate(word, "zh-CN", "Chinese:\t");
    translate(word, "es", "Spanish:\t");
    translate(word, "hi", "Hindi:\t");
    translate(word, "ar", "Arabic:\t");


}

function translate(word, lang, string) {

    var client = new HttpClient();
    var s = "https://script.google.com/macros/s/AKfycby9lybRTpPUSKFLmDN9BtHIpY1iq9EqXE8UPnuKuemsReJ37a5K/exec?" +
        "source=en&target=" + lang + "&q=" + word;
    client.get(s, function (response) {

       var strings = response.split('"');
       var table = document.getElementById("transTable");
       table.insertRow().insertCell(0).innerHTML = string + strings[strings.length-2];

    });

}

function yodaPhrase() {

    var client = new HttpClient();
    var phrase = encodeURI($("#inputYoda").val().toString());
    client.get('http://api.funtranslations.com/translate/yoda.json?text=' + phrase,
        function (response) {
            $("#yodaPhrase").text(response["contents"]["translated"]);
        });

}

var HttpClient = function () {

    this.get = function (url, callback) {
        $.ajax({
            url: url,
            type: 'GET',
            success: callback
        });
    }

};
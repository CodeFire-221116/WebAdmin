/**
 * AJAX Server
 */

var request = new XMLHttpRequest();

// Response listener
request.onreadystatechange = function () {

    if (request.status === 200 && request.readyState === 4) {
        //console.log(request.responseText);
        //document.querySelector('#time_display').innerText = request.responseText;

        //var datetime = new Date(request.responseText);
        //document.querySelector('#time_display').innerText = datetime;

        var datetime = new Date(request.responseText);
        var y = datetime.getFullYear();
        var m = datetime.getMonth() < 10 ? '0' + datetime.getMonth() : datetime.getMonth();
        var d = datetime.getDate() < 10 ? '0' + datetime.getDate() : datetime.getDate();
        var hrs = datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours();
        var min = datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes();
        var sec = datetime.getSeconds() < 10 ? '0' + datetime.getSeconds() : datetime.getSeconds();

        document.querySelector('#time_display').innerText =
            d + "." + m + "." + y + " " + hrs + ":" + min + ":" + sec;
    }

};

function send() {
    request.open('GET', '/ajax/server?message=Hello%20world!');
    request.send();
}

function getTimeNow() {
    request.open('GET', '/ajax/server?action=get_time');
    request.send();
}
/**
 * AJAX Server
 */

var request = new XMLHttpRequest();

function send() {
    request.open('GET', '/ajax/server?message=Hello%20world!');
    request.send();
}
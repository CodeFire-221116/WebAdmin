
var messagesList = document.querySelector('#messagesList');
var messageInput = document.querySelector('#messageInput');

var socket = new WebSocket("ws://192.168.1.102:8080/ws");

socket.onopen = function (e) {
    console.log('open', e);
};
socket.onmessage = function (e) {
    console.log('message', e);

    var item = document.createElement("li");
    item.className = "list-group-item";
    item.innerHTML = e.data;

    messagesList.appendChild(item);
    messagesList.scrollTop = messagesList.scrollHeight;
};
socket.onerror = function (e) {
    console.log('error', e);
};
socket.onclose = function (e) {
    console.log('close', e);
};

function sendMessage() {
    var text = messageInput.value;
    //console.log(text);
    socket.send(text);

    messageInput.value = "";
}
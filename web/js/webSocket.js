var websocket;
var status = document.getElementById("feed");
var maakConnectie = document.getElementById("navheader");
maakConnectie.onclick = openSocket();


function openSocket() {
    websocket = new WebSocket("ws://localhost:8080/connect");

    websocket.onopen = function (event) {
        writeResponse("Connection opened");
    };

    websocket.onmessage = function (event) { // lees deze de functies uit onze java file
        alert("new message" + event.data);
        writeResponse(event.data);
    };

    websocket.onclose = function (event) {
        writeResponse("Connection closed");
    };
}

function send(text) {
    websocket.send(text);
}


function closeSocket() {
    websocket.close();
}

function writeResponse(text) {

    var jsonData = JSON.parse(text);
    var blogid = jsonData.id;
    var blogPost = document.getElementById("blog" + blogid);
    var commentBox = blogPost.querySelector("div[class='commentBox']");
    var commentText = jsonData.antwoord;
    var rating = jsonData.rating;
    var user = jsonData.user;

    commentBox.innerHTML += "<div class ='comments'>"
        + "<strong>" + user + "</strong>"
        + ": " + "<em>" + commentText + "</em>"
        + " - " + rating + "</div>";
}

function submitComment(id) {
    alert(id);
    var blogPost = document.getElementById("blog" + id);
    var obj = {};
    obj ["id"] = id;
    //var user = document.getElementById("userName");
    var elements = blogPost.querySelectorAll(".antwoord");
    for (var i = 0; i < elements.length; i++) {
        var element = elements[i];
        var name = element.name;
        var value = element.value;
        if (isNaN(value) && name == "rating") {
            alert("the rating is not a number" + value);
            return false;
        }
        if (value < 1 || value > 10) {
            alert("the rating must been between 1 and 10");
            return false;
        }
        if (name) {
            obj[name] = value;
        }
    }
    obj ["user"] = document.getElementById("userName").innerText;
    send(JSON.stringify(obj));

}

function toJSONString(form) {
    var obj = {};
    var elements = form.querySelectorAll("input, select, textarea");

    for (var i = 0; i < elements.length; ++i) {
        var element = elements[i];
        var name = element.name;
        var value = element.value;

        if (name) {
            obj[name] = value;
        }
    }


}
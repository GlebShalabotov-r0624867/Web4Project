
/*----Buttons Config----*/
var buttonStatus = document.getElementById("changeStatus");
buttonStatus.onclick = changeStatus;
var buttonAddFriend = document.getElementById("addNewFriendo");
buttonAddFriend.onclick = addNewFriendo;



/*----------------------*/

/*------ XMLHttpRequests -------*/
var xHRObject = new XMLHttpRequest();
var friendlistRequest = new XMLHttpRequest();
/*------------------------------*/

/*----- FUNctions -----*/

function ready() {
    getFriendList();
    getStatus();
}

function getStatus() {
    xHRObject.open("GET", "Controller?action=Status", true);
    xHRObject.onreadystatechange = getStatusData; //-> gaat de status enkel uitvoeren als er iets veranderd
    xHRObject.send(null);
}

function getFriendList() {

    friendlistRequest.open("GET", "Controller?action=Friendlist", true);
    friendlistRequest.onreadystatechange = getFriendsData;
    friendlistRequest.send(null);
}

function getFriendsData() {

    if (friendlistRequest.status == 200) {
        if (friendlistRequest.readyState = 4) {

            var json = JSON.parse(friendlistRequest.responseText);
            var friendList = document.getElementById("friendList");

            if (friendList.childNodes.length > 2) {
                while (friendList.childNodes[2] != null) {
                    friendList.removeChild(friendList.childNodes[2]);
                }
            }

            for (var i = 0; i < json.length; i++) {
                let tablerow = document.createElement('tr');
                let nametext = document.createTextNode(json[i].firstName);
                let tdname = document.createElement('td');
                tdname.className="user";
                tdname.id =json[i].userId;
                tdname.appendChild(nametext);

                let statustext = document.createTextNode(json[i].status);
                let tdstatus = document.createElement('td');
                tdstatus.className = json[i].status;
                tdstatus.appendChild(statustext);
                tablerow.appendChild(tdname);
                tablerow.appendChild(tdstatus);
                friendList.appendChild(tablerow);

            }
            setTimeout(getFriendList, 3000);
        }
    }
}
function getStatusData() {
    if (xHRObject.status == 200) {
        if (xHRObject.readyState == 4) {

            var json =  JSON.parse(xHRObject.responseText);

            var textStatus = document.createTextNode(json.status);



            var divStatus = document.getElementById("status");
            var quoteParagraph = divStatus.childNodes[0];

            if (quoteParagraph == null) {
                quoteParagraph = document.createElement('p');
                quoteParagraph.id = "statusText";
                /*
                                var quoteText = document.createTextNode(status);
                */
                quoteParagraph.appendChild(textStatus);
                divStatus.appendChild(quoteParagraph);
            }
            else {
                quoteParagraph.removeChild(quoteParagraph.childNodes[0]);
                quoteParagraph.appendChild(textStatus);
            }
            //polling
            setTimeout(getStatus, 2500);


            //probleem, hoe haal ik nu mijn status uit mijn html en moet ik het doorsturen naa rmijn servlet
            // aangezien het toch al in mijn html pagina staat?
        }
    }
}

/* ------ Post FUNctions ------ */

function changeStatus() {
    var status = document.getElementById("newStatus").value;
    // encodeURIComponent om UTF-8 te gebruiken en speciale karakters om te zetten naar code
    var information = "newStatus=" + encodeURIComponent(status);
    xHRObject.open("POST", "Controller?action=ChangeStatus", true);
    // belangrijk dat dit gezet wordt anders kan de servlet de informatie niet interpreteren!!!
    // protocol header information
    xHRObject.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xHRObject.send(information); // nieuwe status
}


function addNewFriendo() {
    var newFriend = document.getElementById("newFriendo").value;
    // encodeURIComponent om UTF-8 te gebruiken en speciale karakters om te zetten naar code
    var information = "newFriendo=" + encodeURIComponent(newFriend);
    friendlistRequest.open("POST", "Controller?action=AddFriendo", true);
    // belangrijk dat dit gezet wordt anders kan de servlet de informatie niet interpreteren!!!
    // protocol header information
    friendlistRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    friendlistRequest.send(information); // nieuwe friendo

}



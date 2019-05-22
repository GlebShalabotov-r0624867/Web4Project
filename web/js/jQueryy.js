$(document).ready(function () {

    var users = [];

    $('#friendList').on('click', 'tr td.user', function () {

        var userID = $(this).attr("id");
        var status = $(this).siblings('.status');

        alert(status.text());


        if ($.inArray(userID, users) != -1)
        {
            var user = users.splice($.inArray(userID, users), 1, function (){});
            alert("delete user: " + user);
            users.unshift(user);
        }else{

            users.unshift(userID);
        }


        var idChatbox = 'chatbox-'+ userID;
        var chatpopup =
            "<div class='chatbox'  id='"+ idChatbox+ "'>"
            + "<div class='chatHoofding'>" + $(this).text()
            + " </div> <div class='esc'>x</div>" +
            "<div class='msg_body'><div class='layout''></div> </div>" +
            "<textarea class='msg_input' rows='4'></textarea>"
            + "</div>";

        getMessages(userID);
       $('body').append(chatpopup);
        /*if (status.text()== online){
            $('[id="chatbox-' + userID + '"]').css("background-color", "green");
        }*/
        //pop chat venster
        displayChatBox();

    });

 /*   $('.chathoofding').click(function(){
        var chatbox = $(this).parents().attr("rel") ;
        $('[rel="'+chatbox+'"] .msg_wrap').slideToggle('slow');
        return false;
    });*/

    $(document).on('click', '.chatHoofding', function(){
        var chatbox = $(this).parents().attr("id") ;
        $('[id="'+chatbox+'"] .msg_body').slideToggle('slow');
        return false;
    })

    $(document).on('click', '.esc', function () {
          var chatbox = $(this).parent().attr("id");
        closeChatBox(chatbox);
    });

    $(document).on('hover', '.esc', function () {
        $(this).css('cursor', 'pointer');
    });

    function closeChatBox(chatbox){
        $('[id = "' + chatbox + '"]').hide();

        var userID = chatbox.slice(8,chatbox.length);
        alert($.inArray(userID, users));
        users.splice($.inArray(userID, users), 1);

    }
    function displayChatBox() {
        i = 10; // start position
        j = 260;  //next position

        $.each(users, function (index, value) {
            if (index < 2) {
                $('[id="chatbox-' + value + '"]').css("right", i);

                $('[id="chatbox-' + value + '"]').show();
                i = i + j;
            }
            else {
                $('[id="chatbox-' + value + '"]').hide();
            }
        });
    }


    $(document).on('keypress', 'textarea.msg_input', function (e) {

        if (e.keyCode == 13 ) {
            $bericht = $(this).val();
            $bericht= $bericht.replace(/(\r\n|\n|\r)/gm, "");
            $(this).val("");
            // $(this).empty();
            if($bericht.trim().length != 0){

                var chatbox = $(this).parents().attr("id") ;
                $ontvanger = chatbox.slice(8,chatbox.length);

                $.post("Controller?action=PostMessage", {ontvanger: $ontvanger, bericht: $bericht }
               ,
                    function (data) {
                    // getMessages($ontvanger);

                });

                // $('<div class="msg_right"><div class = "inline_right">'+$bericht+'</div></div>').insertBefore('[id="'+chatbox+'"] .layout');
                $('<div class="msg_right">'+$bericht+'</div>').insertBefore('[id="'+chatbox+'"] .layout');

                $('.msg_body').scrollTop($('.msg_body')[0].scrollHeight);
            }

        }
    })

    function getMessages(userID){


        $.ajax({
            type: "GET",
            url: "Controller?action=GetMessages&ontvanger=" + userID,
            dataType: "json",
            success: function(json){

                $(json).each(function(index, bericht) {

                    var buser = bericht.zender;
                    if(buser.toString() == userID ){
                        // $('<div class="msg_left"><div class="inline_left">'+ bericht.bericht+'</div></div>').insertBefore('[id="chatbox-'+userID+'"] .layout');
                        $('<div class="msg_left">'+ bericht.bericht+'</div>').insertBefore('[id="chatbox-'+userID+'"] .layout');
                    }
                    else{
                        // $('<div class="msg_right"><div class="inline_right">'+ bericht.bericht+'</div></div>').insertBefore('[id="chatbox-'+userID+'"] .layout');
                        $('<div class="msg_right">'+ bericht.bericht+'</div>').insertBefore('[id="chatbox-'+userID+'"] .layout');

                    }

                    $('.msg_body').scrollTop($('.msg_body')[0].scrollHeight);
                })
            },
            error: function() {
                alert("het werkt nie...");
            }
        });
    }

    $("#specialEffect").click(function(){
        alert("yo");
        $("div #opdracht4").animate({


        });
    });

    /*function checkJson(json){
        if (oldJson != null){
            var parseOldJson =JSON.parse(oldJson);
            var parseJson = JSON.parse(json);
            $(json).each(function (index, bericht){
                $(oldJson).each(function(index2,oldbericht){
                    alert(parseJson[index].toString()+ " is ook " + parseOldJson[index2].toString())
                        if((parseJson[index].toString() == parseOldJson[index2].toString())){

                        }
                        else{
                            json.push(oldJson[index2]);
                        }
                })
            })

            alert(json.toString());
        }
        alert(json.toString());
        return json;
    }*/

    function getDiff(a, b){
        var diff = (isArray(a) ? [] : {});
        recursiveDiff(a, b, diff);
        return diff;
    }

    function recursiveDiff(a, b, node){
        var checked = [];

        for(var prop in a){
            if(typeof b[prop] == 'undefined'){
                addNode(prop, '[[removed]]', node);
            }
            else if(JSON.stringify(a[prop]) != JSON.stringify(b[prop])){
                // if value
                if(typeof b[prop] != 'object' || b[prop] == null){
                    addNode(prop, b[prop], node);
                }
                else {
                    // if array
                    if(isArray(b[prop])){
                        addNode(prop, [], node);
                        recursiveDiff(a[prop], b[prop], node[prop]);
                    }
                    // if object
                    else {
                        addNode(prop, {}, node);
                        recursiveDiff(a[prop], b[prop], node[prop]);
                    }
                }
            }
        }
    }

    function addNode(prop, value, parent){
        parent[prop] = value;
    }

    function isArray(obj){
        return (Object.prototype.toString.call(obj) === '[object Array]');
    }
});


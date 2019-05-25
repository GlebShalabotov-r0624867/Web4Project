$(document).ready(function () {

    var users = [];

    $('#friendList').on('click', 'tr td.user', function () {

        var userID = $(this).attr("id");
        var status = $(this).siblings('.status');





        if ($.inArray(userID, users) != -1)
        {
            users.splice($.inArray(userID, users), 1);
            var ell = $('[id="chatbox-'+userID+'"] .hider')
            if (ell.is(":hidden")){ ell.slideToggle('slow');

            }else $('[id="chatbox-'+userID+'"]').remove();

        }

        users.unshift(userID);

        var idChatbox = 'chatbox-'+ userID;
        var chatpopup =
            "<div class='chatbox'  id='"+ idChatbox+ "'>"

            + "<div class='chatHoofding'>" + $(this).text()
            + " </div> <div class='esc'>x</div>" +
            "<div class='hider' >"+
            "<div class='msg_body'><div class='layout''></div> </div>" +
            "<textarea class='msg_input' rows='4'></textarea></div>"
            + "</div>";


       $('body').append(chatpopup);
        /*if (status.text()== online){
            $('[id="chatbox-' + userID + '"]').css("background-color", "green");
        }*/
        //pop chat venster
        displayChatBox();


    });


    $(document).on('click', '.chatHoofding', function(){
        var chatbox = $(this).parents().attr("id") ;
        $('[id="'+chatbox+'"] .hider').slideToggle('slow');

    });

    $(document).on('click', '.esc', function () {
          var chatbox = $(this).parent().attr("id");
        closeChatBox(chatbox);
        displayChatBox();
    });



    function closeChatBox(chatbox){
        $('[id = "' + chatbox + '"]').hide();

        var userID = chatbox.slice(8,chatbox.length);

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
                getMessages(value);
            }
            else {
                $('[id="chatbox-' + value + '"]').hide();
            }
        });
        setTimeout(displayChatBox, 5000);
    }


    $(document).on('keypress', 'textarea.msg_input', function (e) {

        if (e.keyCode == 13 ) {
            $bericht = $(this).val();
            $bericht= $bericht.replace(/(\r\n|\n|\r)/gm, "");
            $(this).val("");
            // $(this).empty();
            if($bericht.trim().length != 0){

                var chatbox = $(this).parents().parents().attr("id") ;
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
                $('[id="chatbox-' + userID + '"] .msg_body').empty();
                $('[id="chatbox-' + userID + '"] .msg_body').append("<div class='layout''></div>");
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



/*

*/
                })
                $('.msg_body').scrollTop($('.msg_body')[0].scrollHeight);
            },
            error: function() {
                alert("het werkt nie...");
            }
        });

    }
});


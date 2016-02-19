var back =false;
var sessionID;
var sessionName;
$(document).ready(function(){
    window.onpopstate = function(event) {
        back = true;
        socket.emit('contentRequest',event.state.url.substring(1));
    };
});

var socket = io(":3000");

console.log(socket);



socket.on('connect',function()
{
    
});

socket.on('page-update',function(pageData)
{
    eval(pageData.js)
    //document.body.innerHTML = data.html;
    document.title = pageData.pageTitle;
    if(!back)
    {
        window.history.pushState({"pageTitle":pageData.pageTitle,"url":pageData.url},"", pageData.url);
    }
    back = false;
    //eval(data.js)
});

socket.on('newSession',function(newSession)
{
    Cookies.set('sessionID', newSession.sessionID, {expires:7});
    Cookies.set('sessionName', newSession.sessionName, {expires:7});
});

socket.on('emailfail',function(msg)
{
    console.log("Email passes");
}).on('emailPass',function(msg)
{
    console.log("Email FAiled");
}).on('accountCreated',function(msg)
{
    document.body.innerHTML = msg;
});

function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}
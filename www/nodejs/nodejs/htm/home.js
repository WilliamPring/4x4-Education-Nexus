(function()
{
	document.body.innerHTML = '{{html}}';
	socket.emit('getCourses',"");
}());


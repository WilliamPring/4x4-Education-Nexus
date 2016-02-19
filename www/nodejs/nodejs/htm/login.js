(function()
{
	document.body.innerHTML = '{{html}}';
	$('#createaccount').click(function(){
		socket.emit('contentRequest','createaccount');
	}); 
	$('#loginForm').submit(function(){
		socket.emit('login', getFormData($('form')));
		return false; 
	});
}());


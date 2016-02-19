(function()
{
	document.body.innerHTML = '{{html}}';
	$('#createAccountForm').submit(function(){
		socket.emit('create account', getFormData($('form')));
		return false;
	});
}());
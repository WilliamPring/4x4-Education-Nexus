(function()
{
	$('#courses').html('{{html}}');
	$('.course').hover(
		function(){$( this ).css("background-color","#738BD7").next().css("display","block")},
		function(){$( this ).css("background-color","#2E3136").next().css("display","none")}
	).click(function(){
		socket.emit('getSessions',$(this).attr('id').substring(6));
	});
	//Couse button listeners
}());


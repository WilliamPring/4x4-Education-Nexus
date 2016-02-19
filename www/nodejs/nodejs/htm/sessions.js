(function()
{
	$('#sessions').html('{{html}}');
	
	$('.session').hover(
		function(){$( this ).css("background-color","#738BD7").next().css("display","block")},
		function(){$( this ).css("background-color","#2E3136").next().css("display","none")}
	).click(function(){
		socket.emit('getQuestion',$(this).attr('id').substring(7));
	});
	//Couse button listeners
}());
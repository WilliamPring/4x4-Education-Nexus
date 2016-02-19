var express = require('express'),
	app = express(),
	http = require('http').Server(app),
	fs = require('fs'),
	path = __dirname,
	url = require('url'),
	io = require('socket.io')(http),
	cookie = require('cookie');
	
var pages = require('./nodejs/requestpage');
var session = require("./nodejs/session.js");
var content = require("./nodejs/content.js");
var account = require("./nodejs/account");

io.on('connection',function(socket)
{
	var pathname = url.parse(socket.handshake.headers.referer).pathname;
	session.start(socket, pathname);
	account.createaccount(socket);
	account.login(socket);
	content.contentRequestListener(socket);
	
	

	var pathname = url.parse(socket.handshake.headers.referer).pathname;


	switch(pathname)
	{
		/*
		case "/":
		//Cahnge////////////////////////////////////////s

			pages.loginpage(function(newPageInfo)
			{
				socket.emit("page-update",newPageInfo);
			});
			break;*/
		case "/home":
			socket['session']['page'] = "/home";
			content.serveHomePage(socket);
			break;
		case "/login":
			socket['session']['page'] = "/login";
			content.serveLoginPage(socket);
			break;
			/*
			socket['session']['page'] = "/login";
			pages.loginpage(function(newPageInfo)
			{
				socket.emit("page-update",newPageInfo);
			});
			break;*/
		case "/createaccount":
			socket['session']['page'] = "/createaccount";
			pages.createaccountpage(function(newPageInfo)
			{
				socket.emit("page-update",newPageInfo);
			});
			break;
		default:
			break;
	}

});




http.listen(3000);
var cookie = require('cookie');
var dbconnection = require("./focalpointdb");
var db = dbconnection.connection;
var content = require("./content");
var checkSession = function(sessionInfo, callback)
{
	var sessionID = sessionInfo['sessionID'];
	var sessionName = sessionInfo['sessionName'];
	

	db.query('select sessionID, sessionName, accountID, cookie, page from usersession where SessionID = ? and SessionName = ?', [sessionID, sessionName], function(err,rows)
	{
		if(err) 
		{
			throw err;
		}
		else
		{
			if(rows[0])
			{
				console.log();
				callback(true,rows[0]);
			}
			else
			{
				callback(false,undefined);
			}
		}
	});
}
var getSessionByAccount = function(accountID, callback)
{
	
	db.query('select cookie from usersession where accountID = ?', accountID, function(err,rows)
	{
		if(err) 
		{
			throw err;
		}
		else
		{
			if(rows[0])
			{
				callback({accountID:rows[0].accountID,cookie:rows[0].cookie});
			}
			else
			{
				console.log('session not found');
				callback(undefined);
			}
		}
	});
}
var createSession = function(sessionInfo, callback)
{
	var sessionID = sessionInfo['sessionID'];
	var sessionName = sessionInfo['sessionName'];

	var post ={sessionID:sessionID,sessionName : sessionName};
	
	db.query('insert into usersession set ?', post, function(err,result)
	{
		if(err) 
		{
			throw err;
		}
		else
		{
			callback(true);
		}
	});
}

var updateSessionID = function(sessionInfo, callback)
{
	var sessionID = sessionInfo['sessionID'];
	var sessionName = sessionInfo['sessionName'];
	var cookieData = sessionInfo['cookie'];
	//var accountID = sessionInfo['accountID'];

	var cookieString = JSON.stringify(cookieData);

	var post = {sessionID:sessionID};

	
	db.query('update usersession set ? where sessionName = ?', [post,sessionName], function(err,result)
	{
		if(err) 
		{
			throw err;
		}
		else
		{
			callback(true);
		}
	});
}

var randomSessionName = function(callback)
{
	
	db.query('call RandomSessionName()', function(err,result)
	{
		if(Array.isArray(result[0]))
		{
			callback(result[0][0].string);
		}
	});
}
var start = function(socket, path)
{
	socket['session'] = {accountID:"",sessionID:"",sessionName:"",permission:"", page:""};
	var userSession = socket['session'];
	var cookieData = {};
	console.log("1");
	if(socket.handshake.headers.cookie)
	{
		console.log("2");
		cookieData = cookie.parse(socket.handshake.headers.cookie);
		if(cookieData.sessionID)
		{
			console.log("3");
			userSession.sessionID = cookieData.sessionID;

			userSession.sessionName = cookieData.sessionName;

			console.log(socket.handshake.headers.cookie);
		}
		else
		{
			console.log("4");
			userSession['sessionID'] = socket.conn.id;
			cookieData['sessionID'] = userSession.sessionID;
		}
		
	}
	else
	{
		userSession['sessionID'] = socket.conn.id;
		cookieData['sessionID'] = userSession.sessionID;
		console.log("5");
	}


	checkSession(userSession,function(exists,userSessionData)
	{
		if(exists == true)
		{
			socket['session']['sessionID'] = userSessionData.sessionID;
			socket['session']['sessionName'] = userSessionData.sessionName;
			socket['session']['cookie'] = JSON.parse(userSessionData.cookie);
			socket['session']['accountID'] = userSessionData.accountID;
			socket['session']['page'] = userSessionData.page;

			console.log("socket loaded");
			userSession.sessionID = cookieData.io;
			updateSessionID(socket['session'],function()
			{
				socket.emit('newSession',{sessionID :userSession.sessionID , sessionName:userSession.sessionName});
				if(path == '/')
				{
					if(socket['session']['page'])
					{
						content.sendPage(socket,socket['session']['page'].substring(1));
					}
					else
					{
						content.sendPage(socket,'login');
					}
				}
			});
		}
		else
		{
			randomSessionName(function(randomString)
			{
				userSession.sessionName = randomString;

				createSession(userSession,function()
				{
					socket.emit('newSession',{sessionID :userSession.sessionID , sessionName:userSession.sessionName});
				});
			});
			content.sendPage(socket,'login');
		}
	});

	socket.on('disconnect', function () 
	{
		db.query('update usersession set ? where sessionName = ?', [{page:socket['session'].page},socket['session'].sessionName], function(err,result)
		{
			if(err) 
			{
				throw err;
			}
			else
			{
				console.log("Saved users last page " + socket['session'].page);
			}
		});
	});
}

module.exports =
{
	checkSession : checkSession,
	getSessionByAccount : getSessionByAccount,
	createSession : createSession,
	updateSessionID : updateSessionID,
	randomSessionName : randomSessionName,
	start : start
};
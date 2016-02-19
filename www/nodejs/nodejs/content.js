var fs = require('fs');
var path = __dirname;
var dbconnection = require("./focalpointdb");
var db = dbconnection.connection;

var contentRequestListener = function(socket)
{
	socket.on('contentRequest',function(msg)
	{
		sendPage(socket, msg);
	});
	socket.on('getCourses',function(msg)
	{
		var coursesInfo = {};
		var html ="";
		db.query("select courseID, name, description from course where accountID = ?", [socket['session'].accountID] , function(err,rows)
		{
			if(err)
			{
				throw err;

			}
			else
			{
				if(rows[0])
				{
					for (var i = 0; i < rows.length; i++) {
						html += '<div id="course'+ rows[i].courseID +'" class="course"> <span class="courseChar">'+ rows[i].name.charAt(0) +'</span>'+'</div>';
						html += '<div class="courseNameDiv"><span class="courseName">'+ rows[i].name +'</span></div>';
					}

					coursesInfo['pageTitle'] = "Home";
					coursesInfo['url'] = "/home";
					fs.readFile( path + '/htm/courses.js','utf8',function(err,data)
					{
						html = html.replace(/(\r\n|\n|\r)/gm," ");
						data = data.replace("{{html}}",html);
						coursesInfo['js'] = data;
						socket.emit("page-update",coursesInfo);
						socket['session']['page'] = '/home';
					});
				}
			}
		});
	});

	socket.on('getSessions',function(courseID)
	{

		var sessionsInfo = {};
		var html ="";
		db.query("select s.sessionID, s.name, s.description from session s inner join coursesession cs on cs.sessionID = s.sessionID inner join course c on c.courseID = cs.courseID where cs.courseID = ? AND c.accountID = ?", [courseID,socket['session'].accountID] , function(err,rows)
		{
			if(err)
			{
				throw err;

			}
			else
			{
				if(rows[0])
				{
					console.log(rows);
					for (var i = 0; i < rows.length; i++) {
						html += '<div id="session'+ rows[i].sessionID +'" class="session"> <span class="sessionName">'+ rows[i].name +'</span>' + '<span class="sessionDescription">'+ rows[i].description +'</span>' + '</div>';
						//html += '<span class="sessionDescription">'+ rows[i].description +'</span>';
					}

					sessionsInfo['pageTitle'] = "Home";
					sessionsInfo['url'] = "/home";
					fs.readFile( path + '/htm/sessions.js','utf8',function(err,data)
					{
						html = html.replace(/(\r\n|\n|\r)/gm," ");
						data = data.replace("{{html}}",html);
						sessionsInfo['js'] = data;
						socket.emit("page-update",sessionsInfo);
						socket['session']['page'] = '/home';
					});
				}
			}
		});
	});
	socket.on('getQuestion',function(questionID)
	{

		var sessionsInfo = {};
		var html ="";
		db.query("select q.questionID, q.name, q.questionContent from question q inner join sessionquestion sq on sq.questionID = q.questionID inner join coursesession cs on cs.sessionID = sq.sessionID inner join course c on c.courseID = cs.courseID where q.questionID = ? AND c.accountID = ?", [questionID,socket['session'].accountID] , function(err,rows)
		{
			if(err)
			{
				throw err;

			}
			else
			{
				if(rows[0])
				{
					console.log(rows);
					for (var i = 0; i < rows.length; i++) {
						html += '<div id="Question'+ rows[i].sessionID +'" class="question"> <span class="sessionName">'+ rows[i].name +'</span>' + '<span class="sessionDescription">'+ rows[i].description +'</span>' + '</div>';
						//html += '<span class="sessionDescription">'+ rows[i].description +'</span>';
					}

					sessionsInfo['pageTitle'] = "Home";
					sessionsInfo['url'] = "/home";
					fs.readFile( path + '/htm/sessions.js','utf8',function(err,data)
					{
						html = html.replace(/(\r\n|\n|\r)/gm," ");
						data = data.replace("{{html}}",html);
						sessionsInfo['js'] = data;
						socket.emit("page-update",sessionsInfo);
						socket['session']['page'] = '/home';
					});
				}
			}
		});
	});
};

var serveHomePage = function(socket)
{
	fs.readFile( path + '/htm/home.html','utf8',function(err,data)
	{
		if(err)
		{
			console.log(err);
		}
		else
		{
			var html = data;
			var newPageInfo = {};
			newPageInfo['pageTitle'] = "Home";
			newPageInfo['url'] = "/home";
			fs.readFile( path + '/htm/home.js','utf8',function(err,data)
			{
				html = html.replace(/(\r\n|\n|\r)/gm," ");
				data = data.replace("{{html}}",html);
				newPageInfo['js'] = data;
				socket.emit("page-update",newPageInfo);
				socket['session']['page'] = '/home';
			});
		}
	});
};

var serveLoginPage = function(socket)
{
	fs.readFile( path + '/htm/login.html','utf8',function(err,data)
	{
		if(err)
		{
			console.log(err);
		}
		else
		{
			var html = data;
			var newPageInfo = {};
			newPageInfo['pageTitle'] = "login";
			newPageInfo['url'] = "/login";
			fs.readFile( path + '/htm/login.js','utf8',function(err,data)
			{
				html = html.replace(/(\r\n|\n|\r)/gm," ");
				data = data.replace("{{html}}",html);
				newPageInfo['js'] = data;
				socket.emit("page-update",newPageInfo);
				socket['session']['page'] = '/login';
			});
		}
	});
};

var sendPage = function(socket, request)
{
	switch(request)
	{
		case "createaccount":
			fs.readFile( path + '/htm/createaccount.html','utf8',function(err,data)
			{
				if(err)
				{
					console.log(err);
				}
				else
				{
					var html = data;
					var newPageInfo = {};
					newPageInfo['pageTitle'] = "Create Account";
					newPageInfo['url'] = "/createaccount";
					fs.readFile( path + '/htm/createaccount.js','utf8',function(err,data)
					{
						html = html.replace(/(\r\n|\n|\r)/gm," ");
						data = data.replace("{{html}}",html);
						newPageInfo['js'] = data;
						socket.emit("page-update",newPageInfo);
						//socket['session']['page'] = '/createaccount';
					});
				}
			});
			break;
		case "login":
			fs.readFile( path + '/htm/login.html','utf8',function(err,data)
			{
				if(err)
				{
					console.log(err);
				}
				else
				{
					var html = data;
					var newPageInfo = {};
					newPageInfo['pageTitle'] = "Login";
					newPageInfo['url'] = "/login";
					fs.readFile( path + '/htm/login.js','utf8',function(err,data)
					{
						html = html.replace(/(\r\n|\n|\r)/gm," ");
						data = data.replace("{{html}}",html);
						newPageInfo['js'] = data;
						socket.emit("page-update",newPageInfo);

						socket['session']['page'] = '/login';
					});
				}
			});
			break;
		case "home":
			serveHomePage(socket);
			break;
		default:
			break;
	}
}
module.exports =
{
	contentRequestListener : contentRequestListener,
	serveHomePage : serveHomePage,
	serveLoginPage : serveLoginPage,
	sendPage : sendPage
};
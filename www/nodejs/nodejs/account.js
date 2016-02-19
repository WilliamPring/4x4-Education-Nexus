var content = require('./content.js');
var dbconnection = require("./focalpointdb");
var db = dbconnection.connection;
module.exports.createaccount = function(socket)
{
	socket.on('create account',function(data)
	{
		var firstname = data['firstname'];
		var lastname = data['lastname'];
		var email = data['email'];
		var pass1 = data['password'];
		var pass2 = data['repassword'];

		if(!firstname || !lastname || !email || !pass1 || !pass2 || pass1 != pass2)
		{
			return false;
		}else
		{
			var bcrypt = require('bcrypt');
			bcrypt.genSalt(10, function(err, salt) {
			    bcrypt.hash(pass1, salt, function(err, hash) {
			        var post =[firstname,lastname,email,hash,"Student"];

					//var dbconnection = require("./focalpointdb");
					//var db = dbconnection.connection;

					db.query("call CreateAccount(?,?,?,?,?)", post, function(err,result)
					{
						var error;
						if(result[0] === [])
						{
							error = result[0][0].error;
						}
						if(error) 
						{
							console.log(err.errno);
						}
						else
						{
							console.log("Created CreateAccount");
						}
					});
			    });
			});
		}
	});
}


module.exports.login = function(socket)
{
	socket.on('login',function(data)
	{
		var bcrypt = require('bcrypt');
		var email = data['email'];
		var password = data['password'];
		if(!email || !password)
		{
			return false;
		}else
		{
			var post ={};
			post['EmailAddress'] = email;

			var dbconnection = require("./focalpointdb");
			var db = dbconnection.connection;
			//need stored procedure / create structure
			
			db.query('select accountID, password, permissionID, verified from account where EmailAddress = ?', email, function(err,rows)
			{
				if(err) 
				{
					throw err;
				}
				else
				{
					if(rows[0])
					{
						bcrypt.compare(password, rows[0].password, function(err, res) {
							if(res == true)
							{
								socket['session'].accountID = rows[0].accountID; 
								socket['session']['permissionID'] = rows[0].permissionID; 
								db.query('update usersession set ? where sessionName = ? and sessionId = ?',[{accountID : socket['session'].accountID},socket['session'].sessionName,socket['session'].sessionID],function(err,res)
								{
									if(err)
									{
										throw err;
									}
									else
									{
										console.log("accountID updated: " + socket['session'].sessionID);
									}
								});


								if(rows[0].permissionID ==  1) // Teacher
								{
									if(rows[0].verified == 0)
									{
										console.log("Teacher not verified");
									}
									else
									{
										content.serveHomePage(socket);
										//socket.emit("page-update","<h1>Logged in</h1>");
									}
								}
								else if(rows[0].permissionID == 2)//student
								{
									if(rows[0].verified == 0)
									{
										console.log("Student not verified");
									}
									else
									{
										console.log("student verified");
									}
								}
							}
							else
							{
								console.log("pass failed");
								//pass failed
							}
						});
					}
					else
					{
						console.log("user name failed");
						//user name failed
					}
				}
			});
		}
	});
}
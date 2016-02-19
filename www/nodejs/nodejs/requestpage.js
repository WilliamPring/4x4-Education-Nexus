var fs = require('fs'),
path = __dirname;
module.exports.loginpage = function(callback)
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
			newPageInfo['pageTitle'] = "Login";
			newPageInfo['url'] = "/login";
			fs.readFile( path + '/htm/login.js','utf8',function(err,data)
			{
				html = html.replace(/(\r\n|\n|\r)/gm," ");
				data = data.replace("{{html}}",html);
				newPageInfo['js'] = data;
				callback(newPageInfo);
			});
		}
	});
}
module.exports.createaccountpage = function(callback)
{
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
				callback(newPageInfo);
			});
			
		}
	});
}
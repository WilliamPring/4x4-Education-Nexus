var mysql      = require('mysql');
var dbpass = require('./no/dbinfo');
var connection = mysql.createConnection({
    host     : '127.0.0.1',
    user     : 'root',
    password : dbpass.dbpassword,
    database    : 'focalpoint_education'
});

connection.connect(function(err) {
    if (err) {
    console.error('error connecting: ' + err.stack);
    return;
}});

module.exports.connection = connection;
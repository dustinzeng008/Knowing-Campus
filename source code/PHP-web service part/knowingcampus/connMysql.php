<?php 
	include("Json.php");
	//set database
	$db_host = "localhost";
	$db_username = "root";
	$db_password = "084232";
	$db_name="KnowingCampus";
	
        //connect server
	$db_link = @mysql_connect($db_host, $db_username, $db_password);
	if (!$db_link) die("connect server fail！");
	
	$selectdb = @mysql_select_db($db_name);
	if (!$selectdb) die("select database fail！");
	
	mysql_query("SET NAMES 'utf8'");
?>
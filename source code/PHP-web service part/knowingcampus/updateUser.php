<?php 
	include("connMysql.php");
	
	$sql_query = "update user set description='$_POST[description]',qq='$_POST[qq]',
	email='$_POST[email]',phone='$_POST[phone]' where id='$_POST[id]';";
	$result = mysql_query($sql_query);
	
    mysql_close();
?>
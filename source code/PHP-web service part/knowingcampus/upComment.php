<?php 
	include("connMysql.php");
	
	$sql_query = "insert into comment values(null,'$_POST[create_at]','$_POST[content]','$_POST[uid]','$_POST[sid]');";
	$result = mysql_query($sql_query);
	
	
    mysql_close();
?>
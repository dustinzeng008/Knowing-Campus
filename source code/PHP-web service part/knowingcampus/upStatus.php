<?php 
	include("connMysql.php");
	
        $sql_query = "insert into status values(null,'$_POST[create_at]','$_POST[content]','$_POST[uid]','$_POST[scid]');";
//$sql_query = "insert into status values('','2015-03-23 11:24','haha','10001','1');";
	$result = mysql_query($sql_query);
	
	
    mysql_close();
?>
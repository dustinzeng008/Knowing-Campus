<?php 
	include("connMysql.php");
	
	$sql_query = "select * from user where id='$_POST[queryUserId]';";
	$result = mysql_query($sql_query);
	
	while($row_result=mysql_fetch_assoc($result))
	{
		$reponse[]=$row_result;
	}
	
	echo Array2Json($reponse);
	
    mysql_close();
	
?>
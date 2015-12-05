<?php 
	include("connMysql.php");
	
	$sql_query = "select * from user where id='$_POST[userID]' and password='$_POST[userPassword]';";
	//$sql_query = "select * from user where id='8000110210' and password='123451';";
	$result = mysql_query($sql_query);
	$resultnum=mysql_numrows($result);
	if($resultnum)
	{
		while($row_result=mysql_fetch_assoc($result))
		{
			$reponse[]=$row_result;
		}
		echo Array2Json($reponse);
	}
	else
	{
		echo "null";
	}
    mysql_close();
?>
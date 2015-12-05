<?php 
	include("connMysql.php");
	
	$sql_query = "select comment.id,created_at,content,uid,sid,name,profile_image_url from comment,user where sid='$_POST[sid]' and comment.uid=user.id;";
	//select comment.id,create_at,content,uid,sid,name,profile_image_url from comment,user where sid='1' and comment.uid=user.id
	$result = mysql_query($sql_query);
	
	while($row_result=mysql_fetch_assoc($result))
	{
		$reponse[]=$row_result;
	}
	
	echo Array2Json($reponse);
	
    mysql_close();
?>
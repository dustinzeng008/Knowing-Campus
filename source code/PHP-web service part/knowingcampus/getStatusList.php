<?php 
	include("connMysql.php");
	
	//$sql_query = "select status.id,name,profile_image_url,created_at,content,uid,scid from status,user where user.id=status.uid;";
	//select count(sid) from comment where sid='1'
	
	
	$sql_query ="select status.id,name,profile_image_url,created_at,content,uid,scid,
	(select count(sid)  from comment where sid=status.id)  as commentnum 
	from status,user where user.id=status.uid;";
	$result = mysql_query($sql_query);
	
	while($row_result=mysql_fetch_assoc($result))
	{
		$reponse[]=$row_result;
	}
	
	echo Array2Json($reponse);
	
    mysql_close();
?>
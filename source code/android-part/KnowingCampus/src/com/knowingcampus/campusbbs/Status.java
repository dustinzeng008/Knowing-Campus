package com.knowingcampus.campusbbs;

import java.io.Serializable;

public class Status implements Serializable {
	private int id=0;
	private String name="";  
    private String content="";  
    private String userPicDir="";  
    private String publicTime="";
    private String uid="";
    private int scid=0;
    private int commentnum=0;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getUserPicDir() {
		return userPicDir;
	}
	public void setUserPicDir(String userPicDir) {
		this.userPicDir = userPicDir;
	}
	public String getPublicTime() {
		return publicTime;
	}
	public void setPublicTime(String publicTime) {
		this.publicTime = publicTime;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getScid() {
		return scid;
	}
	public void setScid(int scid) {
		this.scid = scid;
	}
	public int getCommentnum() {
		return commentnum;
	}
	public void setCommentnum(int commentnum) {
		this.commentnum = commentnum;
	}

	
}

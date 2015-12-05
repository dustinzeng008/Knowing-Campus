package com.knowingcampus.campusbbs;

public class Comment {
	private int id=0;
	private String name="";  
    private String content="";  
    private String userPicDir="";  
    private String publicTime="";
    private String uid="";
    private int sid=0;
    private int lounum=0;
    
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
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getLounum() {
		return lounum;
	}
	public void setLounum(int lounum) {
		this.lounum = lounum;
	}
    
    
}

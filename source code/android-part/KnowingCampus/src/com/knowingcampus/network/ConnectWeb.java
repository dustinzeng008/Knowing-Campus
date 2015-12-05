package com.knowingcampus.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.knowingcampus.campusbbs.Comment;
import com.knowingcampus.campusbbs.Status;
import com.knowingcampus.config.WebConfig;
import com.knowingcampus.datashare.DataShare;
import com.knowingcampus.datashare.User;

public class ConnectWeb {
	private String url=""; 
	private String userID="";
	private String userPassword="";
	private Status theStatus;
	private Comment theComment;
	private List<Status>theStatusList;
	private List<Comment>theCommentList;
	private int statesid;
	private List<Status>theMyStatusList;
	
	private String queryUserId="";
	private User theQueryUser;
	
	
	//user login
	public void userLogin(String userID,String userPassword)
	{
		this.userID=userID;
		this.userPassword=userPassword;
		url=WebConfig.url+"/login.php";
		System.out.println("---"+url);
		ConnUserLogin cus=new ConnUserLogin();
		cus.start();
		synchronized(cus) 
		{
			try 
			{
				cus.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		}
	}
	
	//user login thread
	class ConnUserLogin extends Thread
	{
		public void run()
		{
			synchronized(this)
			{
				try 
				{
					ArrayList nameValuesPairs=new ArrayList();
					nameValuesPairs.add(new BasicNameValuePair("userID",userID));
					nameValuesPairs.add(new BasicNameValuePair("userPassword",userPassword));
					
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httpPost=new HttpPost(url);

					System.out.println("---1---");
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuesPairs));
					System.out.println("---2---");
					HttpResponse response=httpclient.execute(httpPost);
					System.out.println("---3---");
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
					{
						String jsonstr=EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
						System.out.println("jsonstr="+jsonstr);
						
						if(!jsonstr.equals("null"))
						{
							System.out.println("not null");
							try 
							{
								JSONArray jay=new JSONArray(jsonstr);
								for(int i=0;i<jay.length();i++)
								{
									JSONObject jsonLogin=(JSONObject)jay.get(i);	
									DataShare.currentUser=new User();
									DataShare.currentUser.setId(jsonLogin.getString("id"));
									DataShare.currentUser.setName(jsonLogin.getString("name"));
									DataShare.currentUser.setPassword(jsonLogin.getString("password"));
									DataShare.currentUser.setGender(jsonLogin.getString("gender"));
									DataShare.currentUser.setEmail(jsonLogin.getString("email"));
									DataShare.currentUser.setPhone(jsonLogin.getString("phone"));
									DataShare.currentUser.setDescription(jsonLogin.getString("description"));
									DataShare.currentUser.setProfile_image_url(jsonLogin.getString("profile_image_url"));
									DataShare.currentUser.setMajor(jsonLogin.getString("major"));
									DataShare.currentUser.setEnter_year(jsonLogin.getString("enter_year"));
								}
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.toString());
							}
						}
						else
						{
							System.out.println("is null");
							DataShare.currentUser=null;
						}
					}
				} 
				catch(Exception e)
				{
					System.out.println(e.toString());
				}
				notify();
			}
		}
	}
	
	//Post Status
	public void UpStatusToServer(Status theStatus)
	{
		this.theStatus=theStatus;
		url=WebConfig.url+"/upStatus.php";
		System.out.println(url);
		
		CUpStatusToServer custs=new CUpStatusToServer();
		custs.start();
		synchronized(custs) 
		{
			try 
			{
				custs.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		}
	}
	
	//Post Status thread
	class CUpStatusToServer extends Thread
	{
		public void run()
		{
			synchronized(this)
			{
				try 
				{
					ArrayList nameValuesPairs=new ArrayList();
					System.out.println(theStatus.getPublicTime()+"---"+theStatus.getContent()+"---"+theStatus.getUid()+"---"+theStatus.getScid());
					nameValuesPairs.add(new BasicNameValuePair("create_at",theStatus.getPublicTime()));
					nameValuesPairs.add(new BasicNameValuePair("content",theStatus.getContent()));
					nameValuesPairs.add(new BasicNameValuePair("uid",theStatus.getUid()));
					nameValuesPairs.add(new BasicNameValuePair("scid",theStatus.getScid()+""));
					
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httpPost=new HttpPost(url);
					
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuesPairs,HTTP.UTF_8));
					HttpResponse response=httpclient.execute(httpPost);
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
					{
					}
				} 
				catch(Exception e)
				{
					System.out.println("Up Status to Server"+e.toString());
				}
				notify();
			}
		}
	}

	//Get Status List
	public List<Status> getStatusList()
	{
		theStatusList=new ArrayList<Status>();
		url=WebConfig.url+"/getStatusList.php";
		CGetStatusList cgsl=new CGetStatusList();
		cgsl.start();
		synchronized(cgsl) 
		{
			try 
			{
				cgsl.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		}
		return theStatusList;
	}
	
	//Get Status List Thread
	class CGetStatusList extends Thread
	{
		public void run() 
		{
			synchronized(this)
			{
				try 
				{
					
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httpPost=new HttpPost(url);	
					HttpResponse response=httpclient.execute(httpPost);
					
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
					{
						String jsonstr=EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
						System.out.println(jsonstr);
						if(!jsonstr.equals("null"))
						{
							try 
							{
								JSONArray jay=new JSONArray(jsonstr);
								for(int i=0;i<jay.length();i++)
								{
									JSONObject the_json=(JSONObject)jay.get(i);
									
									Status theStatus=new Status();
									theStatus.setId(Integer.parseInt(the_json.getString("id")));
									theStatus.setName(the_json.getString("name"));
									theStatus.setUserPicDir(the_json.getString("profile_image_url"));
									theStatus.setPublicTime(the_json.getString("created_at"));
									theStatus.setContent(the_json.getString("content"));
									theStatus.setUid(the_json.getString("uid"));
									theStatus.setScid(Integer.parseInt(the_json.getString("scid")));
									theStatus.setCommentnum(Integer.parseInt(the_json.getString("commentnum")));
									theStatusList.add(0,theStatus);
								}
							} catch (Exception e) {
								System.out.println("Get Status List---"+e.toString());
							}
						}
						else{
							System.out.println("sorry, there is no status");
						}
					}
				} 
				catch(Exception e)
				{
					System.out.println("out---"+e.toString());
				}
				notify();
			}
		}
	}
	
	//Public Comment
	public void UpCommentToServer(Comment theComment)
	{
		this.theComment=theComment;
		url=WebConfig.url+"/upComment.php";
		
		CUpCommentToServer cucts=new CUpCommentToServer();
		cucts.start();
		synchronized(cucts) 
		{
			try 
			{
				cucts.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		}
	}
	
	//Public Comment Thread
	class CUpCommentToServer extends Thread
	{
		public void run()
		{
			synchronized(this)
			{
				try 
				{
					ArrayList nameValuesPairs=new ArrayList();
					nameValuesPairs.add(new BasicNameValuePair("create_at",theComment.getPublicTime()));
					nameValuesPairs.add(new BasicNameValuePair("content",theComment.getContent()));
					nameValuesPairs.add(new BasicNameValuePair("uid",theComment.getUid()));
					nameValuesPairs.add(new BasicNameValuePair("sid",theComment.getSid()+""));
					
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httpPost=new HttpPost(url);
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuesPairs,HTTP.UTF_8));
					
					HttpResponse response=httpclient.execute(httpPost);
					
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
					{
					
					}
				} 
				catch(Exception e)
				{
					System.out.println(e.toString());
				}
				notify();
			}
		}
	}

	//Get Comment List
	public List<Comment> getCommentList(int statesid)
	{
		this.statesid=statesid;
		theCommentList=new ArrayList<Comment>();
		url=WebConfig.url+"/getCommentList.php";
		CGetCommentList cgcl=new CGetCommentList();
		cgcl.start();
		synchronized(cgcl) 
		{
			try 
			{
				cgcl.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		}
		return theCommentList;
	}

	//Get Comment List Thread
	class CGetCommentList extends Thread
	{
		public void run() 
		{
			synchronized(this)
			{
				try 
				{
					ArrayList nameValuesPairs=new ArrayList();
					nameValuesPairs.add(new BasicNameValuePair("sid",statesid+""));
					
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httpPost=new HttpPost(url);	
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuesPairs,HTTP.UTF_8));
					HttpResponse response=httpclient.execute(httpPost);
					
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
					{
						String jsonstr=EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
						
						if(!jsonstr.equals("null"))
						{
							try 
							{
								JSONArray jay=new JSONArray(jsonstr);
								for(int i=0;i<jay.length();i++)
								{
									JSONObject the_json=(JSONObject)jay.get(i);
									
									Comment theComment=new Comment();
									
									theComment.setId(Integer.parseInt(the_json.getString("id")));
									theComment.setName(the_json.getString("name"));
									theComment.setUserPicDir(the_json.getString("profile_image_url"));
									theComment.setPublicTime(the_json.getString("created_at"));
									theComment.setContent(the_json.getString("content"));
									theComment.setUid(the_json.getString("uid"));
									theComment.setSid(Integer.parseInt(the_json.getString("sid")));
									theCommentList.add(0,theComment);
								}
							} catch (Exception e) {
								System.out.println("in---"+e.toString());
							}
						}
					}
				} 
				catch(Exception e)
				{
					System.out.println("out---"+e.toString());
				}
				notify();
			}
		}
	}
	
	//Get Personal Status List
	public List<Status> getMyStatusList()
	{
		theMyStatusList=new ArrayList<Status>();
		url=WebConfig.url+"/getMyStatusList.php";
		CGetMyStatusList cgmsl=new CGetMyStatusList();
		cgmsl.start();
		synchronized(cgmsl) 
		{
			try 
			{
				cgmsl.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		}
		return theMyStatusList;
	}
	
	//Get Personal Status List Thread
	class CGetMyStatusList extends Thread
	{
		public void run() 
		{
			synchronized(this)
			{
				try 
				{
					ArrayList nameValuesPairs=new ArrayList();
					nameValuesPairs.add(new BasicNameValuePair("uid",DataShare.currentUser.getId()));
					
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httpPost=new HttpPost(url);	
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuesPairs,HTTP.UTF_8));
					HttpResponse response=httpclient.execute(httpPost);
					
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
					{
						String jsonstr=EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
						
						if(!jsonstr.equals("null"))
						{
							try 
							{
								JSONArray jay=new JSONArray(jsonstr);
								for(int i=0;i<jay.length();i++)
								{
									JSONObject the_json=(JSONObject)jay.get(i);
									
									Status theStatus=new Status();
									theStatus.setId(Integer.parseInt(the_json.getString("id")));
									theStatus.setName(the_json.getString("name"));
									theStatus.setUserPicDir(the_json.getString("profile_image_url"));
									theStatus.setPublicTime(the_json.getString("created_at"));
									theStatus.setContent(the_json.getString("content"));
									theStatus.setUid(the_json.getString("uid"));
									theStatus.setScid(Integer.parseInt(the_json.getString("scid")));
									theMyStatusList.add(0,theStatus);
								}
							} catch (Exception e) {
								System.out.println("in---"+e.toString());
							}
						}
					}
				} 
				catch(Exception e)
				{
					System.out.println("out---"+e.toString());
				}
				notify();
			}
		}
	}
	
	
	//Query User Information
	public User queryGetUser(String queryUserId)
	{
		theQueryUser=new User();
		this.queryUserId=queryUserId;
		url=WebConfig.url+"/queryGetUser.php";
		CQueryGetUser cqgs=new CQueryGetUser();
		cqgs.start();
		synchronized(cqgs) 
		{
			try 
			{
				cqgs.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		}
		return theQueryUser;
	}
	
	//Query User Information Thread
	class CQueryGetUser extends Thread
	{
		public void run()
		{
			synchronized(this)
			{
				try 
				{
					ArrayList nameValuesPairs=new ArrayList();
					nameValuesPairs.add(new BasicNameValuePair("queryUserId",queryUserId));
					
					HttpClient httpclient=new DefaultHttpClient();
					HttpPost httpPost=new HttpPost(url);
					
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuesPairs));
					
					HttpResponse response=httpclient.execute(httpPost);
					
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
					{
						String jsonstr=EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
						
						if(!jsonstr.equals("null"))
						{
							try 
							{
								JSONArray jay=new JSONArray(jsonstr);
								for(int i=0;i<jay.length();i++)
								{
									JSONObject jsonLogin=(JSONObject)jay.get(i);	
									
									theQueryUser.setId(jsonLogin.getString("id"));
									theQueryUser.setName(jsonLogin.getString("name"));
									theQueryUser.setPassword(jsonLogin.getString("password"));
									theQueryUser.setGender(jsonLogin.getString("gender"));
									theQueryUser.setEmail(jsonLogin.getString("email"));
									theQueryUser.setPhone(jsonLogin.getString("phone"));
									theQueryUser.setDescription(jsonLogin.getString("description"));
									theQueryUser.setProfile_image_url(jsonLogin.getString("profile_image_url"));
									theQueryUser.setMajor(jsonLogin.getString("major"));
									theQueryUser.setEnter_year(jsonLogin.getString("enter_year"));
								}
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e.toString());
							}
						}
						else
						{
						}
					}
				} 
				catch(Exception e)
				{
					System.out.println(e.toString());
				}
				notify();
			}
		}
	}
	
	
}

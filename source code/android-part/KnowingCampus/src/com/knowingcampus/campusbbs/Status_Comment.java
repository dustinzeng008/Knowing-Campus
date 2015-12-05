package com.knowingcampus.campusbbs;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.knowingcampus.R;
import com.knowingcampus.config.WebConfig;
import com.knowingcampus.datashare.BitmapCache;
import com.knowingcampus.datashare.QueryGetUser;
import com.knowingcampus.network.ConnectWeb;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class Status_Comment extends Activity{
	
	private ActionBar mActionBar;
	private Status theStatus=null;
	private NetworkImageView statuscomment_pic;
	private TextView statuscomment_name;
	private TextView statuscomment_publictime;
	private TextView statuscomment_content;
	private ListView statuscomment_listview;
	
	private RequestQueue mQueue;
    private ImageLoader mImageLoader;
    
    private List<Comment> commentList = new ArrayList<Comment>();
	private ConnectWeb theConnectWeb = new ConnectWeb();
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		System.out.println("Status_Comment");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status_comment);
		
		mActionBar = getActionBar();
		//mActionBar.setNavigationMode(ActionBar.DISPLAY_SHOW_HOME);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setLogo(R.drawable.knowingcampus);
		mActionBar.setTitle("status detail");
		
		//get status from previous activity
		Bundle bundle=this.getIntent().getExtras();
		theStatus=(Status)bundle.getSerializable("theStatus");
		
		mQueue = Volley.newRequestQueue(this);
		mImageLoader = new ImageLoader(mQueue, new BitmapCache());
		
		statuscomment_pic=(NetworkImageView)findViewById(R.id.statuscomment_pic);
		statuscomment_name=(TextView)findViewById(R.id.statuscomment_name);
		statuscomment_publictime=(TextView)findViewById(R.id.statuscomment_publictime);
		statuscomment_content=(TextView)findViewById(R.id.statuscomment_content);
		statuscomment_listview=(ListView)findViewById(R.id.statuscomment_listview);
		
		String imageUrl=WebConfig.url+theStatus.getUserPicDir();
		statuscomment_pic.setTag("url");  
		statuscomment_pic.setImageUrl(imageUrl,mImageLoader);
		statuscomment_pic.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent it=new Intent();
				Bundle bundle=new Bundle();
				it.setClass(Status_Comment.this,QueryGetUser.class);
				bundle.putString("queryUserId",theStatus.getUid());
				it.putExtras(bundle);
				startActivity(it);
			}
		});
		statuscomment_name.setText(theStatus.getName());
		statuscomment_publictime.setText(theStatus.getPublicTime());
		statuscomment_content.setText(theStatus.getContent());
		
		fillcommentlistview();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		fillcommentlistview();
		super.onResume();
	}
	
	private void fillcommentlistview()
	{
		//get comment list
		commentList=theConnectWeb.getCommentList(theStatus.getId());	
		int count=commentList.size();   //get the number of comment
		//set what floor the comment is 
		for(int i=0;i<commentList.size();i++)
		{
			commentList.get(i).setLounum(count--);
		}
		
		StatusCommentLVAdapter mAdapter=new StatusCommentLVAdapter(commentList,this); 
		statuscomment_listview.setAdapter(mAdapter);
		
		statuscomment_listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent it=new Intent();
	        	it.setClass(Status_Comment.this,PublicComment.class);
				Bundle bundle=new Bundle();
				bundle.putInt("statusid",theStatus.getId());
				bundle.putInt("commentid",commentList.get(arg2).getLounum());
				it.putExtras(bundle);
				startActivity(it);
				
			}
		});
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.addcomment, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
        	finish();
        	return true;
        case R.id.action_addcomment:
        	
        	Intent it=new Intent();
        	it.setClass(Status_Comment.this,PublicComment.class);
			Bundle bundle=new Bundle();
			bundle.putInt("statusid",theStatus.getId());
			bundle.putInt("commentid",0);
			it.putExtras(bundle);
			startActivity(it);
				
        	return true;
        } 
        return super.onOptionsItemSelected(item);
    }
}

package com.knowingcampus.campusbbs;

import java.text.SimpleDateFormat;

import com.knowingcampus.R;
import com.knowingcampus.datashare.DataShare;
import com.knowingcampus.network.ConnectWeb;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class PublicComment extends Activity{
	private ActionBar mActionBar;
	private int statusid;
	private int commentid;
	private EditText mEditTextCommentContent;
	
	private ConnectWeb theConnectWeb = new ConnectWeb();
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publiccomment);
		
		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.DISPLAY_SHOW_TITLE);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setLogo(R.drawable.knowingcampus);
		mActionBar.setTitle("public comment");
		
		mEditTextCommentContent=(EditText)findViewById(R.id.et_comment_content);
		
		Bundle bundle=this.getIntent().getExtras();
		statusid=bundle.getInt("statusid");
		commentid=bundle.getInt("commentid");
		
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.puhlic_state, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
        	finish();
        	return true;
        case R.id.action_public_state:
        	String theContent=mEditTextCommentContent.getText().toString().trim();
        	if(!theContent.equals(""))
        	{
        		Comment theComment=new Comment();
        		
        		SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");     
        		String   date   =   sDateFormat.format(new   java.util.Date());
        		theComment.setPublicTime(date);
        		if(commentid==0)
        		{
        			theComment.setContent(theContent);
        		}
        		else
        		{
        			theComment.setContent("reply"+commentid+"floor: "+theContent);
        		}
        		
        		theComment.setUid(DataShare.currentUser.getId());
        		theComment.setSid(statusid);
        		
        		theConnectWeb.UpCommentToServer(theComment);
        		Toast.makeText(PublicComment.this,"public success!",Toast.LENGTH_SHORT).show();
        		finish();
        	}
        	else
        	{
        		Toast.makeText(PublicComment.this,"content should not be empty!",Toast.LENGTH_SHORT).show();
        	}
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

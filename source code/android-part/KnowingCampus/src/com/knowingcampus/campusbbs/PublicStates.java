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

public class PublicStates extends Activity{
	private ActionBar mActionBar;
	private EditText mEditTextStatesContext;
	private Status theStatus;
	private int statecategory;
	private ConnectWeb theConnectWeb = new ConnectWeb();
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publicstates);
		
		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.DISPLAY_SHOW_TITLE);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setLogo(R.drawable.knowingcampus);
		mActionBar.setTitle("create content");
		
		mEditTextStatesContext=(EditText)findViewById(R.id.state_content);
		
		statecategory=getIntent().getIntExtra("statecategory",0);
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
        	String theContent=mEditTextStatesContext.getText().toString().trim();
        	if(!theContent.equals(""))
        	{
        		theStatus=new Status();
        		SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");     
        		String   date   =   sDateFormat.format(new   java.util.Date());
        		theStatus.setPublicTime(date);
        		theStatus.setContent(theContent);
        		theStatus.setUid(DataShare.currentUser.getId());
        		theStatus.setScid(statecategory+1);
        		theConnectWeb.UpStatusToServer(theStatus);
        		Toast.makeText(PublicStates.this,"public success!",Toast.LENGTH_SHORT).show();
        		finish();
        	}
        	else
        	{
        		Toast.makeText(PublicStates.this,"content should not be empty!",Toast.LENGTH_SHORT).show();
        	}
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

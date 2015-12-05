package com.knowingcampus.datashare;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.knowingcampus.R;
import com.knowingcampus.config.WebConfig;
import com.knowingcampus.network.ConnectWeb;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class QueryGetUser extends Activity{
	private String queryUserId;
	private ActionBar mActionBar;
	
	private NetworkImageView querygetuser_image;
	private TextView querygetuser_name;
	private TextView querygetuser_major;
	private TextView querygetuser_enteryear;
	private TextView querygetuser_description;
	private TextView querygetuser_email;
	private TextView querygetuser_phone;
	
	private ConnectWeb theConnectWeb = new ConnectWeb();
	private User theQueryUser;
	
	private RequestQueue mQueue;
    private ImageLoader mImageLoader;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.querygetuser);
		
		//Get Status from previous Activity
		Bundle bundle=this.getIntent().getExtras();
		queryUserId=bundle.getString("queryUserId");
		
		mActionBar = getActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setLogo(R.drawable.knowingcampus);
		mActionBar.setTitle("Personal Detail");
		
		querygetuser_image=(NetworkImageView)findViewById(R.id.querygetuser_image);
		querygetuser_name=(TextView)findViewById(R.id.querygetuser_name);
		querygetuser_major=(TextView)findViewById(R.id.querygetuser_major);
		querygetuser_enteryear=(TextView)findViewById(R.id.querygetuser_enteryear);
		querygetuser_description=(TextView)findViewById(R.id.querygetuser_description);
		querygetuser_email=(TextView)findViewById(R.id.querygetuser_email);
		querygetuser_phone=(TextView)findViewById(R.id.querygetuser_phone);
		
		theQueryUser=theConnectWeb.queryGetUser(queryUserId);
		
		mQueue = Volley.newRequestQueue(this);
		mImageLoader = new ImageLoader(mQueue, new BitmapCache());
		String imageUrl=WebConfig.url+theQueryUser.getProfile_image_url();  
		querygetuser_image.setImageUrl(imageUrl,mImageLoader);
		
		querygetuser_name.setText(theQueryUser.getName());
		querygetuser_major.setText(theQueryUser.getMajor());
		querygetuser_enteryear.setText(theQueryUser.getEnter_year());
		querygetuser_description.setText(theQueryUser.getDescription());
		querygetuser_email.setText(theQueryUser.getEmail());
		querygetuser_phone.setText(theQueryUser.getPhone());
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
        	finish();
        	return true;
        } 
        return super.onOptionsItemSelected(item);
    }

}

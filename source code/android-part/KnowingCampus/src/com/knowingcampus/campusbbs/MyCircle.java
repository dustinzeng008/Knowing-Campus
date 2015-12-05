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
import com.knowingcampus.datashare.DataShare;
import com.knowingcampus.datashare.QueryGetUser;
import com.knowingcampus.network.ConnectWeb;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MyCircle extends Activity {
	private ActionBar mActionBar;
	private MyCircleLVAdapter mAdapter;
	private ConnectWeb theConnectWeb = new ConnectWeb();
	private List<Status> statusList = new ArrayList<Status>();
	
	private ListView mListView;
	private NetworkImageView mycircle_image;
	private TextView mycircle_name;
	
	private RequestQueue mQueue;
    private ImageLoader mImageLoader;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycircle);
		
		mListView=(ListView)findViewById(R.id.mycircle_lv);
		mycircle_image=(NetworkImageView)findViewById(R.id.mycircle_image);
		mycircle_name=(TextView)findViewById(R.id.mycircle_name);
		
		mActionBar = getActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setLogo(R.drawable.knowingcampus);
		mActionBar.setTitle("My Status");
		
		mQueue = Volley.newRequestQueue(this);
		mImageLoader = new ImageLoader(mQueue, new BitmapCache());
		
		String imageUrl=WebConfig.url+DataShare.currentUser.getProfile_image_url();  
		mycircle_image.setImageUrl(imageUrl,mImageLoader);
		mycircle_image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent();
				Bundle bundle=new Bundle();
				it.setClass(MyCircle.this,QueryGetUser.class);
				bundle.putString("queryUserId",DataShare.currentUser.getId());
				it.putExtras(bundle);
				startActivity(it);
			}
		});
		
		mycircle_name.setText(DataShare.currentUser.getName());
	
		statusList=theConnectWeb.getMyStatusList();
		mAdapter=new MyCircleLVAdapter(statusList,this); 
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent it=new Intent();
				Bundle bundle=new Bundle();
				it.setClass(MyCircle.this,Status_Comment.class);
				bundle.putSerializable("theStatus",statusList.get(arg2));
				it.putExtras(bundle);
				startActivity(it);
			}
		});
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

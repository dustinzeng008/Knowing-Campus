package com.knowingcampus.campusbbs;

import java.util.ArrayList;
import java.util.List;

import com.knowingcampus.R;
import com.knowingcampus.network.ConnectWeb;
import com.knowingcampus.view.PathView;
import com.knowingcampus.view.PathView.OnOneItemClickListener;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class CampusBBS extends Activity{
	
	private ActionBar mActionBar;
	private List<Status> statusList = new ArrayList<Status>();
	private ConnectWeb theConnectWeb = new ConnectWeb();
	private ListView mListView;
	private StatusAdapter mAdapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schoolcircle);
		
		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setLogo(R.drawable.knowingcampus);
		mActionBar.setTitle("CampusBBS");
		
		setupView();
		
		SpinnerAdapter mSprinnerAdapter=ArrayAdapter.createFromResource(this,
				R.array.array_status_category,android.R.layout.simple_spinner_dropdown_item);
		mActionBar.setListNavigationCallbacks(mSprinnerAdapter, new DropDownListenser());
		
		fillListviewStatus(0);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		fillListviewStatus(0);
		super.onResume();
	}
	
	class DropDownListenser implements OnNavigationListener
    {
        public boolean onNavigationItemSelected(int itemPosition, long itemId)
        {
        	fillListviewStatus(itemPosition);
        	return true;
        }
    }
	
	private void fillListviewStatus(int statuscategorynum)
	{
		List<Status> mStatusList = new ArrayList<Status>();
		//get all status
		statusList=theConnectWeb.getStatusList();	
		
		if(statuscategorynum==0)
		{
			mStatusList=statusList;
		}
		else
		{
			for(int i=0;i<statusList.size();i++)
			{
				Status theStatus=statusList.get(i);
				if(theStatus.getScid()==statuscategorynum)
				{
					mStatusList.add(theStatus);
				}
			}
		}
		
		mAdapter=new StatusAdapter(mStatusList,this); 
		mListView.setAdapter(mAdapter);
		
		final List<Status> mTempStatusList =mStatusList;
		
		mListView.setOnItemClickListener(new OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			System.out.println("to Status_comments");
			Intent it=new Intent();
			Bundle bundle=new Bundle();
			it.setClass(CampusBBS.this,Status_Comment.class);
			bundle.putSerializable("theStatus",mTempStatusList.get(arg2));
			it.putExtras(bundle);
			startActivity(it);
			}
		});
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mycircle, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
        	finish();
        	return true;
        case R.id.action_mycircle:
        	Intent it=new Intent();
			it.setClass(CampusBBS.this,MyCircle.class);	
			startActivity(it);
        	return true;
        } 
        return super.onOptionsItemSelected(item);
    }

	private void setupView() {
		PathView mPathView = (PathView)findViewById(R.id.mPathView_circle);
		
		mListView=new ListView(this);	
		mPathView.setListView(mListView);
		
		mListView.setDivider(null);
		mListView.setPadding(20, 20, 20, 20);
		mListView.setDividerHeight(10);
		
		ImageButton startMenu = new ImageButton(this);
		startMenu.setBackgroundResource(R.drawable.start_menu_btn);
		mPathView.setStartMenu(startMenu);
		int[] drawableIds = new int[] {R.drawable.pathmenu_text_xinqing, 
				R.drawable.pathmenu_text_xuexi,
				R.drawable.pathmenu_text_shenghuo,
				R.drawable.pathmenu_text_yule};
		View[] items = new View[drawableIds.length];
		for (int i = 0; i < drawableIds.length; i++) {
			ImageButton button = new ImageButton(this);
			button.setBackgroundResource(drawableIds[i]);
			items[i] = button;
		}
		mPathView.setItems(items);
		mPathView.setOnItemClickListener(new OnOneItemClickListener() {
			public void onItemClick(View view, int position) {
				// TODO Auto-generated method stub
				//System.out.println("position"+position);
				switch (position) {
				case 0:
					Intent intent=new Intent();
					intent.putExtra("statecategory",position);
					intent.setClass(CampusBBS.this,PublicStates.class);
					startActivity(intent);
					break;
				case 1:
					intent=new Intent();
					intent.putExtra("statecategory",position);
					intent.setClass(CampusBBS.this,PublicStates.class);
					startActivity(intent);
					break;
				case 2:
					intent=new Intent();
					intent.putExtra("statecategory",position);
					intent.setClass(CampusBBS.this,PublicStates.class);
					startActivity(intent);
					break;
				default:
					intent=new Intent();
					intent.putExtra("statecategory",position);
					intent.setClass(CampusBBS.this,PublicStates.class);
					startActivity(intent);
					break;
				}
			}
		});	
	}

}

package com.knowingcampus.news;
import com.knowingcampus.R;
import com.knowingcampus.campusbbs.CampusBBS;
import com.knowingcampus.campusbbs.MyCircle;

import android.os.Bundle;
import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

public class NewsActivity extends FragmentActivity {
	private ActionBar actionBar;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news);
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setLogo(R.drawable.knowingcampus);
		actionBar.setTitle("Campus News");
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

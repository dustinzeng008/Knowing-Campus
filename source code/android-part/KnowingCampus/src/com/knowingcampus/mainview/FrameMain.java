package com.knowingcampus.mainview;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.knowingcampus.R;
import com.knowingcampus.mainview.MenuFragment.SLMenuListOnItemClickListener;

public class FrameMain extends SlidingActivity implements SLMenuListOnItemClickListener{
	
	private SlidingMenu mSlidingMenu;
	private ActionBar mActionBar;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.DISPLAY_HOME_AS_UP);
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setLogo(R.drawable.knowingcampus);
		mActionBar.setTitle("KnowingCampus");
		
        setContentView(R.layout.frame_content); 
        setBehindContentView(R.layout.frame_menu);   //set the Behind View
        
        // customize the SlidingMenu
        mSlidingMenu = getSlidingMenu();
        mSlidingMenu.setMode(SlidingMenu.RIGHT);
        mSlidingMenu.setShadowDrawable(R.drawable.drawer_shadow); 
        mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);   
        mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset); 
        mSlidingMenu.setFadeDegree(0.35f);    
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);  
       
    
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.menu, new MenuFragment());
        fragmentTransaction.replace(R.id.content, new HomeFragment());
        fragmentTransaction.commit();
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
        	finish();
        	return true;
        case R.id.action_more:     
            toggle(); 
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	public void selectItem(int position, String title) {
		// update the main content by replacing fragments  
	    Fragment fragment = null;  
	    switch (position) {  
	    case 0:  
	        fragment = new HomeFragment();  
	        break;  
	    case 1:  
	       //fragment = new PersonDetailInfoFragment();  
	        break;  
	    case 2:   
	        break;  
	    case 3:  
	        break;  
	    case 4:    
	        break;  
	    case 5:  
	        break;  
	    default:  
	        break;  
	    }  
	  
	    if (fragment != null) {  
	        FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction()  
	                .replace(R.id.content, fragment).commit();  
	        // update selected item and title, then close the drawer  
	        setTitle(title);
	        mSlidingMenu.showContent();
	    } else {  
	        // error in creating fragment  
	        Log.e("MainActivity", "Error in creating fragment");  
	    }  
	}
}

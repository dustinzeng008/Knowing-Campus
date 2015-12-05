package com.knowingcampus.mainview;

import java.util.ArrayList;
import java.util.List;

import com.knowingcampus.R;
import com.knowingcampus.R.drawable;
import com.knowingcampus.R.id;
import com.knowingcampus.R.layout;
import com.knowingcampus.blackboard.Blackboard;
import com.knowingcampus.campusbbs.CampusBBS;
import com.knowingcampus.library.Library;
import com.knowingcampus.lostandfound.LostAndFound;
import com.knowingcampus.news.NewsActivity;
import com.knowingcampus.secondhandmarket.SecondHandMarket;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class HomeFragment extends Fragment {
	private GridView gv_Home_Menu;
	public HomeFragment(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().getActionBar().setTitle("Knowing Campus");
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        findView(rootView);
		init();
        return rootView;
    }
    
    private void findView(View v) {
    	//get GridView
    	gv_Home_Menu= (GridView) v.findViewById(R.id.gv_home_menu);
	}
    
    private void init() {
    	//set each item of GridView
		List<HomeMenuItem> menus = new ArrayList<HomeMenuItem>();
		menus.add(new HomeMenuItem(R.drawable.library, "Mobile Library"));
		menus.add(new HomeMenuItem(R.drawable.news, "Campus News"));
		menus.add(new HomeMenuItem(R.drawable.blackboard, "Blackboard"));
		menus.add(new HomeMenuItem(R.drawable.campusbbs, "Campus BBS"));
		menus.add(new HomeMenuItem(R.drawable.lostandfound, "Lost and Found"));
		menus.add(new HomeMenuItem(R.drawable.secondhandmarket, "Second-Hand market"));
		//set location and size of item
		int margin = (int) (getResources().getDisplayMetrics().density * 14 * 13 / 9);
		HomeMenuItemAdapter adapter = new HomeMenuItemAdapter(getActivity(), menus, margin);
		gv_Home_Menu.setAdapter(adapter);	  //set adapter for gridview
		//set listenning event for gridview
		gv_Home_Menu.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent;
				switch (position) {
				case 0:
					intent=new Intent();
					intent.setClass(getActivity(),Library.class);
					startActivity(intent);
					break;
				case 1:
					intent=new Intent();
					intent.setClass(getActivity(),NewsActivity.class);
					startActivity(intent);
					break;
				case 2:
					intent=new Intent();
					intent.setClass(getActivity(),Blackboard.class);
					startActivity(intent);
					break;
				case 3:
					intent=new Intent();
					intent.setClass(getActivity(),CampusBBS.class);
					startActivity(intent);
					break;
				case 4:
					intent=new Intent();
					intent.setClass(getActivity(),LostAndFound.class);
					startActivity(intent);
					break;
				default:
					intent=new Intent();
					intent.setClass(getActivity(),SecondHandMarket.class);
					startActivity(intent);
					break;
				}
			}
		});
	}
}

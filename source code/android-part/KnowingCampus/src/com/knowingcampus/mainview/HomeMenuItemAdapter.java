package com.knowingcampus.mainview;

import java.util.List;

import com.knowingcampus.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeMenuItemAdapter extends BaseAdapter{
	private List<HomeMenuItem> menus;
	private LayoutInflater inflater;
	private int margin;

	public HomeMenuItemAdapter(Context context, List<HomeMenuItem> menus, int margin) {
		inflater = LayoutInflater.from(context);
		this.menus = menus;
		this.margin = margin;
	}

	@Override
	public int getCount() {
		return menus.size();
	}

	@Override
	public Object getItem(int position) {
		return menus.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int height = parent.getHeight() / 3 - margin;
		AbsListView.LayoutParams param = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, height);
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.home_menu_item, parent, false);
			holder = new ViewHolder();
			holder.iv_menuIcon = (ImageView) convertView.findViewById(R.id.iv_menu_icon);
			holder.tv_menuTitle = (TextView) convertView.findViewById(R.id.tv_menu_title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		HomeMenuItem item = menus.get(position);
		holder.iv_menuIcon.setImageResource(item.menuIconRes);
		holder.tv_menuTitle.setText(item.menuTitle);
		convertView.setLayoutParams(param);
		return convertView;
	}

	private class ViewHolder {
		ImageView iv_menuIcon;
		TextView tv_menuTitle;
	}
}

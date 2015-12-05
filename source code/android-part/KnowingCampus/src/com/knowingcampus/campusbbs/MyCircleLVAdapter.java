package com.knowingcampus.campusbbs;

import java.util.List;

import com.knowingcampus.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyCircleLVAdapter extends BaseAdapter{

	private List<Status>data;
	private Context context;
	private LayoutInflater mInflater;
	
	public MyCircleLVAdapter(List<Status> data, Context context) {
		this.data = data;
		this.context = context;
		mInflater=LayoutInflater.from(context);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;  
        if(convertView==null){  
            convertView = mInflater.inflate(R.layout.mycircle_listitem, null);  
              
            holder = new ViewHolder();  
            holder.mycircle_listitem_time=(TextView)convertView.findViewById(R.id.mycircle_listitem_time);
            holder.mycircle_listitem_content=(TextView) convertView.findViewById(R.id.mycircle_listitem_content);
            
            convertView.setTag(holder);  
        }else{  
            holder = (ViewHolder) convertView.getTag();  
        }  
          
        Status theStatus=data.get(position);
        String[] timeArray=theStatus.getPublicTime().split(" ");
        String[] timeArray1=timeArray[0].split("-");
        holder.mycircle_listitem_time.setText(timeArray1[1]+"-"+timeArray1[2]);
        holder.mycircle_listitem_content.setText(theStatus.getContent());
        return convertView;
	}
	
	public static class ViewHolder{  
        public TextView mycircle_listitem_time;  
        public TextView mycircle_listitem_content;  
    }  

}

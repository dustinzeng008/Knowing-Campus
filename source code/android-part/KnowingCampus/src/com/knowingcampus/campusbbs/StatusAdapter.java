package com.knowingcampus.campusbbs;

import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.knowingcampus.R;
import com.knowingcampus.config.WebConfig;
import com.knowingcampus.datashare.BitmapCache;
import com.knowingcampus.datashare.QueryGetUser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class StatusAdapter extends BaseAdapter{

	private List<Status>data;
	private Context context;
	private LayoutInflater mInflater;
	
	private RequestQueue mQueue;
    private ImageLoader mImageLoader;
    
	public StatusAdapter(List<Status> data, Context context) {
		this.data = data;
		this.context = context;
		mInflater=LayoutInflater.from(context);
		mQueue = Volley.newRequestQueue(context);
		mImageLoader = new ImageLoader(mQueue, new BitmapCache());

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
            convertView = mInflater.inflate(R.layout.status_card_item, null);  
              
            holder = new ViewHolder();  
            holder.status_card_item_pic=(NetworkImageView)convertView.findViewById(R.id.status_card_item_pic);
            holder.status_card_item_name=(TextView) convertView.findViewById(R.id.status_card_item_name);
            holder.status_card_item_publictime=(TextView)convertView.findViewById(R.id.status_card_item_publictime);
            holder.status_card_item_content=(TextView)convertView.findViewById(R.id.status_card_item_content);
            holder.status_card_item_replynum=(TextView)convertView.findViewById(R.id.status_card_item_replynum);
           
            convertView.setTag(holder);  
        }else{  
            holder = (ViewHolder) convertView.getTag();  
        }  
          
        final Status theStatus = data.get(position);  
        
        String imageUrl=WebConfig.url+theStatus.getUserPicDir();
        System.out.println(imageUrl);
        
		holder.status_card_item_pic.setTag("url");  
		holder.status_card_item_pic.setImageUrl(imageUrl,mImageLoader);
		holder.status_card_item_pic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it=new Intent();
				Bundle bundle=new Bundle();
				it.setClass(context,QueryGetUser.class);
				bundle.putString("queryUserId",theStatus.getUid());
				it.putExtras(bundle);
				context.startActivity(it);
			}
		});
		
        holder.status_card_item_name.setText(theStatus.getName());
        holder.status_card_item_publictime.setText(theStatus.getPublicTime());
        holder.status_card_item_content.setText(theStatus.getContent());
        holder.status_card_item_replynum.setText("reply: "+theStatus.getCommentnum());
        return convertView;
	}
	
	public static class ViewHolder{  
		public NetworkImageView status_card_item_pic;
        public TextView status_card_item_name;  
        public TextView status_card_item_publictime;  
        public TextView status_card_item_content;
        public TextView status_card_item_replynum;
    }  

}

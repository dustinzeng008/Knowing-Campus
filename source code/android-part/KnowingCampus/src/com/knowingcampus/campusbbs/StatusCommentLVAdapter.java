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

public class StatusCommentLVAdapter extends BaseAdapter{

	private List<Comment>data;
	private Context context;
	private LayoutInflater mInflater;
	
	private RequestQueue mQueue;
    private ImageLoader mImageLoader;
    
	public StatusCommentLVAdapter(List<Comment> data, Context context) {
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
            convertView = mInflater.inflate(R.layout.status_comment_card_item, null);  
              
            holder = new ViewHolder();  
            holder.statuscommentlistitem_pic=(NetworkImageView)convertView.findViewById(R.id.statuscommentlistitem_pic);
            holder.statuscommentlistitem_name=(TextView) convertView.findViewById(R.id.statuscommentlistitem_name);
            holder.statuscommentlistitem_publictime=(TextView)convertView.findViewById(R.id.statuscommentlistitem_publictime);
            holder.statuscommentlistitem_content=(TextView)convertView.findViewById(R.id.statuscommentlistitem_content);
            holder.statuscommentlistitem_lounum=(TextView)convertView.findViewById(R.id.statuscommentlistitem_lounum);
            
           
            convertView.setTag(holder);  
        }else{  
            holder = (ViewHolder) convertView.getTag();  
        }  
          
        final Comment theComment = data.get(position);  
        
        String imageUrl=WebConfig.url+theComment.getUserPicDir();
		holder.statuscommentlistitem_pic.setTag("url");  
		holder.statuscommentlistitem_pic.setImageUrl(imageUrl,mImageLoader);
		holder.statuscommentlistitem_pic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent();
				Bundle bundle=new Bundle();
				it.setClass(context,QueryGetUser.class);
				bundle.putString("queryUserId",theComment.getUid());
				it.putExtras(bundle);
				context.startActivity(it);
			}
		});
		
        holder.statuscommentlistitem_name.setText(theComment.getName());
        holder.statuscommentlistitem_publictime.setText(theComment.getPublicTime());
        holder.statuscommentlistitem_content.setText(theComment.getContent());
        holder.statuscommentlistitem_lounum.setText(theComment.getLounum()+"th floor");
       
        return convertView;
	}
	
	public static class ViewHolder{  
		public NetworkImageView statuscommentlistitem_pic;
        public TextView statuscommentlistitem_name;  
        public TextView statuscommentlistitem_publictime;  
        public TextView statuscommentlistitem_content;
        public TextView statuscommentlistitem_lounum;
    }  

}

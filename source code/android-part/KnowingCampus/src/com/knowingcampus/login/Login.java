package com.knowingcampus.login;

import com.knowingcampus.R;
import com.knowingcampus.R.drawable;
import com.knowingcampus.R.id;
import com.knowingcampus.R.layout;
import com.knowingcampus.config.WebConfig;
import com.knowingcampus.datashare.DataShare;
import com.knowingcampus.mainview.FrameMain;
import com.knowingcampus.network.ConnectWeb;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

	private ActionBar mActionBar;
	private ConnectWeb theConnectWeb = new ConnectWeb();
	
	private EditText mETUser;
	private EditText mETPasswd;
	private Button mBtnLogin;
	private CheckBox cb_login_RememberInfo;
	
	private String login_config_info="login_config_info";
	private String strUserID="";
	private String strPassword="";
	private int isRemember=0;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.DISPLAY_HOME_AS_UP);
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setLogo(R.drawable.knowingcampus);
		mActionBar.setTitle("Knowing Campus");
		
		mETUser=(EditText)findViewById(R.id.login_user_edit);
		mETPasswd=(EditText)findViewById(R.id.login_passwd_edit);
		mBtnLogin=(Button)findViewById(R.id.login_btn);
		cb_login_RememberInfo=(CheckBox)findViewById(R.id.cb_login_RememberInfo);
		
		get_login_config_info();
		mETUser.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(mETUser.getText().toString().trim().equals(strUserID))
				{
					mETPasswd.setText(strPassword);
					cb_login_RememberInfo.setChecked(true);
				}
				else
				{
					mETPasswd.setText("");
					cb_login_RememberInfo.setChecked(false);
				}
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		
		mBtnLogin.setOnClickListener(new CBtnLogin());
		cb_login_RememberInfo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					isRemember=1;
				}
				else
				{
					isRemember=0;
				}
			}
		});
	}
	
	
	class CBtnLogin implements OnClickListener
	{
		public void onClick(View v) {
			strUserID=mETUser.getText().toString().trim();
			strPassword=mETPasswd.getText().toString().trim();
			if(strUserID.equals("")||strPassword.equals("")){
				Toast.makeText(Login.this,"Please Input all the Informations!",Toast.LENGTH_SHORT).show();
			}
			else{
				theConnectWeb.userLogin(strUserID,strPassword);
				if(DataShare.currentUser!=null){
					save_login_config_info();
					Toast.makeText(Login.this,"Login success!",Toast.LENGTH_SHORT).show();
					Intent intent=new Intent();
					intent.setClass(Login.this,FrameMain.class);
					startActivity(intent);
					finish();
				}
				else{
					Toast.makeText(Login.this,"ID or Password error!",Toast.LENGTH_SHORT).show();
				}
			}		
		}
	}
	
	public void save_login_config_info()
	{
		//Get SharedPreferences
		SharedPreferences mSharedPreferences=getSharedPreferences(login_config_info, 0);
		//open the edit status of SharedPreferences
		Editor editor=mSharedPreferences.edit();
		editor.putString("userID", strUserID);
		if(isRemember==0)
		{
			editor.putString("password","");	
		}
		else
		{
			editor.putString("password", strPassword);
		}
		editor.putInt("isRemember",isRemember);
		editor.commit();	//save data
	
	}
	
	public void get_login_config_info()
	{
		//get SharedPreferences
		SharedPreferences mSharedPreferences=getSharedPreferences(login_config_info, 0);
		//get configue information from login_config_info
		strUserID=mSharedPreferences.getString("userID","");
		strPassword=mSharedPreferences.getString("password","");
		isRemember=mSharedPreferences.getInt("isRemember",0);
		mETUser.setText(strUserID);
		mETPasswd.setText(strPassword);
		if(isRemember==1)
			cb_login_RememberInfo.setChecked(true);
		
	}
	
}

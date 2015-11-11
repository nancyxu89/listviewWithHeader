package com.cr.test;

import java.util.Timer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class TestActivityOther extends Activity implements OnClickListener
{
	ClipDrawable drawable;
	Handler handler = new Handler()  
	{  
		public void handleMessage(Message msg)  
		{  
			if(msg.what == 0x1233)  
			{  
				if(drawable.getLevel() >= 10000){
					drawable.setLevel(1000);  
				}
				//修改ClipDrawable的level值  
				drawable.setLevel(drawable.getLevel() +1000);  
			}  
		}  
	};  
	//时间类
	final Timer timer = new Timer();  
	
	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_main);
		initView();
	}

	private void initView()
	{
		button=(Button)findViewById(R.id.click);
//		ImageView image2=(ImageView)findViewById(R.id.image2);
//		//获取图片所显示的ClipDrawble对象  
//       drawable = (ClipDrawable)image2.getDrawable();  
       
       button.setOnClickListener(this);
       
//       timer.schedule(new TimerTask()  
//   	{  
//   		public void run()  
//   		{  
//   			Message msg = new Message();  
//   			msg.what = 0x1233;  
//   			//发送消息,通知应用修改ClipDrawable对象的level值  
//   			handler.sendMessage(msg);  
//   			//取消定时器  
////                   if(drawable.getLevel() >= 10000)  
////                   {  
////                       timer.cancel();  
////                   }  
//   		}  
//   	},0,300); 
       
       
       PackageInfo packageInfo = getPackageManager().getPackageArchiveInfo(
    		   Environment.getExternalStorageDirectory()
				.getPath()+"/CR_ILS_Pad1.1.0.apk", 1);
       if ((packageInfo != null)
               &&(packageInfo.activities != null)
               && (packageInfo.activities.length > 0)) {
       }
    }

	@Override
	public void onClick(View v)
	{
		if(v.getId()==R.id.click)
		{
			Intent intent = new Intent(this, ProxyActivity.class);
	        intent.putExtra(ProxyActivity.EXTRA_DEX_PATH, Environment.getExternalStorageDirectory()
					.getAbsolutePath()+"/CR_ILS_Pad1.1.0.apk");
//	        intent.putExtra(ProxyActivity.EXTRA_CLASS, "com.cr.schoolInfo.ui.LoginActivity");
	        startActivity(intent);
		}
		
	}  
}

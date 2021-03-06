package com.cr.test;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class TestActivity extends Activity
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
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_main);
		initView();
	}

	private void initView()
	{
		ImageView image2=(ImageView)findViewById(R.id.image2);
		//获取图片所显示的ClipDrawble对象  
       drawable = (ClipDrawable)image2.getDrawable();  
       
       timer.schedule(new TimerTask()  
   	{  
   		public void run()  
   		{  
   			Message msg = new Message();  
   			msg.what = 0x1233;  
   			//发送消息,通知应用修改ClipDrawable对象的level值  
   			handler.sendMessage(msg);  
   			//取消定时器  
//                   if(drawable.getLevel() >= 10000)  
//                   {  
//                       timer.cancel();  
//                   }  
   		}  
   	},0,300); 
    }  
}

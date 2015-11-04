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
					drawable.setLevel(0);  
				}
				//�޸�ClipDrawable��levelֵ  
				drawable.setLevel(drawable.getLevel() +1000);  
			}  
		}  
	};  
	//ʱ����
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
		//��ȡͼƬ����ʾ��ClipDrawble����  
       drawable = (ClipDrawable)image2.getDrawable();  
       
       timer.schedule(new TimerTask()  
   	{  
   		public void run()  
   		{  
   			Message msg = new Message();  
   			msg.what = 0x1233;  
   			//������Ϣ,֪ͨӦ���޸�ClipDrawable�����levelֵ  
   			handler.sendMessage(msg);  
   			//ȡ����ʱ��  
//                   if(drawable.getLevel() >= 10000)  
//                   {  
//                       timer.cancel();  
//                   }  
   		}  
   	},0,300); 
    }  
}
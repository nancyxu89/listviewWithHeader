package com.cr.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.cr.test.view.HeaderViewListView;

public class MainActivity extends Activity
{
	WindowManager.LayoutParams windowAttributes;
	Activity activity;
	LayoutInflater mInflater;

	HeaderViewListView listView;
	ImageView headerIV;
	
	ImageView image;

	public enum Items
	{
		item1(R.drawable.icon_subject_art), item2(
				R.drawable.icon_subject_chinese), item3(
				R.drawable.icon_subject_cteam), item4(
				R.drawable.icon_subject_english), item5(
				R.drawable.icon_subject_math), item6(
						R.drawable.icon_subject_chinese), item7(
								R.drawable.icon_subject_cteam), item8(
								R.drawable.icon_subject_english), item9(
								R.drawable.icon_subject_math), item10(
										R.drawable.icon_subject_chinese), item11(
												R.drawable.icon_subject_cteam), item12(
												R.drawable.icon_subject_english), item13(
												R.drawable.icon_subject_math);

		private int id;

		private Items(int id)
		{

		}

		public int getId()
		{
			return id;
		}

		public void setId(int id)
		{
			this.id = id;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initHeaderView();
		initData();
	}

	private void initData()
	{
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < Items.values().length; i++)
		{
			map.put("imageUrl", Items.values()[i].getId());
			map.put("name", "科目" + i);
			map.put("intro", "认真阅读" + i);
			data.add(map);
		}

		SimpleItemAdapter adapter = new SimpleItemAdapter(this, data,
				R.layout.test_item,
				new String[] { "imageUrl", "name", "intro" },
				new int[] { R.id.target_icon ,R.id.target_name
				,R.id.target_intro });

		listView.setAdapter(adapter);
	}

	private void initHeaderView()
	{
		View view = LayoutInflater.from(this).inflate(R.layout.header_item,
				null);
		headerIV = (ImageView) view.findViewById(R.id.header_iv);
		listView.addHeaderView(view);
		listView.setHeaderImageView(headerIV);
		
		initClip();
	}

	private void initClip()
	{
		image=(ImageView)findViewById(R.id.image);
		//获取图片所显示的ClipDrawble对象  
        final ClipDrawable drawable = (ClipDrawable)image.getDrawable();  
        final Handler handler = new Handler()  
        {  
            public void handleMessage(Message msg)  
            {  
                if(msg.what == 0x1233)  
                {  
                    if(drawable.getLevel() >= 10000){
                        drawable.setLevel(0);  
                    }
                    //修改ClipDrawable的level值  
                    drawable.setLevel(drawable.getLevel() +500);  
                }  
            }  
        };  
                       
        //时间类
        final Timer timer = new Timer();  
        timer.schedule(new TimerTask()  
        {  
            public void run()  
            {  
                Message msg = new Message();  
                msg.what = 0x1233;  
                //发送消息,通知应用修改ClipDrawable对象的level值  
                handler.sendMessage(msg);  
                //取消定时器  
//                if(drawable.getLevel() >= 10000)  
//                {  
//                    timer.cancel();  
//                }  
            }  
        },0,300);  
    }  

	private void initView()
	{
		listView = (HeaderViewListView) findViewById(R.id.header_view_list_view);
	}

	private class SimpleItemAdapter extends SimpleAdapter
	{

		public SimpleItemAdapter(Context context,
				List<? extends Map<String, ?>> data, int resource,
				String[] from, int[] to)
		{
			super(context, data, resource, from, to);
		}

	}
}

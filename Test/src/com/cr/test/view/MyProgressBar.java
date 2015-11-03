package com.cr.test.view;

import com.cr.test.R;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MyProgressBar extends FrameLayout
{
	private boolean running;
	private int progress = 0;
	private static final int MAX_PROGRESS = 10000;

	private ClipDrawable clip;

	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(android.os.Message msg)
		{
			if (msg.what == 0x123)
				clip.setLevel(progress);
		}
	};

	public MyProgressBar(Context context)
	{
		this(context, null, 0);
	}

	public MyProgressBar(Context context, AttributeSet attrs)
	{
		this(context, null, 0);
	}

	public MyProgressBar(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		Init(context);
	}

	public void Init(Context context)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.a_test_page,
				null);

		ImageView iv = (ImageView) view.findViewById(R.id.progress_img);

		addView(view);
		clip = (ClipDrawable) iv.getDrawable();

		Thread thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				running = true;
				while (running)
				{
					handler.sendEmptyMessage(0x123);
					if (progress == MAX_PROGRESS)
						progress = 0;
					progress += 100;
					try
					{
						Thread.sleep(18);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}

	public void stop()
	{
		progress = 0;
		running = false;
	}
}

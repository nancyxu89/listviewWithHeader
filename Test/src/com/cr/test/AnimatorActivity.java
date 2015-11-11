package com.cr.test;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class AnimatorActivity extends Activity implements OnClickListener
{
	private ImageView imageView1;
	private ImageView imageView;
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
		imageView1 = (ImageView) findViewById(R.id.test_image1);
		imageView = (ImageView) findViewById(R.id.test_image2);
		button = (Button) findViewById(R.id.click);
		button.setOnClickListener(this);

		// PackageInfo packageInfo = getPackageManager().getPackageArchiveInfo(
		// Environment.getExternalStorageDirectory().getPath()
		// + "/CR_ILS_Pad1.1.0.apk", 1);
		// if ((packageInfo != null) && (packageInfo.activities != null)
		// && (packageInfo.activities.length > 0))
		// {
		// }

		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		int mScreenHeight = metric.heightPixels;

		animator = ValueAnimator.ofFloat(0,
				mScreenHeight / 2 - imageView.getHeight());
		animator.setTarget(imageView);
		animator.setDuration(5000).start();
		animator.addUpdateListener(new AnimatorUpdateListener()
		{

			@Override
			public void onAnimationUpdate(ValueAnimator animation)
			{
				imageView.setTranslationY((Float) animation.getAnimatedValue());
			}
		});

		ValueAnimator valueAnimator = new ValueAnimator();
		valueAnimator.setDuration(3000);
		valueAnimator.setObjectValues(new PointF(0, 0));
		valueAnimator.setInterpolator(new LinearInterpolator());
		valueAnimator.setEvaluator(new TypeEvaluator<PointF>()
		{
			// fraction = t / duration
			@Override
			public PointF evaluate(float fraction, PointF startValue,
					PointF endValue)
			{
				// x方向200px/s ，则y方向0.5 * 10 * t
				PointF point = new PointF();
				point.x = 200 * fraction * 3;
				point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
				return point;
			}
		});

		valueAnimator.start();
		valueAnimator.addUpdateListener(new AnimatorUpdateListener()
		{
			@Override
			public void onAnimationUpdate(ValueAnimator animation)
			{
				PointF point = (PointF) animation.getAnimatedValue();
				imageView1.setX(point.x);
				imageView1.setY(point.y);

			}
		});
	}

	private ValueAnimator animator;

	@Override
	public void onClick(View v)
	{
		if (v.getId() == R.id.click)
		{
			// Intent intent = new Intent(this, ProxyActivity.class);
			// intent.putExtra(ProxyActivity.EXTRA_DEX_PATH, Environment
			// .getExternalStorageDirectory().getPath()
			// + "/CR_ILS_Pad1.1.0.apk");
			// // intent.putExtra(ProxyActivity.EXTRA_CLASS,
			// // "com.cr.schoolInfo.ui.LoginActivity");
			// startActivity(intent);
			animator.addListener(new AnimatorListenerAdapter()
			{

				@Override
				public void onAnimationEnd(Animator animation)
				{
					ViewGroup group = (ViewGroup) imageView.getParent();
					if (group != null)
					{
						group.removeView(imageView);
					}
					super.onAnimationEnd(animation);
				}

			});
		}

	}
}

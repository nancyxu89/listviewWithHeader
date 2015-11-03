package com.cr.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ListView;

public class HeaderViewListView extends ListView
{
	private ImageView headerImageView;
	private int headerHeight = -1;

	public HeaderViewListView(Context context)
	{
		super(context);
	}

	public HeaderViewListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public HeaderViewListView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public void setHeaderImageView(ImageView view)
	{
		headerImageView = view;
	}

	@Override
	protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
			int scrollY, int scrollRangeX, int scrollRangeY,
			int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent)
	{
		boolean isResize = resizeHeaderImageView(deltaY);

		return isResize ? true : super.overScrollBy(deltaX, deltaY, scrollX,
				scrollY, scrollRangeX, scrollRangeY, maxOverScrollX,
				maxOverScrollY, isTouchEvent);
	}

	private boolean resizeHeaderImageView(int deltaY)
	{
		if (deltaY < 0)
		{
			if (headerImageView != null)
			{
				headerImageView.getLayoutParams().height = headerImageView
						.getHeight() - deltaY;
				headerImageView.requestLayout();
			}
		}
		else 
		{
			if (headerImageView != null
					&& headerImageView.getHeight() > headerHeight)
			{
				headerImageView.getLayoutParams().height = headerImageView
						.getHeight() - deltaY;
				headerImageView.requestLayout();
			}
		}
		return false;
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus)
	{
		super.onWindowFocusChanged(hasWindowFocus);
		if (hasWindowFocus)
		{
			headerHeight = this.headerImageView.getHeight();
		}
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt)
	{
		super.onScrollChanged(l, t, oldl, oldt);

		View parent = (View) this.headerImageView.getParent();
		//缩小ImageView父容器的高度
		if(parent.getTop()<0&&headerImageView.getHeight()>headerHeight)
		{
			headerImageView.getLayoutParams().height=headerImageView.getHeight()+parent.getTop();
			parent.layout(parent.getLeft(), 0, parent.getRight(), parent.getHeight());
			headerImageView.requestLayout();
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		if(ev.getAction()==MotionEvent.ACTION_UP||ev.getAction()==MotionEvent.ACTION_CANCEL)
		{
			ResizeAnimation animation=new ResizeAnimation(headerImageView, headerHeight);
			animation.setDuration(200);
			this.headerImageView.startAnimation(animation);
		}
		return super.onTouchEvent(ev);
	}
	
	public static class ResizeAnimation extends Animation
	{
		private ImageView imageView;
		private int targetHeight;
		private int curHeight;
		private int extraHeight;
		
		public ResizeAnimation(ImageView imageView,int targetHeight)
		{
			this.imageView=imageView;
			this.targetHeight=targetHeight;
			this.curHeight=imageView.getHeight();
			this.extraHeight=this.curHeight-this.targetHeight;
		}
		
		@Override
		protected void applyTransformation(float interpolatedTime,
				Transformation t)
		{
			super.applyTransformation(interpolatedTime, t);
			this.imageView.getLayoutParams().height=(int) (this.curHeight-interpolatedTime*this.extraHeight);
			this.imageView.requestLayout();
		}
	}
}

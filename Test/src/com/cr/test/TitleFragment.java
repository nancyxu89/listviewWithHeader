package com.cr.test;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TitleFragment extends Fragment implements OnClickListener
{
	private TextView title;

	public interface titleFgmListerner
	{
		void onTitleListener();
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.title_fragment_layout, container,
				false);
		title = (TextView) view.findViewById(R.id.title);
		title.setOnClickListener(this);
		return view;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
//		setHasOptionsMenu(true);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		menu.clear();
		inflater.inflate(R.menu.fragment_menu, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Toast.makeText(getActivity(), item.getTitle(), 0).show();

		return true;
	}

	@Override
	public void onClick(View v)
	{
		if (getActivity() instanceof titleFgmListerner)
		{
			((titleFgmListerner) getActivity()).onTitleListener();
		}
	}

}

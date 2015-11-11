package com.cr.test.view;

import com.cr.test.R;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class DefinedDialogFragment extends DialogFragment
{
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState)
{
	getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
	return inflater.inflate(R.layout.dialog_test, container, false);
}
}

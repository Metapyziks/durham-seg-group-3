package com.example.fortitude;

import json.JSONObject;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.SeekBar;
import android.widget.Space;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ImageView;
import android.graphics.Color;

public class AttackCacheScreen extends Window
{
	private static AttackCacheScreen me;
	private static Cache staticTheCache;
	
	public AttackCacheScreen(Cache theCache)
	{
		super(R.drawable.scout_report_success_bg);
		me = this;
		staticTheCache = theCache;
		addContentToContentPane(createWindowPane());
	}
	
	private GridLayout createWindowPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(1);
		mainArea.setRowCount(5);
		
		return mainArea;
	}
	
	////////
	//
	//topBarClicked
	//
	//called when the top bar is clicked
	//
	////////
	public void topBarClicked()
	{
		killMe();
		new AccountScreen() {
			public void showNextScreen()
			{
				new AttackCacheScreen(AttackCacheScreen.getStaticTheCache());
			}
		};
	}

	////////
	//
	//getMe
	//
	//returns the last created instance of this class, could be null
	//
	////////
	public static AttackCacheScreen getMe()
	{
		return me;
	}

	////////
	//
	//killMe
	//
	//removes this window from view
	//
	////////
	public void killMe()
	{
		me = null;
		this.removeAllViews();
	}
	
	public static Cache getStaticTheCache()
	{
		return staticTheCache;
	}
}

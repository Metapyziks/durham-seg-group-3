package com.example.fortitude;

import android.widget.ImageView.ScaleType;
import android.widget.ImageView;import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;
import android.widget.TextView;
import android.view.Gravity;
import android.content.Context;
import android.graphics.Color;

public class NotificationPanel extends GridLayout 
{
	private Spec row1 = GridLayout.spec(0);
	
	private Spec col1 = GridLayout.spec(0);
	
	public NotificationPanel(Context c, NotificationStub stub, int windowWidth, int windowHeight)
	{
		super(c);
		super.setColumnCount(5);
		super.setRowCount(1);
		addContentToMe(stub, windowWidth, windowHeight);
	}
	
	private void addContentToMe(NotificationStub stub, int windowWidth, int windowHeight)
	{
		if(stub == null)
		{
			LayoutParams spaceLayout = new LayoutParams(row1, col1);
			spaceLayout.width = windowWidth;
			spaceLayout.height = windowHeight / 12;
			Space space = new Space(super.getContext());
			space.setLayoutParams(spaceLayout);
			super.addView(space, spaceLayout);
			return;
		}
	}
}

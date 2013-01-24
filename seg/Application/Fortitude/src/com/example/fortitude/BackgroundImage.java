package com.example.fortitude;

import android.widget.ImageView.ScaleType;
import android.widget.ImageView;

public class BackgroundImage extends ImageView
{
	public BackgroundImage(int imageID)
	{	
		super(Fortitude.getFortitude());
		setScaleType(ScaleType.FIT_XY);
		this.setImageResource(imageID);
	}
}

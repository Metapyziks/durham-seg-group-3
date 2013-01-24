package com.example.fortitude;

import android.widget.ImageView.ScaleType;
import android.widget.ImageView;

public class TextFieldImage extends ImageView
{
	public TextFieldImage()
	{	
		super(Fortitude.getFortitude());
		setScaleType(ScaleType.FIT_XY);
		setImageResource(R.drawable.textfield);
	}
}

package com.example.fortitude;

import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.GridLayout.LayoutParams;

public abstract class FortitudeButton extends ImageView
{
	int originalImage;
	int imageToChangeTo;
	int timeBeforeAction;
	
	public FortitudeButton(int originalImage, int imageToChangeTo)
	{
		super(Fortitude.getFortitude());
		this.originalImage = originalImage;
		this.imageToChangeTo = imageToChangeTo;  
		timeBeforeAction = 40;
		setScaleType(ScaleType.FIT_XY);
		changeImage(originalImage);
		addOnTheOnClickListener();
	}
	
	public FortitudeButton(int originalImage, int imageToChangeTo, int timeBeforeAction)
	{
		super(Fortitude.getFortitude());
		this.imageToChangeTo = imageToChangeTo;
		this.timeBeforeAction = timeBeforeAction;
		setScaleType(ScaleType.FIT_XY);
		setImageResource(originalImage);
		addOnTheOnClickListener();
	}
	
	public void addOnTheOnClickListener()
	{
		this.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				preClickActions();
				beenClicked();
			}
		});
	}
	
	public abstract void preClickActions();
	
	private void changeImage(int newImage)
	{
		setImageResource(newImage);
	}
	
	public void beenClicked()
	{
		setImageResource(imageToChangeTo);
		Thread thread = new Thread(new Runnable() {
			public void run()
			{
				try
				{
					Thread.sleep(timeBeforeAction);
				}
				catch(Exception e)
				{
					//Decorative, do nothing
				}
				Fortitude.getFortitude().runOnUiThread(new Runnable() {
					public void run()
					{
						changeImage(originalImage);
						whenClicked();	
					}
				});
			}
		});
		thread.start();
	}

	public abstract void whenClicked();
}

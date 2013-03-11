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
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);
	
	private NotificationStub stub;

	public NotificationPanel(Context c, NotificationStub stub, int windowWidth, int windowHeight)
	{
		super(c);
		super.setColumnCount(5);
		super.setRowCount(1);
		this.stub = stub;
		addContentToMe(stub, windowWidth, windowHeight);
	}
	
	public NotificationStub getStub()
	{
		return stub;
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
		LayoutParams leftSpaceLayout = new LayoutParams(row1,col1);
		leftSpaceLayout.width = windowWidth / 10;
		Space leftSpace = new Space(super.getContext());
		leftSpace.setLayoutParams(leftSpaceLayout);
		super.addView(leftSpace, leftSpaceLayout);

		LayoutParams iconBoxLayout = new LayoutParams(row1, col2);
		iconBoxLayout.width = windowHeight / 15;
		iconBoxLayout.height = windowHeight / 15;
		ImageView icon = new ImageView(super.getContext());
		if(stub.getRead())
		{
			if(stub.getType().equals("Message"))
			{
				icon.setImageResource(R.drawable.news_envelope_read);
			}
			else if(stub.getType().equals("BattleReport"))
			{
				icon.setImageResource(R.drawable.swords_read);
			}
		}
		else
		{
			if(stub.getType().equals("Message"))
			{
				icon.setImageResource(R.drawable.news_envelope);
			}
			else if(stub.getType().equals("BattleReport"))
			{
                icon.setImageResource(R.drawable.swords);
			}
		}
		icon.setScaleType(ScaleType.FIT_XY);
		icon.setLayoutParams(iconBoxLayout);
		super.addView(icon, iconBoxLayout);
		
		LayoutParams middleSpaceLayout = new LayoutParams(row1, col3);
		middleSpaceLayout.width = windowWidth / 30;
		Space middleSpace = new Space(super.getContext());
		middleSpace.setLayoutParams(middleSpaceLayout);
		super.addView(middleSpace, middleSpaceLayout);
		
		LayoutParams textViewLayout = new LayoutParams(row1, col4);
		textViewLayout.height = windowHeight / 15;
		textViewLayout.width = windowWidth - (leftSpaceLayout.width + iconBoxLayout.width + middleSpaceLayout.width);
		TextView textView = new TextView(super.getContext());
		textView.setTextSize(14);
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		textView.setTextColor(Color.WHITE);
		if(stub.getType().equals("Message"))
		{
			String text = (stub.getPerson() + " : " + stub.getContext());
			textView.setText(text.substring(0, Math.min(20, text.length())));
		}
		else if(stub.getType().equals("BattleReport"))
		{
			String text = stub.getContext() + " has been attacked!";
			textView.setText(text.substring(0, Math.min(20, text.length())));
		}
		textView.setLayoutParams(textViewLayout);
		super.addView(textView, textViewLayout);
	}
}

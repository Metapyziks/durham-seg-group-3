package com.example.fortitude;

import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.GridLayout.Spec;
import android.widget.Space;
import android.widget.TextView;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import android.graphics.Color;
import android.graphics.Typeface;

public abstract class ViewMessageScreen extends Window 
{
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);
	private Spec row5 = GridLayout.spec(4);
	private Spec row6 = GridLayout.spec(5);
	private Spec row7 = GridLayout.spec(6);
	private Spec row8 = GridLayout.spec(7);
	
	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);
	
	private static ViewMessageScreen me;
	private static String sender;
	private static String subject;
	public static int goToPage;
	private static String content;
	
	public ViewMessageScreen(String senderx, String subjectx, String contentx,int goToPagex)
	{
		super(R.drawable.read_message_bg);
		me = this;
		sender = senderx;
		subject = subjectx;
		goToPage = goToPagex;
		content = contentx;
		super.addContentToContentPane(createContentPane());
		
		GridLayout reportGrid = new GridLayout(super.getContext());
		reportGrid.setColumnCount(1);
		reportGrid.setRowCount(3);
		
		LayoutParams topRowSpaceLayout = new LayoutParams(row1, col1);
		topRowSpaceLayout.height = (int) (super.getWindowHeight() * 0.42);
		Space topRowSpace = new Space(reportGrid.getContext());
		topRowSpace.setLayoutParams(topRowSpaceLayout);
		reportGrid.addView(topRowSpace, topRowSpaceLayout);
		
		GridLayout reportIconGrid = new GridLayout(reportGrid.getContext());
		reportIconGrid.setRowCount(1);
		reportIconGrid.setColumnCount(3);
		
		LayoutParams iconLeftSpaceLayout = new LayoutParams(row1, col1);
		iconLeftSpaceLayout.width = (int) (super.getWindowWidth() * 0.87);
		Space iconLeftSpace = new Space(reportIconGrid.getContext());
		iconLeftSpace.setLayoutParams(iconLeftSpaceLayout);
		reportIconGrid.addView(iconLeftSpace, iconLeftSpaceLayout);
		
		LayoutParams reportIconLayout = new LayoutParams(row1, col2);
		reportIconLayout.width = super.getWindowWidth() / 10;
		reportIconLayout.height = super.getWindowWidth() / 10;
		ImageView reportIcon = new ImageView(reportIconGrid.getContext());
		reportIcon.setScaleType(ScaleType.FIT_XY);
		reportIcon.setImageResource(R.drawable.report_icon);
		reportIcon.setLayoutParams(reportIconLayout);
		reportIcon.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0)
			{
				ViewMessageScreen.getMe().killMe();
				new ReportScreens(3) {
					public void whenCancelled()
					{
						new ViewMessageScreen(ViewMessageScreen.sender, ViewMessageScreen.subject, ViewMessageScreen.content, ViewMessageScreen.goToPage) {
							public void whenCancelled()
							{
								if(ViewMessageScreen.goToPage == 0)
								{
								   new NotificationScreen(NotificationScreen.pageId);
								}
								else if(ViewMessageScreen.goToPage == 1)
								{
									new InboxScreen(InboxScreen.pageId);
								}
								else
								{
									new MainScreen();
								}
							}
						};
					}
				};
			}
		});
		reportIconGrid.addView(reportIcon, reportIconLayout);
		
		LayoutParams reportIconGridLayout = new LayoutParams(row2, col1);
		reportGrid.addView(reportIconGrid, reportIconGridLayout);
		
		LayoutParams reportGridLayout = new LayoutParams(row2, col2);
		super.addView(reportGrid, reportGridLayout);
	}
	
	private GridLayout createContentPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setRowCount(9);
		mainArea.setColumnCount(1);
		
		LayoutParams topRowSpaceLayout = new LayoutParams(row1, col1);
		topRowSpaceLayout.height = (int) (super.getWindowHeight() * 0.25);
		Space topRowSpace = new Space(mainArea.getContext());
		topRowSpace.setLayoutParams(topRowSpaceLayout);
		mainArea.addView(topRowSpace, topRowSpaceLayout);
		
		GridLayout senderGrid = new GridLayout(mainArea.getContext());
		senderGrid.setRowCount(1);
		senderGrid.setColumnCount(3);
		
		LayoutParams senderGridLeftSpaceLayout = new LayoutParams(row1, col1);
		senderGridLeftSpaceLayout.width = (super.getWindowWidth() * 2) / 5;
		Space senderGridLeftSpace = new Space(senderGrid.getContext());
		senderGridLeftSpace.setLayoutParams(senderGridLeftSpaceLayout);
		senderGrid.addView(senderGridLeftSpace);
		
		LayoutParams senderTextViewLayout = new LayoutParams(row1, col2);
		senderTextViewLayout.width = super.getWindowWidth() / 2;
		senderTextViewLayout.height = super.getWindowHeight() / 10;
		TextView senderTextView = new TextView(senderGrid.getContext());
		senderTextView.setTextSize(14);
		senderTextView.setTextColor(Color.WHITE);
		senderTextView.setGravity(Gravity.LEFT);
		senderTextView.setText(sender);
		senderTextView.setLayoutParams(senderTextViewLayout);
		senderGrid.addView(senderTextView, senderTextViewLayout);
		
		LayoutParams senderGridLayout = new LayoutParams(row2, col1);
		mainArea.addView(senderGrid, senderGridLayout);
		
		LayoutParams thirdRowSpaceLayout = new LayoutParams(row3, col1);
		thirdRowSpaceLayout.height = super.getWindowHeight() / 60;
		Space thirdRowSpace = new Space(mainArea.getContext());
		thirdRowSpace.setLayoutParams(thirdRowSpaceLayout);
		mainArea.addView(thirdRowSpace, thirdRowSpaceLayout);
		
		GridLayout subjectGrid = new GridLayout(mainArea.getContext());
		subjectGrid.setRowCount(1);
		subjectGrid.setColumnCount(3);
		
		LayoutParams subjectGridLeftSpaceLayout = new LayoutParams(row1, col1);
		subjectGridLeftSpaceLayout.width = (super.getWindowWidth() * 2) / 5;
		Space subjectGridLeftSpace = new Space(subjectGrid.getContext());
		subjectGridLeftSpace.setLayoutParams(subjectGridLeftSpaceLayout);
		subjectGrid.addView(subjectGridLeftSpace);
		
		LayoutParams subjectTextViewLayout = new LayoutParams(row1, col2);
		subjectTextViewLayout.width = super.getWindowWidth() / 2;
		subjectTextViewLayout.height = super.getWindowHeight() / 10;
		TextView subjectTextView = new TextView(subjectGrid.getContext());
		subjectTextView.setTextSize(14);
		subjectTextView.setTextColor(Color.WHITE);
		subjectTextView.setGravity(Gravity.LEFT);
		subjectTextView.setText(subject);
		subjectTextView.setLayoutParams(subjectTextViewLayout);
		subjectGrid.addView(subjectTextView, subjectTextViewLayout);
		
		LayoutParams subjectGridLayout = new LayoutParams(row4, col1);
		mainArea.addView(subjectGrid, subjectGridLayout);
	
		LayoutParams fithRowSpaceLayout = new LayoutParams(row5, col1);
		fithRowSpaceLayout.height = super.getWindowHeight() / 30;
		Space fithRowSpace = new Space(mainArea.getContext());
		fithRowSpace.setLayoutParams(fithRowSpaceLayout);
		mainArea.addView(fithRowSpace, fithRowSpaceLayout);
		
		GridLayout messageContentGrid = new GridLayout(mainArea.getContext());
		messageContentGrid.setRowCount(1);
		messageContentGrid.setColumnCount(3);
		
		LayoutParams messageContentGridLeftSpaceLayout = new LayoutParams(row1, col1);
		messageContentGridLeftSpaceLayout.width = (super.getWindowWidth() / 10) + (super.getWindowWidth() / 100);
		Space messageContentGridLeftSpace = new Space(messageContentGrid.getContext());
		messageContentGridLeftSpace.setLayoutParams(messageContentGridLeftSpaceLayout);
		messageContentGrid.addView(messageContentGridLeftSpace, messageContentGridLeftSpaceLayout);
		
		LayoutParams messageContentLayout = new LayoutParams(row1, col2);
		messageContentLayout.width = (int) (super.getWindowWidth() * 0.78);
		messageContentLayout.height = (super.getWindowHeight() / 3) - (super.getWindowHeight() / 18);
		TextView messageContent = new TextView(messageContentGrid.getContext());
		messageContent.setTextColor(Color.WHITE);
		messageContent.setTextSize(14);
		messageContent.setGravity(Gravity.LEFT);
		messageContent.setText(content);
		
		messageContent.setLayoutParams(messageContentLayout);
		messageContentGrid.addView(messageContent, messageContentLayout);
		
		LayoutParams messageContentGridLayout = new LayoutParams(row6, col1);
		mainArea.addView(messageContentGrid, messageContentGridLayout);
		
		LayoutParams seventhRowSpaceLayout = new LayoutParams(row7, col1);
		seventhRowSpaceLayout.height = super.getWindowHeight() / 20;
		Space seventhRowSpace = new Space(mainArea.getContext());
		seventhRowSpace.setLayoutParams(seventhRowSpaceLayout);
		mainArea.addView(seventhRowSpace, seventhRowSpaceLayout);
		
		GridLayout buttonRowGrid = new GridLayout (mainArea.getContext()); // Button Grid
		buttonRowGrid.setColumnCount(5);
		buttonRowGrid.setRowCount(1);
		
		LayoutParams firstButtonSpaceLayout = new LayoutParams(row1, col1); //first button spacer
		firstButtonSpaceLayout.width = super.getWindowWidth() / 12;
		Space firstButtonSpace = new Space(buttonRowGrid.getContext());
		firstButtonSpace.setLayoutParams(firstButtonSpaceLayout);
		buttonRowGrid.addView(firstButtonSpace, firstButtonSpaceLayout);
		
		LayoutParams replyButtonLayout = new LayoutParams(row1, col2); // reply Button
		replyButtonLayout.height = super.getWindowHeight() /10;
		replyButtonLayout.width = (int) (super.getWindowWidth() *0.4);
		FortitudeButton replyButton = (new FortitudeButton(R.drawable.reply, R.drawable.reply_pressed) {
			public void preClickActions() 
			{
				
			}
			public void whenClicked() 
			{
				ViewMessageScreen.getMe().killMe();
				new SendMessageScreen(sender) {
					public void whenCancelled() 
					{
						new NotificationScreen(NotificationScreen.pageId);
					}
				};
			}			
		});
		replyButton.setLayoutParams(replyButtonLayout);
		buttonRowGrid.addView(replyButton, replyButtonLayout);
		
		LayoutParams secondButtonSpaceLayout = new LayoutParams(row1, col3); //second button spacer
		secondButtonSpaceLayout.width = super.getWindowWidth() / 12;
		Space secondButtonSpace = new Space(buttonRowGrid.getContext());
		secondButtonSpace.setLayoutParams(secondButtonSpaceLayout);
		buttonRowGrid.addView(secondButtonSpace, secondButtonSpaceLayout);
		
		LayoutParams cancelButtonLayout = new LayoutParams(row1, col4); //cancel button
		cancelButtonLayout.height = super.getWindowHeight() /10;
		cancelButtonLayout.width = (int) (super.getWindowWidth() *0.4);
		FortitudeButton cancelButton = (new FortitudeButton(R.drawable.cancel, R.drawable.cancel_pressed) {

			public void preClickActions() 
			{
				
			}

			public void whenClicked() 
			{
				ViewMessageScreen.getMe().killMe();
				whenCancelled();
			}
			
		});
		cancelButton.setLayoutParams(cancelButtonLayout);
		buttonRowGrid.addView(cancelButton, cancelButtonLayout);
		
		LayoutParams buttonRowGridLayout = new LayoutParams(row8, col1);
		mainArea.addView(buttonRowGrid, buttonRowGridLayout);
		
		return mainArea;
	}
	
	public void killMe()
	{
		me = null;
		this.removeAllViews();
	}
	
	public static ViewMessageScreen getMe()
	{
		return me;
	}
	
	public abstract void whenCancelled();
}

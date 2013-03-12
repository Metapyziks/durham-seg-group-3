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

public class ActivateUserScreen extends Window
{
	private Spec row1 = GridLayout.spec(0);
	private Spec row2 = GridLayout.spec(1);
	private Spec row3 = GridLayout.spec(2);
	private Spec row4 = GridLayout.spec(3);

	private Spec col1 = GridLayout.spec(0);
	private Spec col2 = GridLayout.spec(1);
	private Spec col3 = GridLayout.spec(2);
	private Spec col4 = GridLayout.spec(3);

	private static ActivateUserScreen me;
	private static TextField emailField;

	public ActivateUserScreen()
	{
		super(R.drawable.activate_bg);
		me = this;
		super.addContentToContentPane(createWindowPane());
	}

	private GridLayout createWindowPane()
	{
		GridLayout mainArea = new GridLayout(Fortitude.getFortitude());
		mainArea.setColumnCount(1);
		mainArea.setRowCount(5);

		LayoutParams firstRowSpaceLayout = new LayoutParams(row1, col1);
		firstRowSpaceLayout.height = ((super.getWindowHeight() / 3) * 2) - (super.getWindowHeight() / 28);
		Space firstRowSpace = new Space(mainArea.getContext());
		firstRowSpace.setLayoutParams(firstRowSpaceLayout);
		mainArea.addView(firstRowSpace, firstRowSpaceLayout);

		GridLayout secondRowGrid = new GridLayout(mainArea.getContext());
		secondRowGrid.setColumnCount(3);
		secondRowGrid.setRowCount(1);

		LayoutParams secondRowLeftSpaceLayout = new LayoutParams(row1, col1);
		secondRowLeftSpaceLayout.width = super.getWindowWidth() / 3;
		Space secondRowLeftSpace = new Space(secondRowGrid.getContext());
		secondRowLeftSpace.setLayoutParams(secondRowLeftSpaceLayout);
		secondRowGrid.addView(secondRowLeftSpace, secondRowLeftSpaceLayout);

		LayoutParams emailFieldImageLayout = new LayoutParams(row1, col2);
		emailFieldImageLayout.width = super.getWindowWidth() / 2;
		emailFieldImageLayout.height = super.getWindowHeight() / 12;
		TextFieldImage emailFieldImage = new TextFieldImage();
		emailFieldImage.setLayoutParams(emailFieldImageLayout);
		secondRowGrid.addView(emailFieldImage, emailFieldImageLayout);

		LayoutParams emailFieldLayout = new LayoutParams(row1, col2);
		emailFieldLayout.width = super.getWindowWidth() / 2;
		emailFieldLayout.height = super.getWindowHeight() / 12;
		emailField = new TextField(50);
		emailField.setLayoutParams(emailFieldLayout);
		secondRowGrid.addView(emailField, emailFieldLayout);

		LayoutParams secondRowGridLayout = new LayoutParams(row2, col1);
		mainArea.addView(secondRowGrid, secondRowGridLayout);

		LayoutParams thirdRowSpaceLayout = new LayoutParams(row3, col1);
		thirdRowSpaceLayout.height = super.getWindowHeight() / 6;
		Space thirdRowSpace = new Space(mainArea.getContext());
		thirdRowSpace.setLayoutParams(thirdRowSpaceLayout);
		mainArea.addView(thirdRowSpace, thirdRowSpaceLayout);

		GridLayout buttonGrid = new GridLayout(mainArea.getContext());
		buttonGrid.setColumnCount(5);
		buttonGrid.setRowCount(1);

		LayoutParams buttonLeftSpaceLayout = new LayoutParams(row1, col1);
		buttonLeftSpaceLayout.width = super.getWindowWidth() / 12;
		Space buttonLeftSpace = new Space(buttonGrid.getContext());
		buttonLeftSpace.setLayoutParams(buttonLeftSpaceLayout);
		buttonGrid.addView(buttonLeftSpace, buttonLeftSpaceLayout);

		LayoutParams sendEmailButtonLayout = new LayoutParams(row1, col2);
		sendEmailButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		sendEmailButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton sendEmailButton = (new FortitudeButton(R.drawable.send_email, R.drawable.send_email_pressed) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{
				ServerRequests.setTheMessageBox(MessageBox.newMsgBox("Connecting To Server", false));
				ServerRequests.resendActivationEmail(emailField.getText().toString().toLowerCase());
			}
		});
		sendEmailButton.setLayoutParams(sendEmailButtonLayout);
		buttonGrid.addView(sendEmailButton, sendEmailButtonLayout);

		LayoutParams middleButtonSpaceLayout = new LayoutParams(row1, col3);
		middleButtonSpaceLayout.width = super.getWindowWidth() / 15;
		Space middleButtonSpace = new Space(buttonGrid.getContext());
		middleButtonSpace.setLayoutParams(middleButtonSpaceLayout);
		buttonGrid.addView(middleButtonSpace, middleButtonSpaceLayout);

		LayoutParams cancelButtonLayout = new LayoutParams(row1, col4);
		cancelButtonLayout.width = (super.getWindowWidth() / 2) - (super.getWindowWidth() / 10);
		cancelButtonLayout.height = super.getWindowHeight() / 10;
		FortitudeButton cancelButton = (new FortitudeButton(R.drawable.cancel, R.drawable.cancel_pressed) {
			public void preClickActions()
			{

			}
			public void whenClicked()
			{
				ActivateUserScreen.getMe().killMe();
				new MainScreen();
			}
		});
		cancelButton.setLayoutParams(cancelButtonLayout);
		buttonGrid.addView(cancelButton, cancelButtonLayout);

		LayoutParams buttonGridLayout = new LayoutParams(row4, col1);
		mainArea.addView(buttonGrid, buttonGridLayout);

		return mainArea;
	}

	public static ActivateUserScreen getMe()
	{
		return me;
	}

	public void killMe()
	{
		me = this;
		this.removeAllViews();
	}
}

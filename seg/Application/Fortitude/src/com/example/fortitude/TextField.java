package com.example.fortitude;

import android.graphics.Color;
import android.text.InputFilter;
import android.widget.EditText;

public class TextField extends EditText
{	
	public TextField()
	{	
		super(Fortitude.getFortitude());
	    setTextSize(12);
	    setBackgroundColor(Color.TRANSPARENT);
	    setSingleLine(true);
	}

	public TextField(int maxInputLength)
	{
		super(Fortitude.getFortitude());
		setTextSize(12);
		setBackgroundColor(Color.TRANSPARENT);
	    InputFilter[] textFieldLengthFilter = new InputFilter[1];
	    textFieldLengthFilter[0] = new InputFilter.LengthFilter(maxInputLength);
	    setFilters(textFieldLengthFilter);
	}
}
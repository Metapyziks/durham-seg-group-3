package com.example.fortitude;

import android.text.method.PasswordTransformationMethod;

public class PasswordTextField extends TextField
{
	public PasswordTextField()
	{
		super();
		super.setTransformationMethod(PasswordTransformationMethod.getInstance());
	}
	
	public PasswordTextField(int maxInputLength)
	{
		super(maxInputLength);
		super.setTransformationMethod(PasswordTransformationMethod.getInstance());
		
	}
}

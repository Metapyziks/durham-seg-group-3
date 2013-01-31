package com.example.fortitude;

import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

public class FileSave {

	public void test() throws IOException{

	    String FILENAME = "hello_file";
	    String string = "hello world!";

	    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
	    fos.write(string.getBytes());
	    fos.close();

	}

	private FileOutputStream openFileOutput(String fILENAME, int modePrivate) {
		// TODO Auto-generated method stub
		return null;
	}
}

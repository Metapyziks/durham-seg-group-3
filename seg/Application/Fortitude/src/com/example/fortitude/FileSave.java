package com.example.fortitude;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;

public class FileSave {

	public void CreateFileDialog(String filename, String file) {

		String FILENAME = filename;
		String string = file;

		try{
		FileOutputStream fos = Fortitude.getFortitude().openFileOutput(FILENAME, Context.MODE_PRIVATE);
		fos.write(string.getBytes());
		fos.close();}
		catch (Exception e) {
			return;
		}
	}


	public String OpenFileDialog(String file) {

		// Read file in Internal Storage
		FileInputStream fis;
		String content = "";
		try {
			fis = Fortitude.getFortitude().openFileInput(file);
			byte[] input = new byte[fis.available()];
			if (input.length == 0 ) {
			return null;
			}
			while (fis.read(input) != -1) {
			}
			content += new String(input);
			if (content == "") {
			return null; }
		} catch (Exception e) {
			return null;
		}
		return content;
	}
}

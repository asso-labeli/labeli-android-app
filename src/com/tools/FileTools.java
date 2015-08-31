package com.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Locale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * > @FileTools
 *
 * Tools for writing and reading files
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public abstract class FileTools {

	public static void writeToFile(Context context, String filename, String data)
	{
		try
		{
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
			outputStreamWriter.write(data);
			outputStreamWriter.close();
		}
		catch (IOException e)
		{
			Log.e("Exception", "File write failed: " + e.toString());
		}
	}
	
	public static void writeBitmapToFile(Context context, String filename, Bitmap data)
	{
			FileOutputStream out = null;
			try {
				out = context.openFileOutput(filename, Context.MODE_PRIVATE);
				data.compress(Bitmap.CompressFormat.PNG, 90, out);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	public static String readFromFile(Context context, String filename)
	{
		String data = new String("");
		try
		{
			InputStream inputStream = context.openFileInput(filename);
			if (inputStream != null)
			{
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String receiveString = new String("");
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null)
					stringBuilder.append(receiveString);

				inputStream.close();
				data = stringBuilder.toString();
				
			}
		}
		catch (FileNotFoundException e)
		{
			Log.e("File", "File not found: " + e.toString());
		}
		catch (IOException e)
		{
			Log.e("File", "Can not read file: " + e.toString());
		}
		return data;
	}
	
	public static Bitmap readBitmapFromFile(String filename)
	{
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		return BitmapFactory.decodeFile(filename, options);	
		
	}
	
	public static String getAbsolutePathLocalFileFromURL(Context context, String url){
		return context.getFilesDir() + "/" + url.replace("/", "_").replace(" ", "%20");
	}
	
	public static String getLocalFileFromURL(String url){
		return url.replace("/", "_").replace(" ", "%20");
	}
	
	public static String createFilenameForProject(String projectName, String extension){
		return projectName.replace(' ', '_').toLowerCase(Locale.FRANCE) + extension;
	}

}

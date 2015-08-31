package com.tools;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DeviceTools {
	
	public static int getHeightScreen(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		
		return metrics.heightPixels;
	}
	
	public static int getWidthScreen(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		
		return metrics.widthPixels;
	}

}

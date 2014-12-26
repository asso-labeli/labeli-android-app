package com.tools;

import org.jsoup.Jsoup;

import android.util.Log;

public abstract class HTMLTools {

	public static String HTMLToText(String html){
		Log.i("Test", html);
		return html.replaceAll("\n", "")
				.replaceAll("<div class=\\\"survey\\\".*?</div>","\nVote en cours\n")
				.replaceAll("<style>.*?</style>", "")
				.replaceAll("<br>", "\n")
				.replaceAll("</li>", "\n")
				.replaceAll("<.*?>", "");
	}

}

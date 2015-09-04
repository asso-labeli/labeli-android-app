package com.tools;

/**
 * > @HTMLTools
 *
 * Tools for HTML encoding translation
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public abstract class HTMLTools {

	public static String HTMLToText(String html){
		return html.replaceAll("<div class=\\\"survey\\\".*?</div>","\nVote en cours\n")
				.replaceAll("<style>.*?</style>", "")
				.replaceAll("<br>", "\n")
				.replaceAll("</li>", "\n")
				.replaceAll("<.*?>", "");
	}

}

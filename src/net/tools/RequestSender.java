package net.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.labeli.interfaces.DataWithPicture;
import com.tools.FileTools;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

/**
 * > @JSONParser
 *
 * Parser for JSON datas
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class RequestSender {

	public static String GET = "GET";
	public static String POST = "POST";
	public static String PUT = "PUT";
	public static String DELETE = "DELETE";

	private static final String COOKIES_HEADER = "Set-Cookie";
	private CookieManager mCookieManager;

	// constructor
	public RequestSender() {
		mCookieManager = new CookieManager();
	}

	public JSONObject makeHttpRequest(String requestURL, String method, 
			HashMap<String, String> urlParameters, HashMap<String, String> bodyParameters){
		HttpURLConnection connection = null;
		URL url = null;
		JSONObject jObj = null;

		try {
			if (urlParameters != null){
				String stringUrlParams = getFormattedParameters(urlParameters);
				url = new URL(requestURL+"?"+stringUrlParams);
			}
			else
				url = new URL(requestURL);

			connection = (HttpURLConnection) url.openConnection();

			if (mCookieManager.getCookieStore().getCookies().size() > 0)
				connection.setRequestProperty("Cookie", 
						TextUtils.join(";", mCookieManager.getCookieStore().getCookies()));

			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			connection.setRequestMethod(method);
			connection.setDoInput(true);
			if (bodyParameters != null){
				connection.setDoOutput(true);

				String stringBodyParameters = getFormattedParameters(bodyParameters);
				connection.setRequestProperty("Content-Type", 
						"application/x-www-form-urlencoded");
				connection.setRequestProperty("Content-Length", "" + 
						Integer.toString(stringBodyParameters.getBytes().length));
				connection.setRequestProperty("Content-Language", "fr-FR"); 

				OutputStream os = connection.getOutputStream();
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(os, "UTF-8"));
				writer.write(stringBodyParameters);

				writer.flush();
				writer.close();
				os.close();
			}

			int responseCode = connection.getResponseCode();

			if (responseCode == HttpsURLConnection.HTTP_OK) {
				List<String> cookiesHeader = connection.getHeaderFields().get(COOKIES_HEADER);
				if (cookiesHeader != null){
					for (String cookie : cookiesHeader)
						mCookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
				}

				String line;
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder sb = new StringBuilder();

				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}

				try {
					jObj = new JSONObject(sb.toString());
				} catch (JSONException e) {
					Log.d("JSON Parser", "Error parsing data " + e.toString());
					Log.d("JSON Parser", "Setting value of jObj to null");
					jObj = null;
				}
			}
			else {
				Log.w("HttpUrlConnection", "Error : server sent code : " + responseCode);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}

		return jObj;
	}

	private String getFormattedParameters(HashMap<String, String> params) throws UnsupportedEncodingException{
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for(Map.Entry<String, String> entry : params.entrySet()){
			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
		}

		return result.toString();
	}

	public boolean postPicture(File f, String pictureName){ 
		try {
			MultipartUtility multipart = null;
			if (mCookieManager.getCookieStore().getCookies().size() > 0)
				multipart = new MultipartUtility(APIConnection.apiUrl + "upload", "UTF-8", mCookieManager.getCookieStore().getCookies());
			else
				multipart = new MultipartUtility(APIConnection.apiUrl + "upload", "UTF-8", null);

			multipart.addHeaderField("User-Agent", "Label[i] Android App");
			multipart.addFormField("name", pictureName);
			multipart.addFilePart("image", f);

			return multipart.finish();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean loadImage(Activity a, DataWithPicture d, int width){
		if (d.getPictureURL() == null) return true;

		final File dataFile = new File(FileTools.getAbsolutePathLocalFileFromURL(a, d.getPictureURL()));
		String wallpaperURLStr = APIConnection.apiUrl + "images/" + d.getPictureURL() +"?dim=" + width;
		final String localFile = FileTools.getLocalFileFromURL(d.getPictureURL());

		Log.i("Co", dataFile.getAbsolutePath());
		if (!dataFile.exists() && !d.getPictureURL().equals("null")){
			Log.i("Net", "Chargement de " + wallpaperURLStr);
			Bitmap bm = null;
			try {
				InputStream in = new java.net.URL(wallpaperURLStr).openStream();
				bm = BitmapFactory.decodeStream(in);

				FileTools.writeBitmapToFile(a, localFile, bm);
				return true;
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
		}

		return false;
	}
}
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
 * > @RequestSender
 *
 * Object to send/get HTTP datas to/from a server.
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

	/**
	 * Constructor - Init the cookie manager
	 */
	public RequestSender() {
		mCookieManager = new CookieManager();
	}

	/**
	 * Send HTTP Request with params (x-url-encoded)
	 * @param requestURL
	 * @param method - HTTP method (GET, POST, PUT, DELETE, ...)
	 * @param urlParameters - parameters send in URL
	 * @param bodyParameters - parameters send in body (encoded)
	 * @return JSONObject returned by the server
	 */
	public JSONObject makeHttpRequest(String requestURL, String method, 
			HashMap<String, String> urlParameters, HashMap<String, String> bodyParameters){
		HttpURLConnection connection = null;
		URL url = null;
		JSONObject jObj = null;

		try {
			// Check if we must add parameters in URL
			if (urlParameters != null){
				String stringUrlParams = getFormattedParameters(urlParameters);
				url = new URL(requestURL+"?"+stringUrlParams);
			}
			else
				url = new URL(requestURL);

			// Create connection
			connection = (HttpURLConnection) url.openConnection();

			// Add cookies to request
			if (mCookieManager.getCookieStore().getCookies().size() > 0)
				connection.setRequestProperty("Cookie", 
						TextUtils.join(";", mCookieManager.getCookieStore().getCookies()));

			// Set request parameters
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			connection.setRequestMethod(method);
			connection.setDoInput(true);
			
			// Check if we must add parameters in body
			if (bodyParameters != null){
				// Create a string with parameters
				String stringBodyParameters = getFormattedParameters(bodyParameters);
				
				// Set output request parameters
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", 
						"application/x-www-form-urlencoded");
				connection.setRequestProperty("Content-Length", "" + 
						Integer.toString(stringBodyParameters.getBytes().length));
				connection.setRequestProperty("Content-Language", "fr-FR"); 

				// Send body's request
				OutputStream os = connection.getOutputStream();
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(os, "UTF-8"));
				writer.write(stringBodyParameters);

				writer.flush();
				writer.close();
				os.close();
			}

			// Get response code
			int responseCode = connection.getResponseCode();

			// If response is 200 (OK)
			if (responseCode == HttpsURLConnection.HTTP_OK) {
				// Keep new cookies in the manager
				List<String> cookiesHeader = connection.getHeaderFields().get(COOKIES_HEADER);
				if (cookiesHeader != null){
					for (String cookie : cookiesHeader)
						mCookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
				}

				// Read the response
				String line;
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder sb = new StringBuilder();

				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}

				// Parse the response to a JSON Object
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
			// Close connection
			connection.disconnect();
		}

		return jObj;
	}

	/**
	 * Create a string formatted with parameters
	 * @param params - HashMap containing all parameters
	 * @return formatted string
	 * @throws UnsupportedEncodingException
	 */
	private String getFormattedParameters(HashMap<String, String> params) throws UnsupportedEncodingException{
		StringBuilder result = new StringBuilder();
		boolean first = true;
		
		// Add each entry to String
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

	/**
	 * Send a picture to a server
	 * @param f - the file with the picture
	 * @param pictureName - new name of picture on the server
	 * @return
	 */
	public boolean postPicture(File f, String pictureName){ 
		try {
			// Crée une requête Multipart
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

	/**
	 * Load an image from a URL
	 * @param a - activity where the image must be load
	 * @param d - a data with a picture URL
	 * @param width - width of the screen
	 * @return true if loading is successfull
	 */
	public boolean loadImage(Activity a, DataWithPicture d, int width){
		if (d.getPictureURL() == null) return true;

		// Open the data file
		final File dataFile = new File(FileTools.getAbsolutePathLocalFileFromURL(a, d.getPictureURL()));
		
		// Create URL
		String wallpaperURLStr = APIConnection.apiUrl + "images/" + d.getPictureURL() +"?dim=" + width;
		final String localFile = FileTools.getLocalFileFromURL(d.getPictureURL());

		// If there's a image to load
		if (!d.getPictureURL().equals("null") && (dataFile.lastModified() < d.getLastEdited().getTime())){
			// Remove the file to reload it
			if (dataFile.exists()) dataFile.delete();

			Bitmap bm = null;
			try {
				InputStream in = new java.net.URL(wallpaperURLStr).openStream();
				bm = BitmapFactory.decodeStream(in);

				FileTools.writeBitmapToFile(a, localFile, bm);
				
				in.close();
				return true;
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
		}

		return false;
	}
}
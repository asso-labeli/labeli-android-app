package net.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static JSONArray jArray = null;
	static String json = "";
	DefaultHttpClient httpClient;

	// constructor
	public JSONParser() {
		httpClient = new DefaultHttpClient();
	}

	// function get json from url
	// by making HTTP POST or GET mehtod
	public JSONObject makeHttpRequest(String url, String method,
			List<NameValuePair> params) {

		// Making HTTP request
		try {

			// check for request method
			if (method == "POST") {
				// request method is POST
				// defaultHttpClient
				HttpParams httpParameters = new BasicHttpParams();
				// Set the timeout in milliseconds until a connection is established.
				// The default value is zero, that means the timeout is not used. 
				int timeoutConnection = 3000;
				HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
				// Set the default socket timeout (SO_TIMEOUT) 
				// in milliseconds which is the timeout for waiting for data.
				int timeoutSocket = 5000;
				HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

				httpClient.setParams(httpParameters);
				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();

			} else if (method == "GET") {
				// request method is GET
				HttpParams httpParameters = new BasicHttpParams();
				// Set the timeout in milliseconds until a connection is established.
				// The default value is zero, that means the timeout is not used. 
				int timeoutConnection = 3000;
				HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
				// Set the default socket timeout (SO_TIMEOUT) 
				// in milliseconds which is the timeout for waiting for data.
				int timeoutSocket = 5000;
				HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

				httpClient.setParams(httpParameters);
				String paramString = URLEncodedUtils.format(params, "utf-8");
				url += "?" + paramString;
				HttpGet httpGet = new HttpGet(url);

				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				
				is = httpEntity.getContent();
			} else if (method == "PUT") {
				HttpParams httpParameters = new BasicHttpParams();

				int timeoutConnection = 3000;
				HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);

				int timeoutSocket = 5000;
				HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

				httpClient.setParams(httpParameters);
				HttpPut httpPut = new HttpPut(url);
				httpPut.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

				HttpResponse httpResponse = httpClient.execute(httpPut);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
			} else if (method == "DELETE") {
				HttpParams httpParameters = new BasicHttpParams();

				int timeoutConnection = 3000;
				HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);

				int timeoutSocket = 5000;
				HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

				httpClient.setParams(httpParameters);
				String paramString = URLEncodedUtils.format(params, "utf-8");
				url += "?" + paramString;
				HttpDelete httpDelete = new HttpDelete(url);

				HttpResponse httpResponse = httpClient.execute(httpDelete);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
			}
		} catch (HttpHostConnectException e){
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();

			json = sb.toString();

		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.d("JSON Parser", "Error parsing data " + e.toString());
			Log.d("JSON Parser", "Setting value of jObj to null");
			jObj = null;
		}

		// return JSON String
		return jObj;

	}
}
package net.tools;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.app.labeli.DataWithPicture;
import com.tools.FileTools;

public class MySingleton {
	private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;

    private MySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap>
                    cache = new LruCache<String, Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
    
    public static void loadImage(final Activity a, DataWithPicture d){
		if (d.getPictureURL() == null) return;

		final File dataFile = new File(FileTools.getAbsolutePathLocalFileFromURL(a, d.getPictureURL()));
		String wallpaperURLStr = APIConnection.apiUrl + d.getPictureURL();
		final String localFile = FileTools.getLocalFileFromURL(d.getPictureURL());

		if (!dataFile.exists() && !d.getPictureURL().equals("null")){
			Log.i("Net", "Chargement de " + wallpaperURLStr);

			RequestFuture<Bitmap> f = RequestFuture.newFuture();
			ImageRequest ir = new ImageRequest(wallpaperURLStr.replace(" ", "%20"), 
					new Response.Listener<Bitmap>(){
				@Override
				public void onResponse(Bitmap b){
					FileTools.writeBitmapToFile(a, localFile, b);
				}
			}, 0, 0, null, 
					new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					Log.w(a.getPackageName(), "Cannot load picture");
				}
					});
			
			MySingleton.getInstance(a).addToRequestQueue(ir);
		}
	}
}

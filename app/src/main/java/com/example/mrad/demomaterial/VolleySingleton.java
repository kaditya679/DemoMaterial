package com.example.mrad.demomaterial;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by MR.AD on 029-Mar-3-29-2017.
 */

public class VolleySingleton {

    private static VolleySingleton vInstance=null;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private VolleySingleton()
    {
        requestQueue= Volley.newRequestQueue(MyApplication.getAppContext());
        imageLoader=new ImageLoader(requestQueue, new ImageLoader.ImageCache() {

            private LruCache<String,Bitmap> cache=new LruCache<>((int)((Runtime.getRuntime().maxMemory()/1024)/8));
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

                cache.put(url,bitmap);
            }
        });

    }

    public static VolleySingleton getvInstance()
    {
        if(vInstance==null)
        {
            vInstance=new VolleySingleton();
        }
        return vInstance;
    }

    public RequestQueue getRequestQueue()
    {
        return requestQueue;
    }

    public ImageLoader getImageLoader()
    {
        return imageLoader;
    }
}

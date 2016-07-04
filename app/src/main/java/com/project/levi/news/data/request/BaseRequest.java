package com.project.levi.news.data.request;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Levi on 7/4/2016.
 */

public class BaseRequest<T> extends Request<T> {
    private static final String TAG = "BaseRequest";

    private Map<String, String> mHeaders;
    private Map<String, String> mParams;
    private Response.Listener<T> mListener;
    private Type mType;

    public BaseRequest(int method, String url, Response.ErrorListener listener, Type type) {
        super(method, url, listener);
        mType = type;
        if (method == Method.POST) {
            mParams = new HashMap<>();
        }
        mHeaders = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return mParams;
    }

    public void setParams(String key, String value) {
        this.mParams.put(key, value);
    }

    @Override
    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    public void setHeaders(String key, String value) {
        this.mHeaders.put(key, value);
    }

    public void setListener(Response.Listener<T> listener) {
        mListener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            Gson gson = new Gson();
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Log.d(TAG, "Json : " + json);
            Type type = new TypeToken<T>() {
            }.getType();
            return Response.success((T) gson.fromJson(json, type), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}

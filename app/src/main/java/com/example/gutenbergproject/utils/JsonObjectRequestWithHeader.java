package com.example.gutenbergproject.utils;
/*
 * Created by SudApps on 23/02/16.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by SudApps on 10/2/16.
 *
 * This class is created for adding Headers like Api-Key
 * Use this class for api call in whole project.
 *
 * In getBodyContentType() in this we pass Content-Type
 *
 * In getHeaders() in this we pass header like Api-Key
 *
 */
public class JsonObjectRequestWithHeader extends JsonObjectRequest {

    public JsonObjectRequestWithHeader(int method, String url, JSONObject requestBody, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
    }

    public String getBodyContentType()
    {
        return "application/json";
    }


}


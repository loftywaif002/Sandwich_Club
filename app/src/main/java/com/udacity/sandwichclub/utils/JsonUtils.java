package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getName();

    /*
    * Initialize JSONObject from JSON String
    *
    * */
    public static Sandwich parseSandwichJson(String json){

        try{
            JSONObject sandwich_details = new JSONObject(json);
            Log.i(TAG,sandwich_details.toString());
        }catch (JSONException e){
            Log.e(TAG, e.toString());
        }


        return null;
    }
}

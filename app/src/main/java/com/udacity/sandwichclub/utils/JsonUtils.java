package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getName();

    /*
    * Initialize JSONObject from JSON String
    * Loop through the JSON object and get the
    *
    * */
    public static Sandwich parseSandwichJson(String json){

        try{
            JSONObject sandwich_details = new JSONObject(json);
            JSONObject name = sandwich_details.getJSONObject("name");
            /*
            * To get the strings, optString method is used since we are not sure if the data will be there or not.
            * If we use getString, it will throw an exception if the value is empty
            * */
            String mainName = name.optString("mainName");
            String alsoKnownAs = name.optString("alsoKnownAs"); //This is an Array

            String placeOfOrigin = sandwich_details.optString("placeOfOrigin");

            String description = sandwich_details.optString("description");
            String image_url = sandwich_details.optString("image");
            String ingredients = sandwich_details.optString("ingredients"); //This is an array

            Log.i(TAG,mainName);
            Log.i(TAG,alsoKnownAs);
            Log.i(TAG,placeOfOrigin);
            Log.i(TAG,description);
            Log.i(TAG,image_url);
            Log.i(TAG,ingredients);
        }catch (JSONException e){
            Log.e(TAG, e.toString());
        }


        return null;
    }
}

package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
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
        
        List<String> ingredients = new ArrayList<>();
        List<String> alsoKnownAs = new ArrayList<>();
        try{
            JSONObject sandwich_details = new JSONObject(json);
            JSONObject name = sandwich_details.getJSONObject("name");
            /*
            * To get the strings, optString method is used since we are not sure if the data will be there or not.
            * If we use getString, it will throw an exception if the value is empty
            * */
            String mainName = name.optString("mainName");

            JSONArray also_known_as_items = name.getJSONArray("alsoKnownAs");
            if(also_known_as_items != null){

                for (int i=0; i<also_known_as_items.length();i++){
                    try {
                        alsoKnownAs.add(also_known_as_items.getString(i)); //pushing each elements in the array
                    } catch (JSONException e) {
                        Log.e(TAG, e.toString());
                    }
                }
            }

            JSONArray ingredient_items = sandwich_details.getJSONArray("ingredients");
            if(ingredient_items != null){

                for (int i=0; i<ingredient_items.length();i++){
                    try {
                        ingredients.add(ingredient_items.getString(i)); //pushing each elements in the array
                    } catch (JSONException e) {
                        Log.e(TAG, e.toString());
                    }
                }
            }

            String placeOfOrigin = sandwich_details.optString("placeOfOrigin");

            String description = sandwich_details.optString("description");
            String image_url = sandwich_details.optString("image");


            Log.i(TAG,mainName);
            Log.i(TAG,alsoKnownAs.toString());
            Log.i(TAG,placeOfOrigin);
            Log.i(TAG,description);
            Log.i(TAG,image_url);
            Log.i(TAG,ingredients.toString());

        }catch (JSONException e){
            Log.e(TAG, e.toString());
        }


        return null;
    }
}

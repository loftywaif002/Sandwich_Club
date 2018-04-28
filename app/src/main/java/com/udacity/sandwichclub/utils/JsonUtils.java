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
        Sandwich sandwich;
        String mainName="";
        JSONArray also_known_as_array;
        List<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin="";
        String description="";
        JSONArray ingredient_array;
        String image_url="";
        List<String> ingredients = new ArrayList<>();

        /*
        * To get the strings, optString method is used since we are not sure if the data will be there or not.
        * If we use getString, it will throw an exception if the value is empty
        * */
        try{
            JSONObject sandwich_details = new JSONObject(json);
            JSONObject name = sandwich_details.getJSONObject("name");
            mainName = name.optString("mainName");
            //Parsing json Array
            also_known_as_array = name.getJSONArray("alsoKnownAs");
            alsoKnownAs = parseJsonArray(also_known_as_array);
            placeOfOrigin= sandwich_details.optString("placeOfOrigin");
            description = sandwich_details.optString("description");
            image_url = sandwich_details.optString("image");
            ingredient_array = sandwich_details.getJSONArray("ingredients");
            //Parsing json Array
            ingredients = parseJsonArray(ingredient_array);
        }catch (JSONException e){
            Log.e(TAG, e.toString());
        }
        //Create new Sandwich object with parsed Data from JSON Object
        sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image_url, ingredients);
        return sandwich;
    }

    protected static List<String> parseJsonArray(JSONArray array){
        List<String> newList = new ArrayList<>();
        if (array!=null){
            for (int i=0; i<array.length();i++){
                try {
                    newList.add(array.getString(i));
                } catch (JSONException e) {
                    Log.e(TAG,  e.toString());
                }
            }
        }
        return newList;
    }
}



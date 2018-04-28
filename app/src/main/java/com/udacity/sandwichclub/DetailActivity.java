package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getName();
    private Sandwich sandwich;
    private TextView also_known_label,
            also_known_as_textView,
            place_of_origin_label,
            place_of_origin_textView,
            ingredient_label,
            ingredient_view,
            description_label,
            description_view;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        also_known_label = findViewById(R.id.alias_label_textView);
        also_known_as_textView = findViewById(R.id.also_known_textView);

        place_of_origin_label = findViewById(R.id.place_label_textView);
        place_of_origin_textView = findViewById(R.id.place_of_origin_textView);

        ingredient_label = findViewById(R.id.ingredient_label_view);
        ingredient_view = findViewById(R.id.ingredient_textview);

        description_label = findViewById(R.id.des_label_view);
        description_view = findViewById(R.id.description_view);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        Log.i(TAG,sandwich.toString());
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    /*
    * For Lists,Simply Converting the List to String is printing only the last value
    * Since its replacing the old value with current value in each iteration inside for-loop
    * So, we read from buffer and append with a Stringbuilder object to get all the String values and then show it
    *
    * If there is no data found, then the labels and textviews will be hidden from user
    * */
    private void populateUI() {
        /*
        * READING FROM THE LISTS (Also know as List)
        * */
        StringBuilder mStringBuilder = new StringBuilder();
        if (sandwich.getAlsoKnownAs().isEmpty()){

            also_known_label.setVisibility(View.GONE); //Hide the textview
            also_known_as_textView.setVisibility(View.GONE);

        }else{
            String delm = "";
            for (String str : sandwich.getAlsoKnownAs())
                {
                    mStringBuilder.append(delm);
                    delm = ",";
                    mStringBuilder.append(" ");
                    mStringBuilder.append(str);
                }
            also_known_as_textView.setText(mStringBuilder);
        }

        /*
        * Getting place of origin data
        * */
        if (sandwich.getPlaceOfOrigin().isEmpty()){
            place_of_origin_label.setVisibility(View.GONE); //Hide the textview
            place_of_origin_textView.setVisibility(View.GONE);

        }else{
            place_of_origin_textView.setText(sandwich.getPlaceOfOrigin());
        }

        /*
        * Getting ingredients data
        * */
        if (sandwich.getIngredients().isEmpty()){
            ingredient_label.setVisibility(View.GONE); //Hide the textview
            ingredient_view.setVisibility(View.GONE);

        }else{
            String delm = "";
            for (String str : sandwich.getIngredients())
            {
                mStringBuilder.append(delm);
                delm = ",";
                mStringBuilder.append(" ");
                mStringBuilder.append(str);
            }
            ingredient_view.setText(mStringBuilder);
        }

        /*
        * Getting description data
        * */
        if (sandwich.getDescription().isEmpty()){
            description_label.setVisibility(View.GONE); //Hide the textview
            description_view.setVisibility(View.GONE);
        }else{
            description_view.setText(sandwich.getDescription());
        }


    }

}

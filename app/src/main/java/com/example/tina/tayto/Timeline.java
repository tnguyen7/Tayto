package com.example.tina.tayto;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Timeline extends AppCompatActivity {

    String productName;
    List<SpecificProduct> specificProductsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        Intent intent = getIntent();
        productName = intent.getStringExtra("name");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
/*
    class GetProducts extends AsyncTask<String, String, String> {

        private static final String url = "http://awtter.website/tayto_api/getUserProfile.php";
        JSONParser jParser;

        String username, profPic, prodPic, productName, description;
        JSONArray jsonArray;
        View view;

        public GetProducts(View view) {
            this.view = view;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            jParser = new JSONParser();

            Log.v("HomeTabsFragment", "In Async Task");
        }

        @Override
        @SuppressWarnings("deprecation")
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject obj = jParser.makeHttpRequest(url, "GET", params);

            Log.v("HomeTabsFragment", obj.toString());
            try {
                    jsonArray = obj.getJSONArray("products");

                    username = obj.getString("username");
                    profPic = "http://awtter.website/tayto_media/" + obj.getString("profile_pic") + ".jpg";

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);

                        productName = c.getString("name");
                        description = c.getString("description");
                        prodPic = "http://awtter.website/tayto_media/" + c.getString("picture") + ".jpg";

                        specificProductList.add(new SpecificProduct(productName, username, prodPic, profPic, description));
                    }

                    Log.v("HomeTabsFragment", String.valueOf(specificProductList.size()));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            RVAdapterGeneralProduct adapter = new RVAdapterGeneralProduct(generalProductList, getActivity());

        }

    }*/
}

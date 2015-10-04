package com.example.tina.tayto;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Timeline extends AppCompatActivity {

    public String username, product;
    List<UpdateProductPost> updatePostList;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        Intent intent = getIntent();
        username = "temp";
        product = intent.getStringExtra("product");
        Log.e("timeline", "Product: " + product);
        updatePostList = new ArrayList<UpdateProductPost>();

        //new GetProducts(this.findViewById(android.R.id.content).getRootView()).execute();

        rv = (RecyclerView) this.findViewById(android.R.id.content).getRootView().findViewById(R.id.rv);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);

        rv.setLayoutManager(layoutManager);
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

        String profPic, prodPic, description, date;
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
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("product", product));
            JSONObject obj = jParser.makeHttpRequest(url, "GET", params);

            Log.v("HomeTabsFragment", obj.toString());
            try {
                    jsonArray = obj.getJSONArray("products");

                    profPic = "http://awtter.website/tayto_media/" + obj.getString("profile_pic") + ".jpg";

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);

                        description = c.getString("description");
                        date = c.getString("date_added");
                        prodPic = "http://awtter.website/tayto_media/" + c.getString("picture") + ".jpg";


                        updatePostList.add(new UpdateProductPost(username, product,
                                description, date, prodPic, "null"));
                    }

                    Log.v("HomeTabsFragment", String.valueOf(updatePostList.size()));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            RVAdapterUpdateProductPost adapter = new RVAdapterUpdateProductPost(updatePostList, getApplicationContext());

            rv.setAdapter(adapter);
        }

    }*/
}

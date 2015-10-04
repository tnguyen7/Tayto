package com.example.tina.tayto;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// In this case, the fragment displays simple text based on the page
public class MyProducts extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    List<SpecificProduct> specificProductList;
    RecyclerView rv;

    public static MyProducts newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MyProducts fragment = new MyProducts();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_products, container, false);

        specificProductList = new ArrayList<SpecificProduct>();

        new GetSpecificProducts(view).execute();
        setUpCardView(view);

        return view;
    }

    void setUpCardView(View view) {
        rv = (RecyclerView) view.findViewById(R.id.rv);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);

        rv.setLayoutManager(layoutManager);

    }

    class GetSpecificProducts extends AsyncTask<String, String, String> {

        private static final String url = "http://awtter.website/tayto_api/homeFeed.php";
        JSONParser jParser;

        String username, description, picLoc;
        JSONArray jsonArray;
        View view;

        public GetSpecificProducts(View view) {
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
            if (mPage == 1) {
                params.add(new BasicNameValuePair("order_by", "popular"));
            } else {
                params.add(new BasicNameValuePair("order_by", "recents"));
            }
            JSONObject obj = jParser.makeHttpRequest(url, "GET", params);

            Log.v("HomeTabsFragment", obj.toString());
            try {

                jsonArray = obj.getJSONArray("products");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);
                    /*
                    Map<String,Object> product = new HashMap<String,Object>();
                    product.put("total_rating",obj.getInt("total_rating"));
                    product.put("num_ratings",obj.getInt("num_ratings"));*/

                    username = c.getString("username");
                    Log.v("HomeTabsFragment", username);
                    String productName = c.getString("name");
                    String prodLoc = "http://awtter.website/tayto_media/" + c.getString("picture") + ".jpg";

                    specificProductList.add(new SpecificProduct(productName, username, prodLoc, picLoc));
                }

                Log.v("HomeTabsFragment", String.valueOf(specificProductList.size()));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            //if else mpage statement here
            RVAdapterSpecificProduct adapter = new RVAdapterSpecificProduct(specificProductList, getActivity());

            rv.setAdapter(adapter);
        }

    }

}

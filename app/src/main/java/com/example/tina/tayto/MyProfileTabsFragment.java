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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tina on 10/4/2015.
 */
public class MyProfileTabsFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    List<GeneralProduct> generalProductList;
    List<SpecificProduct> specificProductList;

    RecyclerView rv;

    public static MyProfileTabsFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MyProfileTabsFragment fragment = new MyProfileTabsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

        generalProductList = new ArrayList<GeneralProduct>();
        specificProductList = new ArrayList<SpecificProduct>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile_tabs, container, false);

        new GetProducts(view).execute();

        rv = (RecyclerView) view.findViewById(R.id.rv);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);

        rv.setLayoutManager(layoutManager);

        return view;
    }

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
                if (mPage == 1) {
                    jsonArray = obj.getJSONArray("products");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);
                    /*
                    Map<String,Object> product = new HashMap<String,Object>();
                    product.put("total_rating",obj.getInt("total_rating"));
                    product.put("num_ratings",obj.getInt("num_ratings"));*/

                        String productName = c.getString("name");
                        String prodLoc = "http://awtter.website/tayto_media/" + c.getString("picture") + ".jpg";

                        generalProductList.add(new GeneralProduct(productName, prodLoc));
                    }

                    Log.v("HomeTabsFragment", String.valueOf(generalProductList.size()));
                } else {
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
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            if (mPage == 1) {
                //if else mpage statement here
                RVAdapterGeneralProduct adapter = new RVAdapterGeneralProduct(generalProductList, getActivity());

                rv.setAdapter(adapter);
            } else {
                RVAdapterSpecificProduct adapter = new RVAdapterSpecificProduct(specificProductList, getActivity());

                rv.setAdapter(adapter);
            }
        }

    }



}

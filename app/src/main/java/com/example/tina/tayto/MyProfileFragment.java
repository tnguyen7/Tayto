package com.example.tina.tayto;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfileFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    Context context;
    String username, profPic, profDesc;

    private OnFragmentInteractionListener mListener;


    public MyProfileFragment() {
        // Required empty public constructor
    }

    public static MyProfileFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MyProfileFragment fragment = new MyProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_my_profile, container, false);
        new GetProfileInformation(view).execute();

        context = getActivity();

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager_profile);
        viewPager.setAdapter(new MyProfileTabsAdapter(getFragmentManager(),
                getActivity()));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs_profile);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    class GetProfileInformation extends AsyncTask<String, String, String> {

        private static final String url = "http://awtter.website/tayto_api/getUserProfile.php";
        JSONParser jParser;
        View view;

        public GetProfileInformation(View view) {
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

                username = obj.getString("username");
                profPic = "http://awtter.website/tayto_media/" + obj.getString("profile_pic") + ".jpg";
                profDesc = obj.getString("profile_desc");
                Log.v("Profile Info: ", username + profPic + profDesc);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            TextView userText = (TextView) view.findViewById(R.id.username);
            userText.setText(username);
            TextView userDesc = (TextView) view.findViewById(R.id.description);
            userDesc.setText(profDesc);
            ImageView profPicIV = (ImageView) view.findViewById(R.id.imageButton1);

            Picasso.with(getActivity())
                    .load(profPic)
                    .into(profPicIV);
        }
    }

}

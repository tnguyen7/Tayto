package com.example.tina.tayto;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,
        MySubscriptionsFragment.OnFragmentInteractionListener, MyProfileFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener{

    private static final String TAG = "MainActivity";
    DrawerLayout drawerLayout;
    View content;
    String title;
    private static final String HOME = "Home";
    private static final String MY_SUBS = "My Subscriptions";
    private static final String MY_PROFILE = "MyProfile";
    private static final String SETTINGS = "Settings";

    HomeFragment homeFragment;
    MySubscriptionsFragment mySubFragment;
    MyProfileFragment myProfileFragment;
    SettingsFragment settingsFragment;
    Fragment currentFragment;

    EditText username, password;

    boolean loginSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_in_screen);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        Button button = (Button) findViewById(R.id.btnSignIn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SignIn(username.getText().toString(), password.getText().toString());
            }
        });

        setUpFragments();

    }

    private void setUpFragments() {

        currentFragment = getSupportFragmentManager().findFragmentByTag(getString(R.string.home));

        if (currentFragment == null) {

            homeFragment = new HomeFragment();
            mySubFragment = new MySubscriptionsFragment();
            myProfileFragment = new MyProfileFragment();
            settingsFragment = new SettingsFragment();


            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, settingsFragment, getString(R.string.settings))
                    .hide(settingsFragment)
                    .add(R.id.content, myProfileFragment, getString(R.string.my_profile))
                    .hide(myProfileFragment)
                    .add(R.id.content, mySubFragment, getString(R.string.my_subs))
                    .hide(mySubFragment)
                    .add(R.id.content, homeFragment, getString(R.string.home))
                    .commit();
            currentFragment = homeFragment;

        } else {

            homeFragment = (HomeFragment) currentFragment;
            myProfileFragment = (MyProfileFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.my_profile));
            mySubFragment = (MySubscriptionsFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.my_subs));
            settingsFragment = (SettingsFragment) getSupportFragmentManager().findFragmentByTag(getString(R.string.settings));

            getSupportFragmentManager().beginTransaction()
                    .hide(settingsFragment)
                    .hide(myProfileFragment)
                    .hide(mySubFragment)
                    .commit();

        }
    }

    public void createNavigationDrawer() {
        // Set content view to actual content/FrameLayout ( for snackbar )
        content = findViewById(R.id.content);

        // Initialize Navigation drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Initialize NavigationView
        final NavigationView view = (NavigationView) findViewById(R.id.navigation_view);


        // Set up listener so that when a person clicks on an item
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                title = menuItem.getTitle().toString();

                switch (title) {

                    case (HOME): {

                        switchTo(homeFragment, title);
                        break;

                    }
                    case (MY_SUBS): {

                        switchTo(mySubFragment, title);
                        /* refresh listener
                        mySubFragment.mySwipeRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.v(TAG, "refreshing");
                                mySubFragment.refreshListener.onRefresh();
                            }
                        });*/
                        break;

                    }
                    case (MY_PROFILE): {

                        switchTo(myProfileFragment, title);
                        break;

                    }
                    case (SETTINGS): {

                        switchTo(settingsFragment, title);
                        break;


                    }

                }
                // Make snackbar on content(FrameLayout) view on teh bottom
                //Snackbar.make(content, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();

                // Make it checked/highlighted when pressed
                menuItem.setChecked(true);

                // Close the drawer
                drawerLayout.closeDrawers();

                return true;
            }

        });

    }

    private void switchTo (Fragment fragment, String name) {

        if (fragment.isVisible())
            return;

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();

        // Make sure the next view is below the current one
        fragment.getView().bringToFront ();
        // And bring the current one to the very top
        currentFragment.getView().bringToFront ();

        // Hide the current fragment
        t.hide (currentFragment);
        t.show(fragment);
        currentFragment = fragment;

        t.addToBackStack(null);
        t.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class SignIn extends AsyncTask<String, String, String> {

        private static final String url = "http://awtter.website/Tayto/signin.php";
        private static final String TAG_USERNAME = "username";
        private static final String TAG_PASSWORD = "password";

        String username, password;


        JSONParser jParser;
        public SignIn(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            jParser = new JSONParser();
        }

        @Override
        @SuppressWarnings("deprecation")
        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_USERNAME, username));
            params.add(new BasicNameValuePair(TAG_PASSWORD, password));

            JSONObject json = jParser.makeHttpRequest(url, "POST", params);

            try {
                Log.d("JSON: ", json.toString());
                loginSuccess = true;
            } catch (NullPointerException npe) {
                loginSuccess = false;
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            if (loginSuccess) {
                setContentView(R.layout.activity_main);
                createNavigationDrawer();
            } else {
                Log.d(TAG, "unsuccessful login!");
            }
        }
    }

}

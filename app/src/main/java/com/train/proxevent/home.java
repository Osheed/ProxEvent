package com.train.proxevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


//TODO: Sortir tous les strings comme dans display activity

public class home extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Tracker mTracker;

    private Button goAdminBtn;
    private ViewPager mViewPager;
    private HomePagerAdapter mHomePagerAdapter;
    private TabLayout mTabLayout;

    private GoogleMap mymap;

    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(R.string.Home);

        // initializes
        mAuth = FirebaseAuth.getInstance();
        mViewPager = (ViewPager) findViewById(R.id.home_tabPager);

        mHomePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());

        //Fragments
        mViewPager.setAdapter(mHomePagerAdapter);
        mTabLayout = (TabLayout) findViewById(R.id.home_tabs);
        mTabLayout.setupWithViewPager(mViewPager);


    }


    // When the user click on Login button
    public void seeDisplayActivityTest(View view) {
        Intent goTopics = new Intent(this, display_activity.class);
        startActivity(goTopics);
    }


    public void goAdminMessages(View view) {
        Intent goToMessages = new Intent(this, messageFromAdmin.class);
        startActivity(goToMessages);


    }


    private void sendToStart() {
        Intent loginIntent = new Intent(home.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }


    /* menu */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_home_map:
                Intent goHomeMap = new Intent(this, home.class);
                startActivity(goHomeMap);
                return true;

            case R.id.action_topics:
                Intent goTopics = new Intent(this, topic_list.class);
                startActivity(goTopics);
                return true;

            case R.id.action_add_activity:
                Intent goAdd = new Intent(this, new_activity.class);
                startActivity(goAdd);
                return true;

            case R.id.action_messages:
                Intent goMessage = new Intent(this, messageFromAdmin.class);
                startActivity(goMessage);
                return true;

            case R.id.action_my_activities:
                Intent goActicities = new Intent(this, my_activities.class);
                startActivity(goActicities);
                return true;

            case R.id.action_profile:
                Intent goProfile = new Intent(this, profile.class);
                startActivity(goProfile);
                return true;

            case R.id.action_settings:
                Intent goSettings = new Intent(this, settings.class);
                startActivity(goSettings);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed
        FirebaseUser currentUser = mAuth.getCurrentUser();


        if (currentUser == null) {
            sendToStart();
        }


    }


}
package com.train.proxevent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.train.proxevent.Objects.Activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by David on 14.07.2017.
 */

//TODO: Fragment carte dans home et fragment activity list dans le 2e
//TODO: Delete l'utilisateur
//TODO: Controler que dans home l'affichage des activités se fait a partir d'aujourd'hui
//TODO: Detruire une activity si on est le proprietaire
//TODO: Sortir tous les strings comme dans display activity

public class home extends AppCompatActivity  {

    private FirebaseAuth mAuth;


    private Button goAdminBtn;
    private ViewPager mViewPager;
    private HomePagerAdapter mHomePagerAdapter;
    private TabLayout mTabLayout;

    private GoogleMap mymap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(R.string.Home);

        // initializes
        mAuth = FirebaseAuth.getInstance();
        mViewPager = (ViewPager)findViewById(R.id.home_tabPager);

        mHomePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());

        //Fragments
        mViewPager.setAdapter(mHomePagerAdapter);
        mTabLayout = (TabLayout)findViewById(R.id.home_tabs);
        mTabLayout.setupWithViewPager(mViewPager);


    }


    // When the user click on Login button
    public void seeDisplayActivityTest(View view) {
        Intent goTopics = new Intent(this, display_activity.class);
        startActivity(goTopics);


    }

    public void goAdmin(View view) {
        //  Intent goToAdmin = new Intent(this, messageFromAdmin.class);
        Intent goToAdmin = new Intent(this, admin.class);
        startActivity(goToAdmin);


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
                Intent goMessage = new Intent(this, notification_list.class);
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

/*
        // FirebaseUser currentUser = mAuth.getCurrentUser();
        // Log.i("currentUser", "you are admin + "+currentUser.getUid());
        String uid = currentUser.getUid();
        String admin = "mJ4aJAWSSeeQ8mEcAtTySQlPJOU2";

        if (uid.equalsIgnoreCase(admin)) {
            goAdminBtn = (Button) findViewById(R.id.goAdmin);
            goAdminBtn.setVisibility(View.VISIBLE);
        }
*/

    }


}


package com.train.proxevent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;
import com.train.proxevent.Objects.Activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by David on 14.07.2017.
 */


public class admin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private RecyclerView rv_currActivities;
    private DatabaseReference mActivityDatabase;
    private Button goAdminBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setTitle(R.string.Admin);

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

}


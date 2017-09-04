package com.train.proxevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by David on 14.07.2017.
 */


public class admin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private RecyclerView rv_currActivities;
    private DatabaseReference mActivityDatabase;
    private Button goAdminBtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference Ref = database.getReference();
    ListView list_of_tables;
    TextView text;
    private int cpt;

    MapFragment mapFragment = (MapFragment) getFragmentManager()
            .findFragmentById(R.id.map);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setTitle(R.string.Admin);

        cpt = 1;


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
//You must remember to remove the listener when you finish using it, also to keep track of changes you can use the ChildChange


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (cpt == 1) {
                    TextView textView1 = (TextView) findViewById(R.id.textView1);
                    textView1.setText(dataSnapshot.getKey());

                    TextView textView2 = (TextView) findViewById(R.id.textView2);
                    textView2.setText(String.valueOf(dataSnapshot.getChildrenCount()));
                }
                if (cpt == 2) {

                    TextView textView3 = (TextView) findViewById(R.id.textView3);
                    textView3.setText(dataSnapshot.getKey());

                    TextView textView4 = (TextView) findViewById(R.id.textView4);
                    textView4.setText(String.valueOf(dataSnapshot.getChildrenCount()));

                }
                if (cpt == 3) {
                    TextView textView5 = (TextView) findViewById(R.id.textView5);
                    textView5.setText(dataSnapshot.getKey());
                    TextView textView6 = (TextView) findViewById(R.id.textView6);
                    textView6.setText(String.valueOf(dataSnapshot.getChildrenCount()));
                }
                if (cpt == 4) {
                    TextView textView7 = (TextView) findViewById(R.id.textView7);
                    textView7.setText(dataSnapshot.getKey());
                    TextView textView8 = (TextView) findViewById(R.id.textView8);
                    textView8.setText(String.valueOf(dataSnapshot.getChildrenCount()));
                }
                if (cpt == 5) {
                    TextView textView9 = (TextView) findViewById(R.id.textView9);
                    textView9.setText(dataSnapshot.getKey());
                    TextView textView10 = (TextView) findViewById(R.id.textView10);
                    textView10.setText(String.valueOf(dataSnapshot.getChildrenCount()));
                }


                cpt++;


                //  Log.e(dataSnapshot.getKey(),dataSnapshot.getChildrenCount() + "aaa");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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


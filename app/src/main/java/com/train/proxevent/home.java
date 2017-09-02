package com.train.proxevent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.train.proxevent.Objects.User;

/**
 * Created by David on 14.07.2017.
 */


public class home extends AppCompatActivity implements OnMapReadyCallback {

    private FirebaseAuth mAuth;

    private GoogleMap mymap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(R.string.Home);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);





        // initializes
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();

        /**
         *
         *    SELECT * FROM user WHERE email = 'user.getEmail()';
         *
         *
         *    ==
         *
         *    new Firebase("https://examples-sql-queries.firebaseio.com/user")
         .startAt('user.getEmail()')
         .endAt('user.getEmail()')
         .once('value', show);

         function show(snap) {
         $('pre').text(JSON.stringify(snap.val(), null, 2));
         }

         **/
    }



    // When the user click on Login button
    public void seeDisplayActivityTest(View view) {
        Intent goTopics = new Intent(this, display_activity.class);
        startActivity(goTopics);


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            sendToStart();
        }
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

            case R.id.action_favorite:
                Intent goFavorite = new Intent(this, favorites_list.class);
                startActivity(goFavorite);
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
    public void onMapReady(GoogleMap map) {
        mymap = map;
        mymap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mymap.getUiSettings().setZoomControlsEnabled(true);
        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(46.2443, 7.3250), 10);

        mymap.moveCamera(camUpd1);

    }
}


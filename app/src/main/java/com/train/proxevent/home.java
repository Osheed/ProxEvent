package com.train.proxevent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.train.proxevent.Objects.Activities;
import com.train.proxevent.Objects.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by David on 14.07.2017.
 */

//TODO: Receive a notification
//TODO: View detail of a notification
//TODO: Controler que dans home l'affichage des activités se fait a partir d'aujourd'hui
//TODO: Detruire une activity si on est le proprietaire
//TODO: Sortir tous les strings comme dans display activity

public class home extends AppCompatActivity implements OnMapReadyCallback {

    private FirebaseAuth mAuth;
    private RecyclerView rv_currActivities;
    private DatabaseReference mActivityDatabase;
    private Button goAdminBtn;

    private GoogleMap mymap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(R.string.Home);


        //Current activities
        rv_currActivities = (RecyclerView) findViewById(R.id.rv_home_currActivities);
        rv_currActivities.setHasFixedSize(true);
        rv_currActivities.setLayoutManager(new LinearLayoutManager(this));

        mActivityDatabase = FirebaseDatabase.getInstance().getReference("Activities").child("All");
        Query sortByDate = mActivityDatabase.orderByChild("Act_date_end");


        // Map
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
    public void goAdmin(View view) {
        Intent goToAdmin = new Intent(this, admin.class);
        startActivity(goToAdmin);


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
    public void onMapReady(GoogleMap map) {
        mymap = map;
        mymap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mymap.getUiSettings().setZoomControlsEnabled(true);
        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(46.2443, 7.3250), 10);

        mymap.moveCamera(camUpd1);

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed
        FirebaseUser currentUser = mAuth.getCurrentUser();

        //Today
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(todayDate);

        if(currentUser == null){
            sendToStart();
        }


        // FirebaseUser currentUser = mAuth.getCurrentUser();
        // Log.i("currentUser", "you are admin + "+currentUser.getUid());
        String uid = currentUser.getUid();
        String admin ="mJ4aJAWSSeeQ8mEcAtTySQlPJOU2";

        if (uid.equalsIgnoreCase(admin)) {
            goAdminBtn = (Button) findViewById(R.id.goAdmin);
            goAdminBtn.setVisibility(View.VISIBLE);
        }
        FirebaseRecyclerAdapter<Activities, activity_list.ActivityViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter <Activities, activity_list.ActivityViewHolder>(
                        Activities.class,
                        R.layout.activity_list_layout,
                        activity_list.ActivityViewHolder.class,
                        mActivityDatabase.orderByChild("Act_date_end").endAt("31-12-2100")
                ){
                    @Override
                    protected void populateViewHolder(activity_list.ActivityViewHolder viewHolder, Activities model, int position){


                        viewHolder.setAdresse(model.getAct_adresse());
                        //viewHolder.setContent(model.getAct_content());
                        viewHolder.setDate_crea(model.getAct_date_crea());
                        viewHolder.setDate_end(model.getAct_date_end());
                        //viewHolder.setLatitude(model.getAct_latitude());
                        //viewHolder.setLongitude(model.getAct_longitude());
                        //viewHolder.setOwner(model.getAct_owner());
                        viewHolder.setTitle(model.getAct_title());
                        //viewHolder.setTopic(model.getAct_topic());
                        viewHolder.setActImage(model.getAct_image(),getApplicationContext());


                        //retrieve the key of activity clicked
                        final String activity_id = getRef(position).getKey();

                        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent activityIntent = new Intent(home.this, display_activity.class);
                                activityIntent.putExtra("idActivity", activity_id);
                                startActivity(activityIntent);
                            }
                        });

                    }


                };

        rv_currActivities.setAdapter(firebaseRecyclerAdapter);

    }
    //to retrieve the data
    public static class ActivityViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public ActivityViewHolder(View itemView){
            super(itemView);

            mView = itemView;
        }


        public void setAdresse(String adresse) {
            TextView activityAdress = (TextView) mView.findViewById(R.id.tv_AL_Adresse);
            activityAdress.setText(adresse);
        }

        public void setDate_crea(String date_crea){
            TextView activityDate_crea = (TextView) mView.findViewById(R.id.tv_AL_dateCrea);
            activityDate_crea.setText(date_crea);
        }

        public void setDate_end(String date_end){
            TextView activityDate_end = (TextView) mView.findViewById(R.id.tv_AL_dateEnd);
            activityDate_end.setText(date_end);
        }

        public void setTitle(String title){
            TextView activityTitle = (TextView) mView.findViewById(R.id.tv_AL_Title);
            activityTitle.setText(title);
        }

        public void setActImage(String act_image, Context applicationContext) {
            CircleImageView activityImage = (CircleImageView)mView.findViewById(R.id.civ_AL_image);
            Picasso.with(applicationContext).load(act_image).placeholder(R.drawable.ic_action_clock).into(activityImage);
        }
    }








}


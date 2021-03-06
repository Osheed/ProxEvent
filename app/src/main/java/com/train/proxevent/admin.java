package com.train.proxevent;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.maps.MapFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


//TODO: Sortir tous les strings comme dans display activity

public class admin extends AppCompatActivity implements OnChartValueSelectedListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference Ref = database.getReference();
    ListView list_of_tables;
    TextView text;
    MapFragment mapFragment = (MapFragment) getFragmentManager()
            .findFragmentById(R.id.map);
    private Tracker mTracker;
    private FirebaseAuth mAuth;
    private RecyclerView rv_currActivities;
    private DatabaseReference mActivityDatabase;
    private Button goAdminBtn;
    private int cpt;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        /**  ArrayList<Entry> yvalues = new ArrayList<Entry>();
         DataSnapshot dataSnapshot = null;

         yvalues.add(new Entry(15f, 1));
         yvalues.add(new Entry(12f, 2));
         yvalues.add(new Entry(25f, 3));
         yvalues.add(new Entry(23f, 4));
         yvalues.add(new Entry(17f, 5));

         PieDataSet dataSet = new PieDataSet(yvalues, "Election Results");

         ArrayList<String> xVals = new ArrayList<String>();

         xVals.add("Activities");
         xVals.add("Messages from Admin");
         xVals.add("User Activities");
         xVals.add("Users");
         xVals.add("Mail");
         xVals.add("Messages");

         PieData data = new PieData(xVals, dataSet);
         data.setValueFormatter(new PercentFormatter());
         pieChart.setData(data);
         pieChart.setDescription("This is Pie Chart");

         pieChart.setDrawHoleEnabled(true);
         pieChart.setTransparentCircleRadius(25f);
         pieChart.setHoleRadius(25f);

         dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
         data.setValueTextSize(13f);
         data.setValueTextColor(Color.DKGRAY);
         pieChart.setOnChartValueSelectedListener(this);

         pieChart.animateXY(1400, 1400);**/

        cpt = 1;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        //You must remember to remove the listener when you finish using it, also to keep track of changes you can use the ChildChange


        myRef.addChildEventListener(new ChildEventListener() {


            ArrayList<Entry> yvalues = new ArrayList<Entry>();
            PieDataSet dataSet = new PieDataSet(yvalues, "Election Results");
            ArrayList<String> xVals = new ArrayList<String>();
            PieData data = new PieData(xVals, dataSet);
            PieChart pieChart = (PieChart) findViewById(R.id.piechart);

            //  pieChart.setOnChartValueSelectedListener(this);


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (cpt == 1) {

                    TextView textView3 = (TextView) findViewById(R.id.textView1);
                    textView3.setText(dataSnapshot.getKey());

                    TextView textView4 = (TextView) findViewById(R.id.textView2);
                    textView4.setText(String.valueOf(dataSnapshot.getChildrenCount()));

                    float num = dataSnapshot.getChildrenCount();
                    xVals.add("Activities");
                    yvalues.add(new Entry(num, 1));

                }
                if (cpt == 2) {

                    TextView textView3 = (TextView) findViewById(R.id.textView3);
                    textView3.setText(dataSnapshot.getKey());

                    TextView textView4 = (TextView) findViewById(R.id.textView4);
                    textView4.setText(String.valueOf(dataSnapshot.getChildrenCount()));

                    float num = dataSnapshot.getChildrenCount();
                    xVals.add("Admin Messages");
                    yvalues.add(new Entry(num, 2));

                }
                if (cpt == 3) {
                    TextView textView5 = (TextView) findViewById(R.id.textView5);
                    textView5.setText(dataSnapshot.getKey());
                    TextView textView6 = (TextView) findViewById(R.id.textView6);
                    textView6.setText(String.valueOf(dataSnapshot.getChildrenCount()));

                    float num = dataSnapshot.getChildrenCount();
                    xVals.add("Users Activities");
                    yvalues.add(new Entry(num, 3));
                }
                if (cpt == 4) {
                    TextView textView7 = (TextView) findViewById(R.id.textView7);
                    textView7.setText(dataSnapshot.getKey());
                    TextView textView8 = (TextView) findViewById(R.id.textView8);
                    textView8.setText(String.valueOf(dataSnapshot.getChildrenCount()));

                    float num = dataSnapshot.getChildrenCount();
                    xVals.add("Users");
                    yvalues.add(new Entry(num, 4));
                }
                if (cpt == 5) {
                    TextView textView9 = (TextView) findViewById(R.id.textView9);
                    textView9.setText(dataSnapshot.getKey());
                    TextView textView10 = (TextView) findViewById(R.id.textView10);
                    textView10.setText(String.valueOf(dataSnapshot.getChildrenCount()));

                    float num = dataSnapshot.getChildrenCount();
                    xVals.add("Messages");
                    yvalues.add(new Entry(num, 5));
                }


                cpt++;

                PieData data = new PieData(xVals, dataSet);
                data.setValueFormatter(new PercentFormatter());
                pieChart.setData(data);
                pieChart.setDescription("This is Pie Chart");

                pieChart.setDrawHoleEnabled(true);
                pieChart.setTransparentCircleRadius(25f);
                pieChart.setHoleRadius(25f);

                dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
                data.setValueTextSize(13f);
                data.setValueTextColor(Color.DKGRAY);

                pieChart.setUsePercentValues(true);


                pieChart.animateXY(1400, 1400);

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

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}

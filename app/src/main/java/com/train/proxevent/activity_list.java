package com.train.proxevent;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.train.proxevent.Objects.Activities;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

public class activity_list extends AppCompatActivity {


    private RecyclerView mActivityList;
    private DatabaseReference mActivityDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mActivityList = (RecyclerView)findViewById(R.id.rv_activities_list);
        mActivityList.setHasFixedSize(true);
        mActivityList.setLayoutManager(new LinearLayoutManager(this));

        String choix = getIntent().getStringExtra("topicSelected");
        mActivityDatabase = FirebaseDatabase.getInstance().getReference().child("Activities").child(choix);


    }

    //Recycler adapter


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter <Activities, ActivityViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter <Activities, ActivityViewHolder>(
                        Activities.class,
                        R.layout.activity_list_layout,
                        ActivityViewHolder.class,
                        mActivityDatabase
                ){
                    protected void populateViewHolder(ActivityViewHolder viewHolder, Activities model, int position){


                        viewHolder.setAdresse(model.getAct_adresse());
                        //viewHolder.setContent(model.getAct_content());
                        viewHolder.setDate_crea(model.getAct_date_crea());
                        viewHolder.setDate_end(model.getAct_date_end());
                        //viewHolder.setLatitude(model.getAct_latitude());
                        //viewHolder.setLongitude(model.getAct_longitude());
                        //viewHolder.setOwner(model.getAct_owner());
                        viewHolder.setTitle(model.getAct_title());
                        //viewHolder.setTopic(model.getAct_topic());
                        //viewHolder.setActImage(model.getAct_image(),getApplicationContext());

                      /*
                        //retrieve the key of activity clicked
                        final String activity_id = getRef(position).getKey();

                        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent activityIntent = new Intent(activity_list.this, display_activity.class);
                                activityIntent.putExtra("idActivity", activity_id);
                                startActivity(activityIntent);
                            }
                        });
                        */
                    }


                };

        mActivityList.setAdapter(firebaseRecyclerAdapter);

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

        public void setContent(String content) {

        }

        public void setDate_crea(String date_crea){
            TextView activityDate_crea = (TextView) mView.findViewById(R.id.tv_AL_dateCrea);
            activityDate_crea.setText(date_crea);
        }

        public void setDate_end(String date_end){
            TextView activityDate_end = (TextView) mView.findViewById(R.id.tv_AL_dateEnd);
            activityDate_end.setText(date_end);
        }

        public void setLatitude(String latitude) {

        }

        public void setLongitude(String longitude){

        }

        public void setOwner(String owner) {

        }

        public void setTitle(String title){
            TextView activityTitle = (TextView) mView.findViewById(R.id.tv_AL_Title);
            activityTitle.setText(title);
        }

        public void setTopic(String topic) {

        }

        public void setActImage(String act_image, Context applicationContext) {
            CircleImageView activityImage = (CircleImageView)mView.findViewById(R.id.civ_AL_image);
            Picasso.with(applicationContext).load(act_image).placeholder(R.drawable.ic_action_clock).into(activityImage);
        }
    }

}




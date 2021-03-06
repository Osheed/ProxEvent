package com.train.proxevent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.train.proxevent.Objects.Activities;

import de.hdodenhof.circleimageview.CircleImageView;

public class my_activities extends AppCompatActivity {


    int nbActivities;
    String current_uid;
    private RecyclerView mActivityList;

    private Query mActivityDatabase;
    private FirebaseUser mCurrentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activities);


        //Recover the data from db
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        current_uid = mCurrentUser.getUid();


        mActivityList = (RecyclerView) findViewById(R.id.rv_activities_list);
        mActivityList.setHasFixedSize(true);
        mActivityList.setLayoutManager(new LinearLayoutManager(this));
        mActivityDatabase = FirebaseDatabase.getInstance().getReference().child("Activities").child("All");
        mActivityDatabase.orderByChild("Act_owner").equalTo(current_uid);

        mActivityDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    nbActivities += 1;
                }
                setTitle("My activities ");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    //Recycler adapter

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Activities, activity_list.ActivityViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Activities, activity_list.ActivityViewHolder>(
                        Activities.class,
                        R.layout.activity_list_layout,
                        activity_list.ActivityViewHolder.class,
                        mActivityDatabase.orderByChild("Act_owner").equalTo(current_uid)
                ) {
                    @Override
                    protected void populateViewHolder(activity_list.ActivityViewHolder viewHolder, Activities model, int position) {


                        viewHolder.setAdresse(model.getAct_adresse());
                        viewHolder.setDate_crea(model.getAct_date_crea());
                        viewHolder.setDate_end(model.getAct_date_end());
                        viewHolder.setTitle(model.getAct_title());
                        viewHolder.setActImage(model.getAct_image(), getApplicationContext());

                        //retrieve the key of activity clicked
                        final String activity_id = getRef(position).getKey();

                        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent activityIntent = new Intent(my_activities.this, display_activity.class);
                                activityIntent.putExtra("idActivity", activity_id);
                                activityIntent.putExtra("topic", "All");
                                startActivity(activityIntent);
                            }
                        });

                    }

                };

        mActivityList.setAdapter(firebaseRecyclerAdapter);

    }

    //to retrieve the data
    public static class ActivityViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public ActivityViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }


        public void setAdresse(String adresse) {
            TextView activityAdress = (TextView) mView.findViewById(R.id.tv_AL_Adresse);
            activityAdress.setText(adresse);
        }

        public void setDate_crea(String date_crea) {
            TextView activityDate_crea = (TextView) mView.findViewById(R.id.tv_AL_dateCrea);
            activityDate_crea.setText(date_crea);
        }

        public void setDate_end(String date_end) {
            TextView activityDate_end = (TextView) mView.findViewById(R.id.tv_AL_dateEnd);
            activityDate_end.setText(date_end);
        }

        public void setTitle(String title) {
            TextView activityTitle = (TextView) mView.findViewById(R.id.tv_AL_Title);
            activityTitle.setText(title);
        }

        public void setActImage(String act_image, Context applicationContext) {
            CircleImageView activityImage = (CircleImageView) mView.findViewById(R.id.civ_AL_image);
            Picasso.with(applicationContext).load(act_image).placeholder(R.drawable.ic_action_clock).into(activityImage);
        }
    }

}




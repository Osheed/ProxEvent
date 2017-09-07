package com.train.proxevent;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
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


public class CurrentActivitiesFragment extends Fragment {

    private DatabaseReference mActivityDatabase;

    private FirebaseAuth mAuth;
    private View mMainView;
    private RecyclerView rv_currActivities;

    public CurrentActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMainView = inflater.inflate(R.layout.fragment_current_activities, container, false);
        //Current activities
        rv_currActivities = (RecyclerView) mMainView.findViewById(R.id.rv_home_currActivities);
        rv_currActivities.setHasFixedSize(true);
        rv_currActivities.setLayoutManager(new LinearLayoutManager(getContext()));

        mActivityDatabase = FirebaseDatabase.getInstance().getReference("Activities").child("All");


        // initializes
        mAuth = FirebaseAuth.getInstance();


        // Inflate the layout for this fragment
        return mMainView;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed
        FirebaseUser currentUser = mAuth.getCurrentUser();

        //Today
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(todayDate);


        FirebaseRecyclerAdapter<Activities, activity_list.ActivityViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Activities, activity_list.ActivityViewHolder>(
                        Activities.class,
                        R.layout.activity_list_layout,
                        activity_list.ActivityViewHolder.class,
                        mActivityDatabase.orderByChild("Act_date_end").endAt("31-12-2100")
                ) {
                    @Override
                    protected void populateViewHolder(activity_list.ActivityViewHolder viewHolder, Activities model, int position) {


                        viewHolder.setAdresse(model.getAct_adresse());
                        //viewHolder.setContent(model.getAct_content());
                        viewHolder.setDate_crea(model.getAct_date_crea());
                        viewHolder.setDate_end(model.getAct_date_end());
                        //viewHolder.setLatitude(model.getAct_latitude());
                        //viewHolder.setLongitude(model.getAct_longitude());
                        //viewHolder.setOwner(model.getAct_owner());
                        viewHolder.setTitle(model.getAct_title());
                        //viewHolder.setTopic(model.getAct_topic());
                        viewHolder.setActImage(model.getAct_image(), getContext());


                        //retrieve the key of activity clicked
                        final String activity_id = getRef(position).getKey();
                        final String activity_topic = model.getAct_topic();
                        final String activity_owner = model.getAct_owner();

                        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent activityIntent = new Intent(getContext(), display_activity.class);
                                activityIntent.putExtra("idActivity", activity_id);
                                activityIntent.putExtra("topic", activity_topic);
                                activityIntent.putExtra("owner", activity_owner);
                                startActivity(activityIntent);
                            }
                        });

                    }


                };

        rv_currActivities.setAdapter(firebaseRecyclerAdapter);

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

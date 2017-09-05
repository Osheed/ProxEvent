package com.train.proxevent;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.train.proxevent.Objects.Activities;
import com.train.proxevent.Objects.UserActivity;
import com.train.proxevent.Objects.Users;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

public class display_activity extends AppCompatActivity {

    user_list_adapter user_list_adapter;
    private DatabaseReference mUserDatabase, currentUserDB, currentActivity, mCurrentUserCreator, mUserActivityDatabase, mUserActivityDatabaseAdd;
    private FirebaseUser mCurrentUser;
    private Users users;
    private ListView listView;
    FloatingActionButton fab;
    private FloatingActionButton fabDelete;
    String myValueIdActivity, myValueTopic, mUserActivity_id, currentUserId;
    Activities activities_displayed;
    //Progress
    private ProgressDialog mProgress;
    ImageView imageTop, imageCreator;
    TextView activity_Tile, activity_content, activityDate, activityDateEnd, tvCreator, tvEmptyUserCurrentList;
    UserActivity userActivity;
    private FirebaseAuth mAuth;
    private DatabaseReference mActivityDatabase;
    private String Continue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_activity);
        listView = (ListView) findViewById(R.id.usersList);
        user_list_adapter = new user_list_adapter(getApplicationContext(), R.id.userchat_list_layout);
        imageTop = (ImageView) findViewById(R.id.iv_toolbar_detail);
        imageCreator = (ImageView) findViewById(R.id.tvimageCreator);
        activity_Tile = (TextView) findViewById(R.id.tvTitle);
        activity_content = (TextView) findViewById(R.id.tvActivityContent);
        activityDate = (TextView) findViewById(R.id.activityDate);
        activityDateEnd = (TextView) findViewById(R.id.activityDateEnd);
        tvCreator = (TextView) findViewById(R.id.tvCreator);
        tvEmptyUserCurrentList = (TextView) findViewById(R.id.tvEmptyUserCurrentList);
        listView.setAdapter(user_list_adapter);
        fabDelete = (FloatingActionButton) findViewById(R.id.fab_displayActivity_Delete);
        mAuth = FirebaseAuth.getInstance();


        setUserDetails();

        myValueIdActivity = getIntent().getExtras().getString("idActivity");
        myValueTopic = getIntent().getExtras().getString("topic");


        //Recover the data from db
        mUserActivityDatabaseAdd = FirebaseDatabase.getInstance().getReference().child("UserActivity");



        mUserActivity_id = mUserActivityDatabaseAdd.push().getKey();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Users");
        mUserDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                users = dataSnapshot.getValue(Users.class);

                String idUserChat = dataSnapshot.getKey();
                mUserActivityDatabase = FirebaseDatabase.getInstance().getReference().child("UserActivity").child(myValueIdActivity).child(idUserChat);
                //to do: check if it's a good path for add only members in the chat
                if (mUserActivityDatabase != null) {
                    //add all users excepts the current user
                    if (users.getName() != UserDetails.username) {
                        user_list_adapter.add(users);
                        setListViewHeight(listView);
                    }
                }

                tvEmptyUserCurrentList.setText("Chat with activity's members (" + user_list_adapter.getCount() + ")");


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
        listViewOnClickListener();


        getCurrentActivity();


        fab = (FloatingActionButton) findViewById(R.id.fabJoinActivity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // to ordenate data for db
                HashMap<String, String> userActivity = new HashMap<>();
                userActivity.put("idUser", currentUserId);
//                userActivity.put("idActivity",content);

                mUserActivityDatabaseAdd.child(mUserActivity_id).setValue(userActivity).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                                                  @Override
                                                  public void onComplete(@NonNull Task<Void> task) {

                                                      if (task.isSuccessful()) {

                                                          Toast.makeText(getApplicationContext(), "You are a member of this activity", Toast.LENGTH_LONG).show();

                                                      } else {

                                                          Toast.makeText(getApplicationContext(), "Errors in Database", Toast.LENGTH_LONG).show();

                                                      }
                                                  }
                                              }
                        );
            }
        });

        //test author & currentUser
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String uid = currentUser.getUid();
        //Mask the delete Activity Button
        String Act_owner = getIntent().getExtras().getString("owner");
        if(!Act_owner.equals(uid)){

            fabDelete.hide();
        }else{
            fabDelete.show();
            //to delete the activity
            fabDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(display_activity.this);
                    builder.setTitle(R.string.dilog_message_deleteActivity);
                    builder.setMessage(R.string.dialog_title_deleteActivity);

                    builder.setPositiveButton(getResources().getString(R.string.Continue), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            //Code to delete - intent - and finish

                            Intent toHome = new Intent(display_activity.this,home.class);
                            startActivity(toHome);
                            finish();
                        }
                    });
                    builder.setNegativeButton(getResources().getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    //create
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            });

        }

    }


    public void getCurrentActivity() {
        currentActivity = FirebaseDatabase.getInstance().getReference().child("Activities").child(myValueTopic).child(myValueIdActivity);
        currentActivity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String imgTop = dataSnapshot.child("Act_image").getValue().toString();
                String title = dataSnapshot.child("Act_title").getValue().toString();
                String content = dataSnapshot.child("Act_content").getValue().toString();
                String dateCrea = dataSnapshot.child("Act_date_crea").getValue().toString();
                String dateEnd = dataSnapshot.child("Act_date_end").getValue().toString();


                activity_Tile.setText(title);
                activity_content.setText(content);
                activityDate.setText(activityDate.getText() + " " + dateCrea);
                activityDateEnd.setText(activityDateEnd.getText() + " " + dateEnd);
                //load image
                if(!imgTop.equals("ic_action_clock")) {
                    Picasso.with(display_activity.this).load(imgTop).placeholder(R.drawable.ic_action_clock).into(imageTop);
                }
                //User creator
                setUserCreator(dataSnapshot.child("Act_owner").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }


    public void setUserCreator(String id) {
        mCurrentUserCreator = FirebaseDatabase.getInstance().getReference().child("Users").child(id);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
//                Users creator = dataSnapshot.getValue(Users.class);
//                UserDetails.username = post.getName();
                String creatorName = dataSnapshot.child("name").getValue().toString();
                tvCreator.setText(creatorName);

                String imgAuthor = dataSnapshot.child("image").getValue().toString();
                Picasso.with(display_activity.this).load(imgAuthor).into(imageCreator);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mCurrentUserCreator.addValueEventListener(postListener);
    }


    public void setUserDetails() {
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        currentUserId = mCurrentUser.getUid();
        currentUserDB = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Users post = dataSnapshot.getValue(Users.class);
                UserDetails.username = post.getName();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        currentUserDB.addValueEventListener(postListener);
    }


    public void listViewOnClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Users item = (Users) user_list_adapter.getItem(position);
                Intent i = new Intent(display_activity.this, chat.class);
                i.putExtra("myValueKeyChatWith", item.getName());
                i.putExtra("myValueKeyIdActivity", myValueIdActivity);
                i.putExtra("myValueKeyTopic", myValueTopic);
                UserDetails.chatWith = item.getName();
                display_activity.this.startActivity(i);
            }
        });
    }

    // set the list height automatically
    public static boolean setListViewHeight(ListView listView) {
        int position = 0;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {
            int numberOfItems = listAdapter.getCount();

            // height of items
            int totalItemsHeight = 0;
            for (position = 0; position < numberOfItems; position++) {
                View item = listAdapter.getView(position, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Set list height
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;
        } else {
            return false;
        }
    }
}

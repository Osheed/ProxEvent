package com.train.proxevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.widget.AdapterView;
import com.google.firebase.database.ValueEventListener;
import com.train.proxevent.Objects.Users;

public class display_activity extends AppCompatActivity {

    user_list_adapter user_list_adapter;
    private DatabaseReference mUserDatabase, currentUserDB;
    private FirebaseUser mCurrentUser;
    private Users users;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_activity);
        listView = (ListView) findViewById(R.id.usersList);
        user_list_adapter = new user_list_adapter(getApplicationContext(), R.id.userchat_list_layout);
        listView.setAdapter(user_list_adapter);

        setUserDetails();

        //Recover the data from db
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Users");
        mUserDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                users = dataSnapshot.getValue(Users.class);

                //add all users excepts the current user
                if(users.getName() != UserDetails.username){
                    user_list_adapter.add(users);
                    setListViewHeight(listView);
                }

//                pd = new ProgressDialog(display_activity.this);
//                pd.setMessage("Loading...");
//                pd.show();
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
    }


    public void setUserDetails(){
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = mCurrentUser.getUid();
        currentUserDB = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Users post = dataSnapshot.getValue(Users.class);
                UserDetails.username = post.getName();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {      }
        };
        currentUserDB.addValueEventListener(postListener);
    }


    public void listViewOnClickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Users item = (Users) user_list_adapter.getItem(position);
                Intent i = new Intent(display_activity.this, chat.class);
                i.putExtra("myValueKeyChatWith", item.getName());
                UserDetails.chatWith = item.getName();

                display_activity.this.startActivity(i);
            }
        });
    }

    // set the list height automatically
    public static boolean setListViewHeight(ListView listView) {
        int position =0;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {
            int numberOfItems = listAdapter.getCount();

            // height of items
            int totalItemsHeight = 0;
            for ( position = 0; position < numberOfItems;position++) {
                View item = listAdapter.getView(position, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Set list height
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight ;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;
        } else {
            return false;
        }
    }
}

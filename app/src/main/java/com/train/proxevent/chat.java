package com.train.proxevent;

import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class chat extends AppCompatActivity {

    LinearLayout layout;
    RelativeLayout layout_2;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1, reference2;

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);





        //test
        //Recover the data from db
//        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
//        String current_uid = mCurrentUser.getUid();
//        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);




        layout = (LinearLayout) findViewById(R.id.layout1);
        layout_2 = (RelativeLayout) findViewById(R.id.layout2);
        sendButton = (ImageView) findViewById(R.id.sendButton);
        messageArea = (EditText) findViewById(R.id.messageArea);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://fulltopia-f6db1.firebaseio/messages/" + "user1" + "_" + "test2");
        reference2 = new Firebase("https://fulltopia-f6db1.firebaseio/messages/" + "test2" + "_" +  "user1");



//        reference1 = new Firebase("https://proxevent-240cf.firebaseio.com/" + "UserDetails.username" + "_" + "UserDetails.chatWith");
//        reference2 = new Firebase("https://proxevent-240cf.firebaseio.com/" + "UserDetails.username" + "_" + "UserDetails.chatWith");

        sendButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String messageText = messageArea.getText().toString();

                if (!messageText.equals("")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("user", "user1");//UserDetails.username);
//                    map.put("message", messageText);
//                    map.put("user", ", messageText);

                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    messageArea.setText("");

                }
            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String userName = map.get("user").toString();

                if (userName.equals(UserDetails.username)) {
                    addMessageBox("You:-\n" + message, 1);
                } else {
                    addMessageBox(UserDetails.chatWith + ":-\n" + message, 2);
                }
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
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }





    public void addMessageBox(String message, int type){
    TextView textView = new TextView(chat.this);
    textView.setText(message);

    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    lp2.weight = 1.0f;

    if(type == 1) {
    lp2.gravity = Gravity.LEFT;
    textView.setBackgroundResource(R.drawable.chat_right);
    }
    else{
    lp2.gravity = Gravity.RIGHT;
    textView.setBackgroundResource(R.drawable.chat_left);
    }
    textView.setLayoutParams(lp2);
    layout.addView(textView);
    scrollView.fullScroll(android.view.View.FOCUS_DOWN);
    }
    }
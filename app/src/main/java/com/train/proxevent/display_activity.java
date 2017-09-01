package com.train.proxevent;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.train.proxevent.Objects.Activities;
import com.train.proxevent.Objects.User;
import com.train.proxevent.Objects.Users;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class display_activity extends AppCompatActivity {


    ListView usersList;
    ArrayList<String> al = new ArrayList<>();
    int totalUsers = 0;
    ProgressDialog pd;
    Button saveMessage;
    Button goChat;
    EditText etAddMessage;
    user_list_adapter user_list_adapter;
    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;
    private Users users;


    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_activity);

//        usersList = (ListView) findViewById(R.id.usersList);
//        goChat = (Button) findViewById(R.id.buttonChat);


        listView = (ListView) findViewById(R.id.usersList);
        user_list_adapter = new user_list_adapter(getApplicationContext(), R.id.userchat_list_layout);
        listView.setAdapter(user_list_adapter);


        //Recover the data from db
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
//        String current_uid = mCurrentUser.getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Users");




        mUserDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                users = dataSnapshot.getValue(Users.class);
                user_list_adapter.add(users);
                setListViewHeight(listView);
//                                                    listView.setAdapter(activity_list_adapter);
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


    public void listViewOnClickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Users item = (Users) user_list_adapter.getItem(position);

                Intent i = new Intent(display_activity.this, chat.class);
            /* put Extra in the intent for displaying question in QuestionDisplay*/
                i.putExtra("myValueKeyChatWith", item.getName());
                UserDetails.chatWith = item.getName();



                mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
                String current_uid = mCurrentUser.getUid();
                mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid).child("name");
//
//                mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("name");
                String test = mUserDatabase.toString();

                UserDetails.username = "xaviernendaz";


//    /**/            UserDetails.username
//                UserDetails.chatWith = al.get(position);
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

            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight ; //+ totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;
        } else {
            return false;
        }
    }






}











//        goChat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent chat = new Intent(display_activity.this, chat.class);
//                startActivity(chat);
//            }
//        });
//    }
//}














//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fulltopia-f6db1.firebaseio.com/Users");
//
//        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
//                this,
//                String.class,
//                android.R.layout.simple_list_item_1,
//                databaseReference
//        ) {
//
//
//            protected void populateView(View v, String model, int position) {
//
//                TextView textView = (TextView) v.findViewById(android.R.id.text1);
//                textView.setText(model);
//            }
//        };
//
//        usersList.setAdapter(firebaseListAdapter);
//
//    }}



//        pd = new ProgressDialog(display_activity.this);
//        pd.setMessage("Loading...");
//        pd.show();
//
//
//       //Recover the data from db
//        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
//        String current_uid = mCurrentUser.getUid();
//        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
////        dataSnapshot.child("name").getValue().toString();
//        String username = mUserDatabase.child("name").toString();
////        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
//
//        UserDetails.username = username;
//        String url =   "https://fulltopia-f6db1.firebaseio.com/Users";
////        String url = "https://androidchatapp-76776.firebaseio.com/users.json";
////        String url = "https://androidchatapp-76776.firebaseio.com/users.json";
//
//        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
//        public void onResponse(String s) {
//            doOnSuccess(s);
//        }
//
//
//        },new Response.ErrorListener(){
//        @Override
//        public void onErrorResponse(VolleyError volleyError) {
//        System.out.println("" + volleyError);
//        }
//        });
//
//        RequestQueue rQueue = Volley.newRequestQueue(display_activity.this);
//        rQueue.add(request);
//
//        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        UserDetails.chatWith = al.get(position);
//        startActivity(new Intent(display_activity.this, chat.class));
//        }
//        });


        /*
        etAddMessage = (EditText) findViewById(R.id.etAddMessage);
        //save a comment when user click on save
        saveMessage = (Button) findViewById(R.id.btSaveMsg);
        saveMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String comment = etAddMessage.getText().toString();
                if (TextUtils.isEmpty(comment)) {
                    Toast.makeText(getBaseContext(), R.string.enterChat, Toast.LENGTH_SHORT).show();

                }else{
                    SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy - HH:mm");
                    String currentTime = time.format(new Date());
                    String content = etAddMessage.getText().toString();
                    //String author = usernameSharedPref.toString();

                    // add comment in database

                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Chat");
                    myRef.setValue(content);

                    //dbHelper.addComment(content, currentTime, author, myValueKeyIdQuestion);
                    Toast.makeText(getBaseContext(), R.string.commentAdded, Toast.LENGTH_SHORT).show();


                    // Clear and set hint in EditText addComment
                    etAddMessage.getText().clear();
                    etAddMessage.setHint(R.string.addComment);


                    // Close the keyboard on save comment
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
        */




//
//
//
//    public void doOnSuccess(String s){
//       try {
//        JSONObject obj = new JSONObject(s);
//
//           Iterator i = obj.keys();
//           String key = "";
//
//            while(i.hasNext()){
//            key = i.next().toString();
//
//                if(!key.equals(UserDetails.username)) {
//                    al.add(key);
//                    }
//
//                totalUsers++;
//                }
//
//       } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        if(totalUsers <=1){
//        usersList.setVisibility(View.GONE);
//        }
//        else{
//        usersList.setVisibility(View.VISIBLE);
//        usersList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
//    }
//
//        pd.dismiss();
//    }
//}


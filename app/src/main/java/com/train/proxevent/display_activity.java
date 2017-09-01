package com.train.proxevent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_activity);

        usersList = (ListView) findViewById(R.id.usersList);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fulltopia-f6db1.firebaseio.com/Users");

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_list_item_1,
                databaseReference
        ) {


            protected void populateView(View v, String model, int position) {

                TextView textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText(model);
            }
        };

        usersList.setAdapter(firebaseListAdapter);

    }}



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



//        goChat = (Button) findViewById(R.id.buttonChat);
//        goChat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent chat = new Intent(display_activity.this, chat.class);
//                startActivity(chat);
//            }
//        });
//    }
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


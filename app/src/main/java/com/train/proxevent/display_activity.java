package com.train.proxevent;

import android.content.Context;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;

public class display_activity extends AppCompatActivity {


    Button saveMessage;
    EditText etAddMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_activity);


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


    }




}



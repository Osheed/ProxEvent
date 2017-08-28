package com.train.proxevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class settings extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(R.string.Settings);



    }
    private void sendToStart() {
        Intent loginIntent = new Intent(settings.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
    public void logOff(View view) {
        // initializes
        mAuth = FirebaseAuth.getInstance();
        FirebaseAuth.getInstance().signOut();
        sendToStart();
    }
}

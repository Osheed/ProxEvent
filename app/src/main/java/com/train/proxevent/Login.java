package com.train.proxevent;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.train.proxevent.Objects.FirebaseReferences;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //add firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //add reference to database
        DatabaseReference myRef = database.getReference(FirebaseReferences.PROXEVENT_REFERENCE);
        //change value of database/ProxEvent to 4
        myRef.setValue(4);
        //add values to database/ProxEvent (not duplicates!!)
        myRef.push().setValue(5);
        myRef.push().setValue(9);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int valor = dataSnapshot.getValue(Integer.class);
                Log.i("DATA:",valor + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ERROR", databaseError.getMessage());
            }
        };
       myRef.addValueEventListener(valueEventListener);
    }
}

package com.train.proxevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by David on 14.07.2017.
 */


public class home extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public FirebaseAuth Auth = FirebaseAuth.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();

       /**
        *
        *    SELECT * FROM user WHERE email = 'user.getEmail()';
        *
        *
        *    ==
        *
        *    new Firebase("https://examples-sql-queries.firebaseio.com/user")
    .startAt('user.getEmail()')
                .endAt('user.getEmail()')
                .once('value', show);

        function show(snap) {
                $('pre').text(JSON.stringify(snap.val(), null, 2));
        }

        **/

    }
}
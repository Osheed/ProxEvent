package com.train.proxevent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class settings extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference MUserDB;
    private FirebaseUser currentUser;
    private Button goAdminBtn;

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(R.string.Settings);


//        String admin = currentUser.getUid().toString();

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = mCurrentUser.getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
        mUserDatabase.getKey();

        String admin = "mJ4aJAWSSeeQ8mEcAtTySQlPJOU2";


        if (mUserDatabase.getKey().equalsIgnoreCase(admin)) {
            goAdminBtn = (Button) findViewById(R.id.goAdmin);
            goAdminBtn.setVisibility(View.VISIBLE);
        }


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

    public void deleteUser(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(settings.this);
        builder.setTitle(R.string.dilog_title_deleteUser);
        builder.setMessage(R.string.dialog_message_deleteUser);


        builder.setPositiveButton(getResources().getString(R.string.Continue), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                mAuth = FirebaseAuth.getInstance();
                currentUser = mAuth.getCurrentUser();
                String uid = currentUser.getUid();

                //Code to delete from db
                MUserDB = FirebaseDatabase.getInstance().getReference("Users").child(uid);
                MUserDB.removeValue();


                //Code to delete from auth
                currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Intent toLogin = new Intent(settings.this, LoginActivity.class);
                            // Intent to redirect the user at home not at the display_activity when they push back
                            toLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(toLogin);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.ContactAdmin), Toast.LENGTH_LONG).show();
                        }
                    }
                });

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

    public void goAdmin(View view) {
        //  Intent goToAdmin = new Intent(this, messageFromAdmin.class);
        Intent goToAdmin = new Intent(this, admin.class);
        startActivity(goToAdmin);


    }
}


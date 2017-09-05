package com.train.proxevent;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
                        if(task.isSuccessful()){

                            Intent toLogin = new Intent(settings.this,LoginActivity.class);
                            // Intent to redirect the user at home not at the display_activity when they push back
                            toLogin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(toLogin);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),getResources().getString(R.string.ContactAdmin),Toast.LENGTH_LONG).show();
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
    }


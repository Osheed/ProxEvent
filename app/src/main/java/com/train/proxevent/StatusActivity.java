package com.train.proxevent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusActivity extends AppCompatActivity {

    private TextInputLayout mStatus;
    private Button mSavebtn;
    private DatabaseReference mStatusDb;
    private FirebaseUser mCurrentUser;

    //Progress
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        mStatus = (TextInputLayout) findViewById(R.id.til_status_change);
        mSavebtn = (Button) findViewById(R.id.btn_status_change);

        //Firebase
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_id = mCurrentUser.getUid();
        mStatusDb = FirebaseDatabase.getInstance().getReference().child("Users").child(current_id);

        //step to retrieve the old status
        String status_value = getIntent().getStringExtra("status_value");
        mStatus.getEditText().setHint(status_value);

        mSavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Progress
                mProgress = new ProgressDialog(StatusActivity.this);
                mProgress.setTitle("Saving Changes");
                mProgress.setMessage("Please wait while we save the changes");
                mProgress.show();

                //Recover data and sent to the EditText to wiev old status
                String status = mStatus.getEditText().getText().toString();

                mStatusDb.child("status").setValue(status).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            mProgress.dismiss();
                            Intent profileIntent = new Intent(StatusActivity.this, profile.class);
                            startActivity(profileIntent);

                        } else {

                            Toast.makeText(getApplicationContext(), "There was some errors in saving Changes", Toast.LENGTH_LONG).show();

                        }
                    }
                });


            }
        });

    }
}

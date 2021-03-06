package com.train.proxevent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout til_Email;
    private TextInputLayout til_Password;
    private Button btn_LogIn;
    private TextView btn_Register;
    private ProgressDialog mLoginProgress;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log in");
        //Initializes
        til_Email = (TextInputLayout) findViewById(R.id.til_login_email);
        til_Password = (TextInputLayout) findViewById(R.id.til_login_password);
        btn_LogIn = (Button) findViewById(R.id.btn_login_login);
        btn_Register = (TextView) findViewById(R.id.btn_login_register);
        mLoginProgress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();


        //Registration
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent regIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regIntent);

            }
        });

        //Log-in
        btn_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = til_Email.getEditText().getText().toString();
                String password = til_Password.getEditText().getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {

                    mLoginProgress.setTitle("Logging In");
                    mLoginProgress.setMessage("Please wait while we check your credentials ");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();

                    loginUser(email, password);

                }
            }
        });

    }

    private void loginUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    mLoginProgress.dismiss();

                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    Log.i("currentUser", "you are admin + " + currentUser.getUid());
                    String uid = currentUser.getUid();
                    String admin = "mJ4aJAWSSeeQ8mEcAtTySQlPJOU2";

                    if (uid.equalsIgnoreCase(admin)) {
                        Log.i("aaa", "aaa admin + " + currentUser.getUid());

                        Intent goAdmin = new Intent(LoginActivity.this, admin.class);

                        startActivity(goAdmin);
                        finish();


                    } else {
                        Log.i("home", "home admin + " + currentUser.getUid());
                        Intent mainIntent = new Intent(LoginActivity.this, home.class);

                        //Clear previous tasks
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(mainIntent);
                        finish();
                    }
                } else {

                    mLoginProgress.hide();
                    Toast.makeText(LoginActivity.this, "Cannot Sign in. Please check the form and try again ", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}


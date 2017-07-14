package com.train.proxevent;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button buttonRegister,buttonSingIn;
    EditText editTextEmail, editTextPass;

    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);

        buttonRegister = (Button) findViewById(R.id.register_button);
        buttonSingIn = (Button) findViewById(R.id.singin_button);
        editTextEmail = (EditText) findViewById(R.id.login_email);
        editTextPass = (EditText) findViewById(R.id.login_password);

        buttonRegister.setOnClickListener(this);
        buttonSingIn.setOnClickListener(this);

        mAuthListener= new FirebaseAuth.AuthStateListener(){

            @Override
            //when we change sesion (start or change)
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {//return object firebaseauth

                FirebaseUser user = firebaseAuth.getCurrentUser();//==FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    //start activity...
                    Log.i("SESION", "sesion started with email: " + user.getEmail());
                    //singleton, has the registred user next screen!


                } else {
                    Log.i("SESION", "sesion clossed");
                }
            }
        };

    }
    private void register(String email, String pass) {
        //default method with email
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.i("SESION", "user created ok");

                }else{
                    Log.e("SESION",task.getException().getMessage()+"");
                }
            }
        });
    }

    private void startSesion(String email, String pass){
        //default method with email
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.i("SESION", "session started ok");

                    Intent intent = new Intent(getApplicationContext(), home.class);
                    startActivity(intent);
                }else{
                    Log.e("SESION",task.getException().getMessage()+"");
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.singin_button:
                String emailStart = editTextEmail.getText().toString();
                String passStart = editTextPass.getText().toString();
                startSesion(emailStart,passStart);
                break;
            case R.id.register_button:
                String emailReg = editTextEmail.getText().toString();
                String passReg = editTextPass.getText().toString();
                register(emailReg,passReg);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
           FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }
    public void pass(View view) {

        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }
}

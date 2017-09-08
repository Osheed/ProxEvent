package com.train.proxevent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout til_DisplayName;
    private TextInputLayout til_Email;
    private TextInputLayout til_Password;
    private Button btn_Register;
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;
    private ProgressDialog regProgress;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Registration");

        // Initializes
        til_DisplayName = (TextInputLayout) findViewById(R.id.til_reg_dName);
        til_Email = (TextInputLayout) findViewById(R.id.til_reg_email);
        til_Password = (TextInputLayout) findViewById(R.id.til_reg_password);
        btn_Register = (Button) findViewById(R.id.btn_reg_register);
        mAuth = FirebaseAuth.getInstance();
        regProgress = new ProgressDialog(this);


        // Retrieves the infos by clicking
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String displayName = til_DisplayName.getEditText().getText().toString();
                String email = til_Email.getEditText().getText().toString();
                String password = til_Password.getEditText().getText().toString();


                if (!TextUtils.isEmpty(displayName) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {

                    regProgress.setTitle("Registrering User");
                    regProgress.setMessage("Please wait while we create your account");
                    regProgress.setCanceledOnTouchOutside(false);
                    regProgress.show();

                    register_user(displayName, email, password);

                }


            }
        });


    }

    private void register_user(final String displayName, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {

                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = current_user.getUid();

                    //db to current User
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                    // to ordenate data for db
                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("name", displayName);
                    userMap.put("status", "Hi there I'm using ProxEvent !");
                    userMap.put("image", "default");
                    userMap.put("thumb_image", "default");

                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                regProgress.dismiss();

                                Intent registerIntent = new Intent(RegisterActivity.this, home.class);

                                // Intent to redirect the user at login_page not at the map_activity when they push back
                                registerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(registerIntent);
                                finish();

                            }

                        }
                    });


                } else {
                    //check the errors
                    String error = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        error = "Weak Password";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        error = "Invalid Email";
                    } catch (FirebaseAuthUserCollisionException e) {
                        error = "Existing Account";
                    } catch (Exception e) {
                        error = "Check your internet connection";
                        e.printStackTrace();
                    }
                    regProgress.hide();
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}

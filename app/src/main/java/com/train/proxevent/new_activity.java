package com.train.proxevent;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;

import static com.train.proxevent.R.array.topics_array_spinner;

public class new_activity extends AppCompatActivity {

    private TextView mDisplayDate;
    private Spinner mTopics;
    private EditText mEnterTitle;
    private EditText mEnterContent;
    private CheckBox mPermanent;
    private TextView mSelectLocation;
    private CheckBox mLocation;
    private Button mSave;
    private CircleImageView mImage;
    private static final int GALLERY_PICK = 1;
    private String topics;
    private String dateEnd;
    //Progress
    private ProgressDialog mProgress;
    //Storage Firebase
    private StorageReference mImageStorage;
    private FirebaseUser mCurrentUser;
    private String mActivity_id;
    private DatabaseReference mActivityDatabase;
    private int mYear, mMonth, mDay;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);
        setTitle(R.string.newActivity);
        final Calendar c = Calendar.getInstance();

        //Initializes
        mDisplayDate = (TextView) findViewById(R.id.tv_newActivity_SelectDate);
        mTopics = (Spinner)findViewById(R.id.sp_newActivity_topics);
        mEnterTitle = (EditText) findViewById(R.id.et_newActivity_EnterTitle);
        mEnterContent = (EditText) findViewById(R.id.et_newActivity_EnterContent);
        mPermanent = (CheckBox) findViewById(R.id.cb_newActivity_Permanent);
        mSelectLocation = (TextView) findViewById(R.id.tv_newActivity_SelectLocation);
        mLocation = (CheckBox) findViewById(R.id.cb_newActivity_LocationNoSpec);
        mSave = (Button) findViewById(R.id.btn_newActivity_Save);
        mImage = (CircleImageView) findViewById(R.id.civ_newActivity_ChooseImg);
        mImageStorage = FirebaseStorage.getInstance().getReference();




        //choose the Topic
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.topics_array_spinner));
        mTopics.setAdapter(adapter);
        mTopics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(new_activity.this, adapterView.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
               topics = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Database
        mActivityDatabase = FirebaseDatabase.getInstance().getReference().child("Activities");
        mActivity_id = mActivityDatabase.push().getKey();


        //Choose the Date
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = new DatePickerDialog(new_activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                c.set(year, month, day);
                                String date = new SimpleDateFormat("dd-MM-yyyy").format(c.getTime());
                                mDisplayDate.setText(date);
                                mYear = c.get(Calendar.YEAR);
                                mMonth = c.get(Calendar.MONTH);
                                mDay = c.get(Calendar.DAY_OF_MONTH);
                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                Calendar d = Calendar.getInstance();
                d.add(Calendar.MONTH,1);
                dpd.getDatePicker().setMaxDate(d.getTimeInMillis());
                dpd.show();
            }

        });
/*
        //Display the date
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                dateEnd = day + "-" + month + "-" + year;
                mDisplayDate.setText(dateEnd);
            }
        };
*/

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Progress
                mProgress = new ProgressDialog(new_activity.this);
                mProgress.setTitle("Saving Changes");
                mProgress.setMessage("Please wait while we save the changes");
                mProgress.show();

                //Recover data
                String content = mEnterContent.getText().toString().trim();
                Date todayDate = Calendar.getInstance().getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String dateCrea = formatter.format(todayDate);
                String dateEnd = mDisplayDate.getText().toString().trim();
                String latitude = "latitudeyyyy";
                String longitude = "longitudexxx";
                String adresse = "adresse-test";
                mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
                String current_id = mCurrentUser.getUid();
                String title = mEnterTitle.getText().toString().trim();


                // to ordenate data for db
                HashMap< String, String> activityMap = new HashMap<>();
                activityMap.put("Act_adresse", adresse);
                activityMap.put("Act_content",content);
                activityMap.put("Act_date_crea",dateCrea);
                activityMap.put("Act_date_end",dateEnd);
                activityMap.put("Act_latitude",latitude);
                activityMap.put("Act_longitude",longitude);
                activityMap.put("Act_owner",current_id);
                activityMap.put("Act_title",title);
                activityMap.put("Act_topic",topics);
                activityMap.put("Act_image", "test-image");



                //enregistrement pour topic all
                mActivityDatabase.child("All").child(mActivity_id).setValue(activityMap).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                                                  @Override
                                                  public void onComplete(@NonNull Task<Void> task) {

                                                      if(task.isSuccessful()){

                                                          mProgress.dismiss();

                                                      }else{

                                                          Toast.makeText(getApplicationContext(),"There was some errors in Creating this Activities",Toast.LENGTH_LONG).show();

                                                      }
                                                  }
                                              }
                        );

                //enregistrement par rapport au topic choisi
                mActivityDatabase.child(topics).child(mActivity_id).setValue(activityMap).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {

                                 if(task.isSuccessful()){

                                     mProgress.dismiss();
                                     Intent profileIntent = new Intent(new_activity.this, home.class);
                                     startActivity(profileIntent);

                                 }else{

                                     Toast.makeText(getApplicationContext(),"There was some errors in Creating this Activities",Toast.LENGTH_LONG).show();

                                 }
                             }
                        }
                );
            }









        });






    }
}

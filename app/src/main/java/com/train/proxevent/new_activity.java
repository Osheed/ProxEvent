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
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
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


public class new_activity extends AppCompatActivity {

    private TextView mDisplayDate;
    private Spinner mTopics;
    private EditText mEnterTitle;
    private EditText mEnterContent;
    private CheckBox mPermanent;
    private TextView mEnterLocation;
    private CheckBox mLocation;
    private Button mSave;
    private CircleImageView mImage;
    private static final int GALLERY_PICK = 1;
    private String topics;
    private String dateEnd;
    private String adresse;
    //Progress
    private ProgressDialog mProgress;
    //Storage Firebase
    private StorageReference mImageStorage;
    private FirebaseUser mCurrentUser;
    private String mActivity_id;
    private DatabaseReference mActivityDatabase;
    private DatabaseReference mActivityImgDb;
    private int mYear, mMonth, mDay;
    private String img_url;


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
        mEnterLocation = (EditText) findViewById(R.id.et_newActivity_EnterLocation);
        mLocation = (CheckBox) findViewById(R.id.cb_newActivity_LocationNoSpec);
        mSave = (Button) findViewById(R.id.btn_newActivity_Save);
        mImage = (CircleImageView) findViewById(R.id.civ_newActivity_ChooseImg);
        mImageStorage = FirebaseStorage.getInstance().getReference();
        mPermanent = (CheckBox)findViewById(R.id.cb_newActivity_Date);



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

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Recover image in the phone
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE"), GALLERY_PICK);

            }
        });

        //Test for Date
        mPermanent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if((compoundButton).isChecked()){
                    dateEnd = "31-12-9999";
                    mDisplayDate.setHint("No Specific");
                }else {
                    mDisplayDate.setHint("Date...");
                    dateEnd = mDisplayDate.getText().toString().trim();
                }
            }
        });
        //test for Location
        mLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if((compoundButton.isChecked())){
                    adresse = "No Specific";
                    mEnterLocation.setHint("No Specific");
                }else {
                    mEnterLocation.setHint("Location..");
                    adresse = mEnterLocation.getText().toString().trim();
                }
            }
        });


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
                String latitude = "latitudeyyyy";
                String longitude = "longitudexxx";

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
                activityMap.put("Act_image", img_url);



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
    //Recover the Crop Image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK) {

            Uri imageUri = data.getData();

            // start cropping activity for pre-acquired image saved on the device
            CropImage.activity(imageUri)
                    .setAspectRatio(1, 1)
                    .setMinCropWindowSize(500,500)
                    .start(this);


        }
        // Checks and retrieves the uri of the image
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {

                //Progress Bar for the User
                mProgress = new ProgressDialog(new_activity.this);
                mProgress.setTitle("Uploading Image...");
                mProgress.setMessage("Please wait while we upload and process the image");
                mProgress.setCanceledOnTouchOutside(false);
                mProgress.show();


                Uri resultUri = result.getUri();

                String current_activity_id = mActivity_id;

                //Compress-Convert
                final File thumb_filePath = new File(resultUri.getPath());
                final Bitmap thumb_bitmap = new Compressor(this)
                        .setMaxHeight(200)
                        .setMaxHeight(200)
                        .setQuality(75)
                        .compressToBitmap(thumb_filePath);

                //Upload
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thumb_bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                final byte [] thumb_byte = baos.toByteArray();

                StorageReference filepath = mImageStorage.child("activities_images").child(current_activity_id +".jpg");
                final StorageReference thumb_filepath = mImageStorage.child("profile_images").child("thumbs").child(current_activity_id +".jpg");


                //Upload Main image
                filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {

                    @SuppressWarnings("VisibleForTests")
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if (task.isSuccessful()) {

                            //get the url for activity db
                            final String download_url = task.getResult().getDownloadUrl().toString();
                            img_url = download_url;
                            //Task for thumb image
                            UploadTask uploadTask = thumb_filepath.putBytes(thumb_byte);
                            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> thumb_task) {

                                    String thumb_downloadUrl = thumb_task.getResult().getDownloadUrl().toString();

                                    if (thumb_task.isSuccessful()) {

                                        Map update_hashMap = new HashMap();
                                        update_hashMap.put("image", download_url);
                                        update_hashMap.put("thumb_image", thumb_downloadUrl);


                                        mActivityDatabase.updateChildren(update_hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()) {

                                                    mProgress.dismiss();
                                                    Toast.makeText(new_activity.this, "Success Uploading", Toast.LENGTH_LONG).show();
                                                    //Picasso.with(new_activity.this).load(img_url).placeholder(R.drawable.button_add_icon).into(mImage);
                                                    Picasso.with(new_activity.this).load(img_url).into(mImage);

                                                }
                                            }
                                        });

                                    } else {

                                        Toast.makeText(new_activity.this, "Error in uploading Thumbnail", Toast.LENGTH_LONG).show();
                                        mProgress.dismiss();
                                    }

                                }
                            });
                        } else {
                            Toast.makeText(new_activity.this, "Error in uploading", Toast.LENGTH_LONG).show();
                            mProgress.dismiss();
                        }
                    }
                });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();

            }
        }
    }
}

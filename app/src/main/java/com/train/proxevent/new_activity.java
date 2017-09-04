package com.train.proxevent;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;


public class new_activity extends AppCompatActivity {

    private TextView mDisplayDate;
    private TextView mDisplayNDate;
    private Spinner mTopics;
    private EditText mEnterTitle;
    private EditText mEnterContent;
    private CheckBox mPermanent;
    private CheckBox mLocation;
    private CheckBox mNDate;
    private TextView mEnterLocation;
    private Button mSave;
    private CircleImageView mImage;
    private static final int GALLERY_PICK = 1;
    private String topics;
    private String dateEnd;
    private String dateBegining;
    private String adresse;
    //Progress
    private ProgressDialog mProgress;
    //Storage Firebase
    private StorageReference mImageStorage;
    private FirebaseUser mCurrentUser;
    private String mActivity_id;
    private DatabaseReference mActivityDatabase;
    private DatabaseReference mActivityImgDb;
    private int mYear, mMonth, mDay, mNYear,mNMonth,mNDay;
    private String img_url;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);
        setTitle(R.string.newActivity);
        final Calendar c = Calendar.getInstance();

        //Initializes
        mDisplayDate = (TextView) findViewById(R.id.tv_newActivity_SelectDate);
        mDisplayNDate = (TextView) findViewById(R.id.tv_newActivity_SelectNDate);
        mTopics = (Spinner) findViewById(R.id.sp_newActivity_topics);
        mEnterTitle = (EditText) findViewById(R.id.et_newActivity_EnterTitle);
        mEnterContent = (EditText) findViewById(R.id.et_newActivity_EnterContent);
        mEnterLocation = (EditText) findViewById(R.id.et_newActivity_EnterLocation);
        mSave = (Button) findViewById(R.id.btn_newActivity_Save);
        mImage = (CircleImageView) findViewById(R.id.civ_newActivity_ChooseImg);
        mImageStorage = FirebaseStorage.getInstance().getReference();
        mNDate = (CheckBox) findViewById(R.id.cb_newActivity_NDate);
        mPermanent = (CheckBox) findViewById(R.id.cb_newActivity_Date);
        mLocation = (CheckBox) findViewById(R.id.cb_newActivity_LocationNoSpec);


        //choose the Topic
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.topics_array_spinner));
        mTopics.setAdapter(adapter);
        mTopics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(new_activity.this, adapterView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                topics = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Database
        mActivityDatabase = FirebaseDatabase.getInstance().getReference().child("Activities");
        mActivity_id = mActivityDatabase.push().getKey();


        //Choose the Begining Date
        mDisplayNDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpn = new DatePickerDialog(new_activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int Nyear, int Nmonth, int Nday) {
                                c.set(Nyear, Nmonth, Nday);
                                String dateN = new SimpleDateFormat("dd-MM-yyyy").format(c.getTime());
                                mDisplayNDate.setText(dateN);
                                mNYear = c.get(Calendar.YEAR);
                                mNMonth = c.get(Calendar.MONTH);
                                mNDay = c.get(Calendar.DAY_OF_MONTH);
                            }
                        }, mNYear, mNMonth, mNDay);
                dpn.getDatePicker().setMinDate(System.currentTimeMillis());
                Calendar d = Calendar.getInstance();
                d.add(Calendar.MONTH, 1);
                dpn.getDatePicker().setMaxDate(d.getTimeInMillis());
                dpn.show();

            }
        });

        //Choose the End Date
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
                d.add(Calendar.MONTH, 1);
                dpd.getDatePicker().setMaxDate(d.getTimeInMillis());
                dpd.show();

            }

        });
        //Recover image in the phone
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE"), GALLERY_PICK);

            }
        });

        //Test for Begining Date
        mNDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if ((compoundButton).isChecked()) {
                    dateBegining = "No Specific";
                    mDisplayNDate.setClickable(false);
                    mDisplayNDate.setText("");
                    mDisplayNDate.setHint("No Specific");
                } else {
                    mDisplayNDate.setClickable(true);
                    mDisplayNDate.setHint("Date...");
                }
            }
        });
        //Test for End Date
        mPermanent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if ((compoundButton).isChecked()) {
                    dateEnd = "No Specific";
                    mDisplayDate.setClickable(false);
                    mDisplayDate.setText("");
                    mDisplayDate.setHint("No Specific");
                } else {
                    mDisplayDate.setClickable(true);
                    mDisplayDate.setHint("Date...");

                }
            }
        });
        //test for Location
        mLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if ((compoundButton.isChecked())) {
                    adresse = "No Specific";
                    mEnterLocation.setEnabled(false);
                    mEnterLocation.setText("");
                    mEnterLocation.setHint("No Specific");
                } else {
                    mEnterLocation.setEnabled(true);
                    mEnterLocation.setClickable(true);
                    mEnterLocation.setHint("Location..");

                }
            }
        });

        //saving data
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
                String latitude = "latitudeyyyy";
                String longitude = "longitudexxx";
                mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
                String current_id = mCurrentUser.getUid();
                String title = mEnterTitle.getText().toString().trim();
                String vide = "";
                if(mEnterLocation.getText().toString().trim().equals(vide)){
                        mEnterLocation.setText("No Specific");
                }else{
                        adresse = mEnterLocation.getText().toString().trim();
                }

                if(mDisplayDate.getText().toString().trim().equals(vide)){
                    mDisplayDate.setText("No Specific");
                }else{
                    dateEnd = mDisplayDate.getText().toString().trim();
                }

                if (mDisplayNDate.getText().toString().trim().equals(vide)){
                    mDisplayNDate.setText("No Specific");
                }else{
                    dateBegining = mDisplayNDate.getText().toString().trim();
                }

                // to ordenate data for db
                HashMap<String, String> activityMap = new HashMap<>();
                activityMap.put("Act_adresse", adresse);
                activityMap.put("Act_content", content);
                activityMap.put("Act_date_crea", dateBegining);
                activityMap.put("Act_date_end", dateEnd);
                activityMap.put("Act_latitude", latitude);
                activityMap.put("Act_longitude", longitude);
                activityMap.put("Act_owner", current_id);
                activityMap.put("Act_title", title);
                activityMap.put("Act_topic", topics);
                activityMap.put("Act_image", img_url);

                //saving for topic all
                mActivityDatabase.child("All").child(mActivity_id).setValue(activityMap).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                                                  @Override
                                                  public void onComplete(@NonNull Task<Void> task) {

                                                      if (task.isSuccessful()) {
                                                          mProgress.dismiss();
                                                      } else {
                                                          Toast.makeText(getApplicationContext(), "There was some errors in Creating this Activities", Toast.LENGTH_LONG).show();
                                                      }
                                                  }
                                              }
                        );

                //Saving for choosen topic
                mActivityDatabase.child(topics).child(mActivity_id).setValue(activityMap).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                                                  @Override
                                                  public void onComplete(@NonNull Task<Void> task) {

                                                      if (task.isSuccessful()) {
                                                          mProgress.dismiss();
                                                          Intent profileIntent = new Intent(new_activity.this, home.class);
                                                          startActivity(profileIntent);
                                                      } else {
                                                          Toast.makeText(getApplicationContext(), "There was some errors in Creating this Activities", Toast.LENGTH_LONG).show();
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
                    .setMinCropWindowSize(500, 500)
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
                thumb_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                final byte[] thumb_byte = baos.toByteArray();

                StorageReference filepath = mImageStorage.child("activities_images").child(current_activity_id + ".jpg");
                final StorageReference thumb_filepath = mImageStorage.child("profile_images").child("thumbs").child(current_activity_id + ".jpg");


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

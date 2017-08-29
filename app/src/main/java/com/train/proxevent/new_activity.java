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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;

import static com.train.proxevent.R.array.topics_array_spinner;

public class new_activity extends AppCompatActivity {

    private TextView mDisplayDate;
    private Spinner mTopics;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText mEnterTitle;
    private EditText mEnterContent;
    private CheckBox mPermanent;
    private TextView mSelectLocation;
    private CheckBox mLocation;
    private Button mSave;
    private CircleImageView mImage;
    private static final int GALLERY_PICK = 1;
    private ProgressDialog mProgressDialog;
    //Storage Firebase
    private StorageReference mImageStorage;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mActivityDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);
        setTitle(R.string.newActivity);

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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        //Choose the Date
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(
                        new_activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        day, month, year);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                month = month + 1;

                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });






    }
}

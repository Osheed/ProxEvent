package com.train.proxevent;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class activity_list extends AppCompatActivity {

    FirebaseAuth.AuthStateListener mAuthListener;
    ListView listView;
    Cursor cursor;
    activity_list_adapter activity_list_adapter;
    String myValueTopicSelected;
//    LanguageLocalHelper languageLocalHelper;
    String currentLanguage;
    Context context = this;
    AlertDialog.Builder builder;
    byte [] imgtest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


          /* Recover Object Question from activity_question_list */
        myValueTopicSelected = getIntent().getExtras().getString("topicSelected");


        listView = (ListView) findViewById(R.id.listview_activityList);
        activity_list_adapter = new activity_list_adapter(getApplicationContext(), R.id.activity_list_layout);
        listView.setAdapter(activity_list_adapter);


//        currentLanguage = languageLocalHelper.getLanguage(QuestionList.this).toString();


        // if the user is in all questions
        if(myValueTopicSelected.equalsIgnoreCase("All") ||
                myValueTopicSelected.equalsIgnoreCase("Tout")){
            //display all questions
//            setAllQuestions();

        }else{
            //if the user select a topic
//            displayQuestionsFromTopicSortNew();
        }

        /* Set title and count nb questions */
        setTitle(myValueTopicSelected + " ("+ activity_list_adapter.getCount()+")");



    }
}

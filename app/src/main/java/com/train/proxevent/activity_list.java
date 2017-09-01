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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.train.proxevent.Objects.Activity;

import java.util.EventListener;

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

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;
    private Activity activity;

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



        //Recover the data from db
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
//        String current_uid = mCurrentUser.getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Activities");

        mUserDatabase.addChildEventListener(new ChildEventListener() {
                                                @Override
                                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                                    activity = dataSnapshot.getValue(Activity.class);
                                                    activity_list_adapter.add(activity);
//                                                    listView.setAdapter(activity_list_adapter);
                                                }

                                                @Override
                                                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                                }

                                                @Override
                                                public void onChildRemoved(DataSnapshot dataSnapshot) {

                                                }

                                                @Override
                                                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
        });
                                            }


//        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Activities").child().child("topic").equalTo(myValueTopicSelected);


//        usersRef.orderByChild(‘email’).equalTo(‘user-i-need-to-find@gmail.com’).once(‘value’).then(…)






    /* menu */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_list, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_add_activity:
                Intent go = new Intent(this, new_activity.class);
                startActivity(go);
                return true;


            /***** CLOUD *****/

            case R.id.   menu_sync:

//                if(isNetworkAvailable() == true){
//                    getQuestionsBackend();
//                    Toast.makeText(getBaseContext(), R.string.loading, Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getBaseContext(), R.string.notConnected, Toast.LENGTH_SHORT).show();
//                }

                return true;

            /***** CLOUD *****/


            case R.id.menu_sortTimeNew:
//                questionListDataAdapter.clear();
//                setAllQuestions();
                return true;

            case R.id.menu_sortTimeOld:
//                questionListDataAdapter.clear();
//
//                // if the user is in all questions
//
//
//                            Question c = new Question(id, topic, title, content, username, image, nbLike, date);
//                            questionListDataAdapter.add(c);
//
//                        } while (cursor.moveToNext());
//                    }
//                    listViewOnClickListenerAllQuestions();
//
//                }else{
//                    //if the user has selected a topic
//                    cursor = dbHelper.getAllQuestionsFromTopic(myValueTopicSelected);
//                    //order by last with inverse cursor
//                    if (cursor.moveToFirst()) {
//                        do {
//                            int id;
//                            String topic, title, content, username, nbLike, date;
//                            byte [] image;
//                            id = cursor.getInt(0);
//                            topic = cursor.getString(1);
//                            title = cursor.getString(2);
//                            content = cursor.getString(3);
//                            username = cursor.getString(4);
//                            image = cursor.getBlob(5);
//                            date = cursor.getString(6);
//                            nbLike = String.valueOf(dbHelper.countPositiveVote(id));
//
//                            Question c = new Question(id, topic, title, content, username, image, nbLike, date);
//                            questionListDataAdapter.add(c);
//
//                        } while (cursor.moveToNext());
//                    }
//                    listViewOnClickListener();
//                }
//
//                listViewOnLongClickListener();

                return true;


            case R.id.menu_sortASC:
//                questionListDataAdapter.clear();
//
//                // if the user is in all questions
//                if (myValueTopicSelected.equalsIgnoreCase("All")) {
//                    // if the app is in English, we want only the english questions
//                    cursor = dbHelper.getAllQuestionsFromTopicSortASCEN();
//                    if (cursor.moveToFirst()) {
//                        do {
//                            int id;
//                            String topic, title, content, username, nbLike, date;
//                            byte[] image;
//                            id = cursor.getInt(0);
//                            topic = cursor.getString(1);
//                            title = cursor.getString(2);
//                            content = cursor.getString(3);
//                            username = cursor.getString(4);
//                            image = cursor.getBlob(5);
//                            date = cursor.getString(6);
//
//                            nbLike = String.valueOf(dbHelper.countPositiveVote(id));
//
//                            Question c = new Question(id, topic, title, content, username, image, nbLike, date);
//                            questionListDataAdapter.add(c);
//
//                        } while (cursor.moveToNext());
//                    }
//                    listViewOnClickListenerAllQuestions();
//
//                } else if (myValueTopicSelected.equalsIgnoreCase("Tout")) {
//                    // if the app is in french
//                    cursor = dbHelper.getAllQuestionsFromTopicSortASCFR();
//                    if (cursor.moveToFirst()) {
//                        do {
//                            int id;
//                            String topic, title, content, username, nbLike, date;
//                            byte[] image;
//                            id = cursor.getInt(0);
//                            topic = cursor.getString(1);
//                            title = cursor.getString(2);
//                            content = cursor.getString(3);
//                            username = cursor.getString(4);
//                            image = cursor.getBlob(5);
//                            date = cursor.getString(6);
//
//                            nbLike = String.valueOf(dbHelper.countPositiveVote(id));
//
//                            Question c = new Question(id, topic, title, content, username, image, nbLike, date);
//                            questionListDataAdapter.add(c);
//
//                        } while (cursor.moveToNext());
//                    }
//                    listViewOnClickListenerAllQuestions();
//
//                }else {
//
//                    // if the User has selected a topis
//                    cursor = dbHelper.getAllQuestionsFromTopicSortASC(myValueTopicSelected);
//                    if (cursor.moveToFirst()) {
//                        do {
//                            int id;
//                            String topic, title, content, username, nbLike, date;
//                            byte[] image;
//                            id = cursor.getInt(0);
//                            topic = cursor.getString(1);
//                            title = cursor.getString(2);
//                            content = cursor.getString(3);
//                            username = cursor.getString(4);
//                            image = cursor.getBlob(5);
//                            date = cursor.getString(6);
//                            nbLike = String.valueOf(dbHelper.countPositiveVote(id));
//
//                            Question c = new Question(id, topic, title, content, username, image, nbLike, date);
//                            questionListDataAdapter.add(c);
//
//                        } while (cursor.moveToNext());
//                    }
//                    listViewOnClickListener();
//                }
//
//                listViewOnLongClickListener();
                return true;

            case R.id.menu_sortDESC:
//                questionListDataAdapter.clear();
//
//                // if the user is in all questions
//                if (myValueTopicSelected.equalsIgnoreCase("All")) {
//                    // if the app is in English, we want only the english questions
//                    cursor = dbHelper.getAllQuestionsFromTopicSortDESCEN();
//                    if (cursor.moveToFirst()) {
//                        do {
//                            int id;
//                            String topic, title, content, username, nbLike, date;
//                            byte[] image;
//                            id = cursor.getInt(0);
//                            topic = cursor.getString(1);
//                            title = cursor.getString(2);
//                            content = cursor.getString(3);
//                            username = cursor.getString(4);
//                            image = cursor.getBlob(5);
//                            date = cursor.getString(6);
//
//                            nbLike = String.valueOf(dbHelper.countPositiveVote(id));
//
//                            Question c = new Question(id, topic, title, content, username, image, nbLike, date);
//                            questionListDataAdapter.add(c);
//
//                        } while (cursor.moveToNext());
//                    }
//                    listViewOnClickListenerAllQuestions();
//
//                } else if (myValueTopicSelected.equalsIgnoreCase("Tout")) {
//                    // if the app is in french
//                    cursor = dbHelper.getAllQuestionsFromTopicSortDESCFR();
//                    if (cursor.moveToFirst()) {
//                        do {
//                            int id;
//                            String topic, title, content, username, nbLike, date;
//                            byte[] image;
//                            id = cursor.getInt(0);
//                            topic = cursor.getString(1);
//                            title = cursor.getString(2);
//                            content = cursor.getString(3);
//                            username = cursor.getString(4);
//                            image = cursor.getBlob(5);
//                            date = cursor.getString(6);
//
//                            nbLike = String.valueOf(dbHelper.countPositiveVote(id));
//
//                            Question c = new Question(id, topic, title, content, username, image, nbLike, date);
//                            questionListDataAdapter.add(c);
//
//                        } while (cursor.moveToNext());
//                    }
//                    listViewOnClickListenerAllQuestions();
//
//                }else {
//
//                    // if the User has selected a topis
//                    cursor = dbHelper.getAllQuestionsFromTopicSortDESC(myValueTopicSelected);
//                    if (cursor.moveToFirst()) {
//                        do {
//                            int id;
//                            String topic, title, content, username, nbLike, date;
//                            byte[] image;
//                            id = cursor.getInt(0);
//                            topic = cursor.getString(1);
//                            title = cursor.getString(2);
//                            content = cursor.getString(3);
//                            username = cursor.getString(4);
//                            image = cursor.getBlob(5);
//                            date = cursor.getString(6);
//                            nbLike = String.valueOf(dbHelper.countPositiveVote(id));
//
//                            Question c = new Question(id, topic, title, content, username, image, nbLike, date);
//                            questionListDataAdapter.add(c);
//
//                        } while (cursor.moveToNext());
//                    }
//                    listViewOnClickListener();
//                }
//
//                listViewOnLongClickListener();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}




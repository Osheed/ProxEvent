package com.train.proxevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class topic_list extends AppCompatActivity {

    ListView list;
    String [] topics;
    topic_list_custom adapter;

    Integer[] imgTopicsId = {
            R.drawable.topic_all,
            R.drawable.topic_fruit,
            R.drawable.topic_vegetable,
            R.drawable.topic_protein,
            R.drawable.topic_dairy,
            R.drawable.topic_bred,
            R.drawable.topic_vegan,
            R.drawable.topic_fastfood,
            R.drawable.topic_drink,
            R.drawable.topic_dessert,
            R.drawable.topic_candies,
            R.drawable.topic_general,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list_layout);
        setTitle(R.string.topic);

        topics = getResources().getStringArray(R.array.topics_array);
        adapter = new topic_list_custom(topic_list.this, topics, imgTopicsId);
        list = (ListView)findViewById(R.id.listviewTopics);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Toast.makeText(TopicsList.this, "You Clicked at " +topics[+ position], Toast.LENGTH_SHORT).show();
                Intent i = new Intent(topic_list.this, activity_list.class);
//                i.putExtra("topicSelected", adapter.getItem(position));
                topic_list.this.startActivity(i);
            }
        });


    }
}

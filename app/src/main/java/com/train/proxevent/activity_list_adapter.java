package com.train.proxevent;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xavne on 14/07/2017.
 */

public class activity_list_adapter extends ArrayAdapter {

    List list = new ArrayList();
    LayoutHandler layoutHandler;
    ByteArrayInputStream imageStream;

    public activity_list_adapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler
    {
        ImageView ACTIVITY_IMAGE;
        TextView ACTIVITY_TITLE;
        TextView ACTIVITY_DATE;
        TextView ACTIVITY_LIKE;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;


        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.activity_list_layout, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.ACTIVITY_IMAGE = (ImageView)row.findViewById(R.id.imvQuestionList );
            layoutHandler.ACTIVITY_TITLE = (TextView)row.findViewById(R.id.question_title );
            layoutHandler.ACTIVITY_LIKE = (TextView)row.findViewById(R.id.nbMember );
            layoutHandler.ACTIVITY_DATE = (TextView)row.findViewById(R.id.activityDate );
            row.setTag(layoutHandler);
        }
        else{

            layoutHandler = (activity_list_adapter.LayoutHandler)row.getTag();

        }

        com.train.proxevent.Objects.Activity cActivity = (com.train.proxevent.Objects.Activity)this.getItem(position);

        // display image in list
//        byte[] img = cActivity.getImage();
//        ImageView imageView = (ImageView) row.findViewById(R.id.imvQuestionList );
//        imageStream = new ByteArrayInputStream(img);
//        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
//        imageView.setImageBitmap(theImage);


//        layoutHandler.ACTIVITY_IMAGE.setImageBitmap(theImage);
        layoutHandler.ACTIVITY_TITLE.setText(cActivity.getTitle());
//        layoutHandler.ACTIVITY_LIKE.setText(cActivity.getNbMember());
        layoutHandler.ACTIVITY_DATE.setText(""+cActivity.getDateCreation());


        return row;
    }
}

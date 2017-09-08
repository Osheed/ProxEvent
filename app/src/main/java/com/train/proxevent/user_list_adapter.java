package com.train.proxevent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.train.proxevent.Objects.Users;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;


public class user_list_adapter extends ArrayAdapter {

    List list = new ArrayList();
    LayoutHandler layoutHandler;
    ByteArrayInputStream imageStream;

    public user_list_adapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler {
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


        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.userchat_list_layout, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.ACTIVITY_IMAGE = (ImageView) row.findViewById(R.id.imvQuestionList);
            layoutHandler.ACTIVITY_TITLE = (TextView) row.findViewById(R.id.question_title);
            layoutHandler.ACTIVITY_LIKE = (TextView) row.findViewById(R.id.nbMember);
            layoutHandler.ACTIVITY_DATE = (TextView) row.findViewById(R.id.activityDate);
            row.setTag(layoutHandler);
        } else {

            layoutHandler = (user_list_adapter.LayoutHandler) row.getTag();

        }

        Users cbUser = (Users) this.getItem(position);

        // display image in list
//        byte[] img = cbUser.getImage();
//        ImageView imageView = (ImageView) row.findViewById(R.id.imvQuestionList );
//        imageStream = new ByteArrayInputStream(img);
//        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
//        imageView.setImageBitmap(theImage);
//
//        //load image
//        Picasso.with(profile.this).load(image).into(layoutHandler.ACTIVITY_IMAGE);
////       .setIma

//        layoutHandler.ACTIVITY_IMAGE.setImageBitmap(theImage);
        layoutHandler.ACTIVITY_TITLE.setText(cbUser.getName());
        layoutHandler.ACTIVITY_LIKE.setText(cbUser.getStatus());
        layoutHandler.ACTIVITY_DATE.setText("" + cbUser.getStatus());


        return row;
    }
}

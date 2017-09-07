package com.train.proxevent;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    private View mMainView;
    private GoogleMap mymap;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMainView = inflater.inflate(R.layout.fragment_map,container,false);


        // Map
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        //   mapFragment.getMapAsync(getContext());



        // Inflate the layout for this fragment
        return mMainView;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mymap = map;
        mymap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mymap.getUiSettings().setZoomControlsEnabled(true);
        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(46.2443, 7.3250), 10);

        mymap.moveCamera(camUpd1);
    }
}
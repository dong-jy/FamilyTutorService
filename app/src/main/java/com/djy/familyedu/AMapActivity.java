package com.djy.familyedu;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

public class AMapActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amap);

        MapView mapView = (MapView) findViewById(R.id.amap_view);
        mapView.onCreate(savedInstanceState);
        AMap aMap = mapView.getMap();
    }
}

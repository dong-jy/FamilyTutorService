package com.djy.familyedu;

import android.Manifest;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MapActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener
        ,AMapLocationListener,LocationSource {

    protected static final String TAG = "MapActivity";

    private MapView mapView;
    private AMap map;
    private AMapLocationClient mapLocationClient;
    private LocationSource.OnLocationChangedListener locationChangedListener;
    private AMapLocationClientOption mapLocationClientOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapView = (MapView) findViewById(R.id.map_view);

//        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        mapView.onCreate(savedInstanceState);

        initMapView();
        initUI();
        requestPermissions();
        setMyLocationStyle();
        startClientLocation();
    }

    private void initMapView() {
        if (map == null) {
            map = mapView.getMap();
            LatLng haiKou = new LatLng(19.9816509, 110.3330756);
            map.addMarker(new MarkerOptions().position(haiKou).title("Marker in hmc"));
            map.moveCamera(CameraUpdateFactory.newLatLng(haiKou));
            map.setMapType(AMap.MAP_TYPE_NORMAL);
        }
    }

    private void initUI() {
//        findViewById(R.id.)
    }

    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.VIBRATE,
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.WAKE_LOCK,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CHANGE_WIFI_STATE,
                            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.SYSTEM_ALERT_WINDOW,
                    }
                    , 1);
        }
    }

    public void setMyLocationStyle() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.interval(1000);
//        myLocationStyle.showMyLocation(true);
//        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(
//                R.drawable.locate_location_button));
//        myLocationStyle.anchor(0, 1);
//        myLocationStyle.strokeColor(Color.MAGENTA);
//        myLocationStyle.radiusFillColor(Color.CYAN);
//        myLocationStyle.strokeWidth(5);
        map.setMyLocationStyle(myLocationStyle.myLocationType(
                MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER));
        map.setMyLocationEnabled(true);
        map.setMyLocationStyle(myLocationStyle);
    }

    private void startClientLocation() {
        map.setLocationSource(this);
        map.getUiSettings().setMyLocationButtonEnabled(true);

        map.setTrafficEnabled(true);

        map.setMyLocationEnabled(true);
        mapLocationClient = new AMapLocationClient(getApplicationContext());
        mapLocationClient.setLocationListener(this);

        mapLocationClientOption = new AMapLocationClientOption();
        mapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mapLocationClientOption.setNeedAddress(true);
        mapLocationClientOption.setOnceLocation(false);
        mapLocationClientOption.setWifiScan(true);
        mapLocationClientOption.setMockEnable(true);
        mapLocationClientOption.setInterval(1000);
        mapLocationClient.startLocation();

    }


    public void onLocationChanged(AMapLocation mapLocation) {
        if (locationChangedListener != null && mapLocation != null) {
            locationChangedListener.onLocationChanged(mapLocation);
            if (mapLocation.getErrorCode() == 0) {
                mapLocation.getLocationType();
                LatLng currentPosition = new LatLng(mapLocation.getLatitude(), mapLocation.getLongitude());//获取纬度;

                mapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(mapLocation.getTime());
                df.format(date);//定位时间
//                map.addMarker(new MarkerOptions().position(currentPosition).title("Lastest position"));
                map.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
//                mapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果
//                mapLocation.getCountry();//国家信息
//                mapLocation.getProvince();//省信息
//                mapLocation.getCity();//城市信息
//                mapLocation.getDistrict();//城区信息
//                mapLocation.getRoad();//街道信息
//                mapLocation.getCityCode();//城市编码
//                mapLocation.getAdCode();//地区编码
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e(TAG, "location Error, ErrCode:"
                        + mapLocation.getErrorCode() + ", errInfo:"
                        + mapLocation.getErrorInfo());
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (mapLocationClient != null) {
            mapLocationClient.onDestroy();
            mapLocationClient = null;
            mapLocationClientOption = null;
        }
    }


    public AMapLocationClient mlocationClient;
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {

        }
    };


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        locationChangedListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {

    }
}


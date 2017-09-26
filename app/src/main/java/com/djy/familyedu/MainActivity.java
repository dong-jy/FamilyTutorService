package com.djy.familyedu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
import android.view.View;
import android.widget.Button;

//import com.amap.api.location.AMapLocation;
//import com.amap.api.location.AMapLocationClient;
//import com.amap.api.location.AMapLocationListener;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;

/**
 * Created by djy-ubuntu16 on 9/23/17.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to handle app links.

        final Button teacherButton = findViewById(R.id.button_teacher);
        teacherButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegLogActivity.class));
            }
        });

        final Button parentButton = findViewById(R.id.button_parent);
        parentButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegLogActivity.class));
            }
        });
    }
//    //声明AMapLocationClient类对象
//    public AMapLocationClient mLocationClient = null;
//    //声明定位回调监听器
//    public AMapLocationListener mLocationChangeListener = new AMapLocationListener() {
//        @Override
//        public void onLocationChanged(AMapLocation aMapLocation) {
//            if (aMapLocation != null) {
//                if (aMapLocation.getErrorCode() == 0) {
//                    //可在其中解析amapLocation获取相应内容。
//                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                    aMapLocation.getLatitude();//获取纬度
//                    aMapLocation.getLongitude();//获取经度
//                    aMapLocation.getAccuracy();//获取精度信息
//                    aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                    aMapLocation.getCountry();//国家信息
//                    aMapLocation.getProvince();//省信息
//                    aMapLocation.getCity();//城市信息
//                    aMapLocation.getDistrict();//城区信息
//                    aMapLocation.getStreet();//街道信息
//                    aMapLocation.getStreetNum();//街道门牌号信息
//                    aMapLocation.getCityCode();//城市编码
//                    aMapLocation.getAdCode();//地区编码
//                    aMapLocation.getAoiName();//获取当前定位点的AOI信息
//                    aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
//                    aMapLocation.getFloor();//获取当前室内定位的楼层
//                    aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
////获取定位时间
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Date date = new Date(aMapLocation.getTime());
//                    df.format(date);
//                } else {
//                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
//                    Log.e("AmapError","location Error, ErrCode:"
//                            + aMapLocation.getErrorCode() + ", errInfo:"
//                            + aMapLocation.getErrorInfo());
//                }
//            }
//        }
//    };
////    //初始化定位
////    mLocationClient = new AMapLocationClient(getApplicationContext());
////    //设置定位回调监听
////    mLocationClient.setLocationListener(mLocationChangeListener);
}

package com.djy.familyedu;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.poisearch.PoiSearch;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public abstract class MapActivity extends AppCompatActivity implements AMapLocationListener
        , CompoundButton.OnCheckedChangeListener, LocationSource,PoiSearch.OnPoiSearchListener
//        OnMarkerClickListener, InfoWindowAdapter, TextWatcher,
//        , OnClickListener {

{
    protected static final String TAG = "MapActivity";
    public AMapLocationClient mlocationClient;
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {

        }
    };
//    MaterialSearchBar searchBar = (MaterialSearchBar) findViewById(R.id.searchBar);
    private MapView mapView;
    private AMap aMap;
    private AMapLocationClient mapLocationClient;
    private LocationSource.OnLocationChangedListener locationChangedListener;
    private AMapLocationClientOption mapLocationClientOption;

    private List<String> searchHistory;
//    private MaterialSearchBar searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
/*        setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                       @Override
                                       public boolean onMenuItemClick(MenuItem item) {
                                           switch (item.getItemId())
                                           {
                                               case R.id.clear_search_history:
                                                   clearSearchHostory();
                                                   break;
                                           }
                                           return false;
                                       }
                                   }*/
initSearchIntent();

        initMapView();
        initUI();
        requestPermissions();
        setMyLocationStyle();
        startClientLocation();
    }

    private String queryString;

    private void initSearchIntent() {
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            queryString = intent.getStringExtra(SearchManager.QUERY);
            doSearchQuery();
        }
    }
    private PoiSearch.Query query;
    private PoiSearch poiSearch;
    protected void doSearchQuery() {

        String keyWord = queryString;
        query = new PoiSearch.Query(keyWord, "", "海口市");

        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

//    SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
//            SearchHistoryProvider.AUTHORITY, SearchHistoryProvider.MODE);
//            suggestions.saveRecentQuery(queryString,null);
//}
    private void initMapView() {
        if (aMap == null) {
            aMap = mapView.getMap();
            if (mapLocationClient == null) {
                LatLng haiKou = new LatLng(19.9816509, 110.3330756);
                aMap.addMarker(new MarkerOptions().position(haiKou).title("app出生地"));
                aMap.moveCamera(CameraUpdateFactory.newLatLng(haiKou));
                Toast.makeText(this, "定位中...", Toast.LENGTH_SHORT).show();
            }
            aMap.setMapType(AMap.MAP_TYPE_NORMAL);
            aMap.getUiSettings().setCompassEnabled(true);
            aMap.showBuildings(true);
        }
    }

    private
    private void initUI() {
            searchBar = findViewById(R.id.searchBar);
//            searchBar.setOnSearchActionListener(this);
            //restore last queries from disk
            lastSearches = loadSearchSuggestionFromDisk();
            searchBar.setLastSuggestions(list);
            //Inflate menu and setup OnMenuItemClickListener
            searchBar.inflateMenu(R.menu.main);
            searchBar.getMenu().setOnMenuItemClickListener(this);
    }
    private List<String> loadSearchSuggestionFromDisk() {

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
        aMap.setMyLocationStyle(myLocationStyle.myLocationType(
                MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER));
        aMap.setMyLocationEnabled(true);
        aMap.setMyLocationStyle(myLocationStyle);
    }

    private void startClientLocation() {
        aMap.setLocationSource(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);

//        aMap.setTrafficEnabled(true);
        aMap.setMyLocationEnabled(true);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (mapLocationClient != null) {
            mapLocationClient.onDestroy();
            mapLocationClient = null;
            mapLocationClientOption = null;
        }
        saveSearchHistoryToDisk(searchBar.getLastSuggestions());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    private void saveSearchHistoryToDisk(List<String> lastSearches) {
        SharedPreferences searchHistory = this.getSharedPreferences("SearchHistory", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = searchHistory.edit();

    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
        String s = enabled ? "enabled" : "disabled";
        Toast.makeText(MapActivity.this, "Search " + s, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSearchConfirmed(CharSequence text) {
        startSearch(text.toString(), true, null, true);
    }public void onLocationChanged(AMapLocation mapLocation) {
        if (locationChangedListener != null && mapLocation != null) {
            locationChangedListener.onLocationChanged(mapLocation);
            if (mapLocation.getErrorCode() == 0) {
                mapLocation.getLocationType();
                LatLng currentPosition = new LatLng(mapLocation.getLatitude(), mapLocation.getLongitude());//获取纬度;

                mapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(mapLocation.getTime());
                df.format(date);//定位时间
//                aMap.addMarker(new MarkerOptions().position(currentPosition).title("Lastest position"));
                aMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
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
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode){
            case MaterialSearchBar.BUTTON_NAVIGATION:
                drawer.openDrawer(Gravity.LEFT);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    public void clearSearchHostory() {
        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                SearchHistoryProvider.AUTHORITY, SearchHistoryProvider.MODE);
        suggestions.clearHistory();
    }

    private List<String> fetchSearchHistoryFromDisk() {
        SharedPreferences searchHistoryPref = this.getSharedPreferences("SearchHistory", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = searchHistoryPref.edit();
        String searchHistory = searchHistoryPref.getString("searchHistory", new String());

    }

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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode){
            case MaterialSearchBar.BUTTON_NAVIGATION:
                doSearch
                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                openVoiceRecognizer();
        }
    }

public class SearchHistoryProvider extends SearchRecentSuggestionsProvider {
        public final static String AUTHORITY = "com.djy.SearchHistoryProvider";
        public final static int MODE = DATABASE_MODE_QUERIES;

        public SearchHistoryProvider() {
            setupSuggestions(AUTHORITY, MODE);
        }
    }


searchBar.inflateMenu(R.menu.main);

searchBar.getMenu().
    );
//    protected void doSearchQuery() {
//        keyWord = mSearchText.getText().toString().trim();
//        currentPage = 0;
//        queryString = new PoiSearch.Query(keyword, "", "海口市");
//        queryString.setPageSize(12);
//        queryString.setPageNum(currentPage);
//
//        if (lp != null) {
//            poiSearch = new PoiSearch(this, queryString);
//            poiSearch.setOnPoiSearchListener(this);
//            poiSearch.setBound(new PoiSearch.SearchBound(lp, 3500, true));
//            poiSearch.searchPOIAsync();
//        }
//    }
}


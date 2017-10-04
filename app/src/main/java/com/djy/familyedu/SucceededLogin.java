package com.djy.familyedu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.avos.avoscloud.AVUser;

// Log tag
import android.app.ListActivity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

public class SucceededLogin extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succeeded_login);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_topbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_settings:
                startActivity(new Intent(SucceededLogin.this, SettingsActivity.class));
                return true;
            case R.id.menu_about:
                startActivity(new Intent(SucceededLogin.this, AboutActivity.class));
                return true;
            case R.id.menu_amap:
                startActivity(new Intent(SucceededLogin.this, MapActivity.class));
                return true;
            case R.id.menu_log_out:
                AVUser.getCurrentUser().logOut();
//                AVUser currentUser = AVUser.getCurrentUser();
                startActivity(new Intent(SucceededLogin.this, ChooseEntryActivity.class));
                SucceededLogin.this.finish();
        }
        return false;
    }
}


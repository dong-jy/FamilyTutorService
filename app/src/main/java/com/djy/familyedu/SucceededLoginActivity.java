package com.djy.familyedu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.avos.avoscloud.AVUser;

// Log tag
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SucceededLoginActivity extends AppCompatActivity {

    private List<UserListDataModel> usersList;
    private RecyclerView recyclerView ;
    private UserListAdapter userListAdapter;
    private LinearLayoutManager llm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succeeded_login);

        recyclerView = findViewById(R.id.users_recycler_view);

        prepareUserInfo();
        userListAdapter = new UserListAdapter(usersList);

        llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(userListAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int posistion) {
                        startActivity(new Intent(SucceededLoginActivity.this, ChooseEntryActivity.class));
                    }
                })
        );


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        prepareDashboardInfo();
    }
    private void prepareUserInfo() {
        usersList = new ArrayList<>();

        usersList.add(new UserListDataModel("周老师","海南医学院", new ArrayList<String>(){{add("数学"); add("语文");}}));
        usersList.add(new UserListDataModel("王老师","海南医学院", new ArrayList<String>(){{add("数学"); }}));
        usersList.add(new UserListDataModel("周老师","海南大学", new ArrayList<String>(){{add("英语");add("数学"); add("语文");}}));
        usersList.add(new UserListDataModel("周老师","海南医学院", new ArrayList<String>(){{add("数学"); add("语文");add("化学");}}));
        usersList.add(new UserListDataModel("李老师","海南师范", new ArrayList<String>(){{add("英语");add("数学"); add("语文");}}));
        usersList.add(new UserListDataModel("周老师","海南医学院", new ArrayList<String>(){{add("数学"); add("语文");add("物理");add("化学");add("生物");}}));
        usersList.add(new UserListDataModel("周老师","海南大学", new ArrayList<String>(){{add("物理");add("数学"); add("语文");}}));
        usersList.add(new UserListDataModel("周老师","海南医学院", new ArrayList<String>(){{add("数学"); add("语文");add("化学");}}));
        usersList.add(new UserListDataModel("周老师","海南医学院", new ArrayList<String>(){{add("语文");}}));
        usersList.add(new UserListDataModel("周老师","海南医学院", new ArrayList<String>(){{add("数学");add("物理"); add("语文");}}));
    }

    private List<DashBoardDataModel> dashboardList;
    private void prepareDashboardInfo() {
        dashboardList = new ArrayList<>();

        dashboardList.add(new DashBoardDataModel("周老师发表了学习心得"));
        dashboardList.add(new DashBoardDataModel("李老师发表了《家教总结1》"));
        dashboardList.add(new DashBoardDataModel("王老师发表了学习心得"));
        dashboardList.add(new DashBoardDataModel("李老师发表了《家教总结2》"));
        dashboardList.add(new DashBoardDataModel("周老师发表了学习心得"));
        dashboardList.add(new DashBoardDataModel("李老师发表了《家教总结3》"));
        dashboardList.add(new DashBoardDataModel("周老师发表了学习心得"));
        dashboardList.add(new DashBoardDataModel("李老师发表了《家教总结4》"));
        dashboardList.add(new DashBoardDataModel("周老师发表了学习心得"));
        dashboardList.add(new DashBoardDataModel("李老师发表了《家教总结5》"));
        dashboardList.add(new DashBoardDataModel("周老师发表了学习心得"));
        dashboardList.add(new DashBoardDataModel("李老师发表了《家教总结6》"));
        dashboardList.add(new DashBoardDataModel("周老师发表了学习心得"));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    recyclerView.setAdapter(userListAdapter);
                    recyclerView.setLayoutManager(llm);
                    return true;
                case R.id.navigation_dashboard:
                    prepareDashboardInfo();

                    DashboardAdapter dashboardAdapter = new DashboardAdapter(dashboardList);
                    recyclerView.setAdapter(dashboardAdapter);
                    GridLayoutManager glm = new GridLayoutManager(getApplicationContext(), 2);
                    glm.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(glm);

                    return true;
                case R.id.navigation_notifications:
                    recyclerView.setAdapter(null);
                    return true;
            }
            return false;
        }
    };

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
                startActivity(new Intent(SucceededLoginActivity.this, SettingsActivity.class));
                return true;
            case R.id.menu_about:
                startActivity(new Intent(SucceededLoginActivity.this, AboutActivity.class));
                return true;
            case R.id.menu_amap:
                startActivity(new Intent(SucceededLoginActivity.this, MapActivity.class));
                return true;
            case R.id.menu_log_out:
                AVUser.getCurrentUser().logOut();
//                AVUser currentUser = AVUser.getCurrentUser();
                startActivity(new Intent(SucceededLoginActivity.this, ChooseEntryActivity.class));
                SucceededLoginActivity.this.finish();
        }
        return false;
    }
}


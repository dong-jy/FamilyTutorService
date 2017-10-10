package com.djy.familyedu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.qc.stat.common.User;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by djy-ubuntu16 on 10/10/17.
 */

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder> {

    public static class DashboardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        protected ImageView vUserPhoto;
        protected TextView vUsername;
//        protected TextView vSchool;
//        protected TextView vSubjects;
//        protected Button vChatButton;

        public DashboardViewHolder(View v) {
            super(v);

//        vUserPhoto = v.findViewById(R.id.user_photo);
            vUsername = v.findViewById(R.id.user_name);
//            vSchool = v.findViewById(R.id.user_school);
//            vSubjects = v.findViewById(R.id.user_subjects);
//            vChatButton = v.findViewById(R.id.chat_button);
//            v.setOnClickListener(this);
//            vChatButton.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Log.d(TAG, "Onclick " + getAdapterPosition() + " ");
        }
    }

    private Context mContext;
    private List<DashBoardDataModel> dashboardList;

    DashboardAdapter(Context context, List<DashBoardDataModel> dashboardList) {
        mContext = context;
        this.dashboardList = dashboardList;
    }

    public DashboardAdapter(List<DashBoardDataModel> dashboardList) {
        this.dashboardList = dashboardList;
    }

    @Override
    public DashboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View thisItemsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard,  parent, false);

        return new DashboardViewHolder(thisItemsView);
    }

    @Override
    public void onBindViewHolder(DashboardViewHolder holder, int position) {
        DashBoardDataModel user_article = dashboardList.get(position);
        holder.vUsername.setText(user_article.getUserName());
    }
    @Override
    public int getItemCount() {
        return dashboardList.size();
    }
}


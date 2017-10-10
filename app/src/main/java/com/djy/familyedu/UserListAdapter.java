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

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder> {

public static class UserListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected ImageView vUserPhoto;
    protected TextView vUsername;
    protected TextView vSchool;
    protected TextView vSubjects;
    protected Button vChatButton;

    public UserListViewHolder(View v) {
        super(v);
//        vUserPhoto = v.findViewById(R.id.user_photo);
        vUsername = v.findViewById(R.id.user_name);
        vSchool = v.findViewById(R.id.user_school);
        vSubjects = v.findViewById(R.id.user_subjects);
        vChatButton = v.findViewById(R.id.chat_button);
        v.setOnClickListener(this);
        vChatButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

    }
}

    private Context mContext;
    private List<UserListDataModel> usersList;

    UserListAdapter(Context context, List<UserListDataModel> usersList) {
        mContext = context;
        this.usersList = usersList;
    }

    public UserListAdapter(List<UserListDataModel> usersList) {
        this.usersList = usersList;
    }

    @Override
    public UserListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View thisItemsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_card,  parent, false);
        thisItemsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int pos = getA
//                startActivity()

            }
        });
        return new UserListViewHolder(thisItemsView);
    }

    @Override
    public void onBindViewHolder(UserListViewHolder holder, int position) {
//        super(holder, position);
        UserListDataModel user = usersList.get(position);
//        holder.mCardView.setCardBackgroundColor();
//        holder.vUserPhoto.setImageDrawable(user.);
        holder.vUsername.setText(user.getUserName());
        holder.vSchool.setText(user.getUserSchool());
        holder.vSubjects.setText(user.getUserSubjects().toString());
    }
    @Override
    public int getItemCount() {
        return usersList.size();
    }
}

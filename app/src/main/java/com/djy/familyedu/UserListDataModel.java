package com.djy.familyedu;

import java.util.List;

/**
 * Created by djy-ubuntu16 on 10/10/17.
 */

public class UserListDataModel {
    private String mUserName;
    private String mSchool;
    private List<String> mSubjects;

    public UserListDataModel(String userName, String school, List<String> subjects) {
        mUserName = userName;
        mSchool = school;
        mSubjects = subjects;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getUserSchool() {
        return mSchool;
    }

    public List<String> getUserSubjects() {
        return mSubjects;
    }

}

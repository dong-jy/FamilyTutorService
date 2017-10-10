package com.djy.familyedu;

import java.util.List;

/**
 * Created by djy-ubuntu16 on 10/10/17.
 */

public class DashBoardDataModel {
    private String mUserName;
//    private String mSchool;
//    private List<String> mSubjects;

    public DashBoardDataModel(String userName) {
        mUserName = userName;
//        mSchool = school;
//        mSubjects = subjects;
    }

    public String getUserName() {
        return mUserName;
    }

//    public String getUserSchool() {
//        return mSchool;
//    }
//
//    public List<String> getUserSubjects() {
//        return mSubjects;
//    }

}

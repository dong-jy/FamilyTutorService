package com.djy.familyedu;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by djy-ubuntu16 on 10/4/17.
 */

public class AVOSInit extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "j2hrJcfbHICaD33d2UeYwdSW-gzGzoHsz", "xpdfJ1WEnRFhdUoMySBvoe9v");
        AVOSCloud.setDebugLogEnabled(true);
    }
}

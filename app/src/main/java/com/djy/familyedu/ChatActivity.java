package com.djy.familyedu;

import android.app.Application;

import cn.leancloud.chatkit.LCChatKit;

/**
 * Created by djy-ubuntu16 on 10/5/17.
 */

public class ChatActivity extends AVOSInit {
    private final String APP_ID = "j2hrJcfbHICaD33d2UeYwdSW-gzGzoHsz";
    private final String APP_KEY = "xpdfJ1WEnRFhdUoMySBvoe9v";

    @Override
    public void onCreate() {
        super.onCreate();
        LCChatKit.getInstance().setProfileProvider(CustomUserProvider.getInstance());
        LCChatKit.getInstance().init(getApplicationContext(), APP_ID, APP_KEY);
    }
}

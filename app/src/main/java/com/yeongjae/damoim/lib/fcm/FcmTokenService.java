package com.yeongjae.damoim.lib.fcm;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.yeongjae.damoim.util.LogUtils;

public class FcmTokenService {

    public static String getFcmToken() {
        LogUtils.logInfo(FirebaseInstanceId.getInstance().getToken() + "");
        return FirebaseInstanceId.getInstance().getToken();
    }
}

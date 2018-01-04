package com.pro.cryptobot.presentation.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.pro.cryptobot.presentation.CryptoBotApplication;

/**
 * Created by OCCOYANC on 12/22/2017.
 */

public class ConnectivityUtil {
    public static boolean isNetworkConnected(Context context) {
        if (context == null) {
            context = CryptoBotApplication.getInstance();
        }
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    public static boolean isNetworkConnected() {
        return isNetworkConnected(null);
    }
}

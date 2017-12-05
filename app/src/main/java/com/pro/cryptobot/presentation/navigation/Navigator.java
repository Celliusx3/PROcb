package com.pro.cryptobot.presentation.navigation;

/**
 * Created by coyanoh on 01/12/2017.
 */


import android.content.Context;
import android.content.Intent;

import com.pro.cryptobot.presentation.view.activity.MainActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    private static final String PLAY_STORE_URI = "market://details?id=";
    public static final String CRYPTO_PACKAGE_NAME = "com.pro.cryptobot";

    @Inject
    public Navigator() {
    }

    public void navigateToMain(Context context) {
        if (context != null) {
            Intent intentToLaunch = MainActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

}

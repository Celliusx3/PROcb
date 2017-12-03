package com.pro.cryptobot.presentation;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.pro.cryptobot.di.component.ApplicationComponent;
import com.pro.cryptobot.di.component.DaggerApplicationComponent;
import com.pro.cryptobot.di.module.ApplicationModule;

import butterknife.ButterKnife;

/**
 * Created by coyanoh on 28/11/2017.
 */

public class CryptoBotApplication extends MultiDexApplication {

    private ApplicationComponent mApplicationComponent;
    private final Object mLock = new Object();

    // Singleton Instance
    private static CryptoBotApplication singleton;

    public static CryptoBotApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeSDKs();

        Log.d("Application", "TEST");

        synchronized (mLock) {
            singleton = this;
            Log.d("Application", "GG");
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
            mApplicationComponent.inject(this);
        }
    }

    private void initializeSDKs() {
//        if (BuildConfig.CRASHLYTICS_ENABLED || !BuildConfig.DEBUG)
//            Fabric.with(this, new Crashlytics());


        ButterKnife.setDebug(true);

        //AstroGoImageLoader.getInstance().init(this);

//        // Initialize Calligraphy
//        CalligraphyConfig.initDefault(
//                new CalligraphyConfig.Builder()
//                        // TODO - SET THIS FONT TEMPORALLY WHILE WE GET THE CORRECT FONT FILES IN OTF
//                        .setDefaultFontPath("fonts/GothamRoundedBook.otf")
//                        .setFontAttrId(R.attr.fontPath)
//                        .build()
//        );

    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}

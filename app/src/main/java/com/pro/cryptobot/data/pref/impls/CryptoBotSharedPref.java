package com.pro.cryptobot.data.pref.impls;

import android.content.SharedPreferences;

import com.pro.cryptobot.data.pref.CryptoBotPref;

/**
 * Created by coyanoh on 28/11/2017.
 */

public class CryptoBotSharedPref implements CryptoBotPref {

    private final SharedPreferences preferences;

    public CryptoBotSharedPref(SharedPreferences sharedPreferences) {
        this.preferences = sharedPreferences;
    }

}

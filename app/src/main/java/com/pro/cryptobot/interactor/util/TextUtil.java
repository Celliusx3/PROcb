package com.pro.cryptobot.interactor.util;

import android.content.Context;
import android.support.annotation.Nullable;

import com.pro.cryptobot.presentation.CryptoBotApplication;

/**
 * Created by OCCOYANC on 12/22/2017.
 */

public class TextUtil {

    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

    public static String firstLetterUppercase(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static String getStringFromResource(Context context, String dictionaryKey) {
        if (context == null) {
            context = CryptoBotApplication.getInstance();
        }

        int resourceId = context.getResources().getIdentifier(dictionaryKey,"string",context.getPackageName());

        if(resourceId == 0){
            return "";
        } else{
            return context.getString(resourceId);
        }
    }

    public static String getStringFromResource(String dictionaryKey) {
        return getStringFromResource(null,dictionaryKey);
    }
}

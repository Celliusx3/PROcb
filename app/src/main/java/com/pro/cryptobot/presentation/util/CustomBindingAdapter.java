package com.pro.cryptobot.presentation.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by OCCOYANC on 12/10/2017.
 */

public class CustomBindingAdapter {

    @BindingAdapter({"visible"})
    public static void setVisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter({"invisible"})
    public static void setInvisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }

    @BindingAdapter("textWatcher")
    public static void bindTextWatcher(View view, TextWatcher textWatcher) {
        if (view instanceof EditText) {
            ((EditText) view).addTextChangedListener(textWatcher);
        }
    }
}

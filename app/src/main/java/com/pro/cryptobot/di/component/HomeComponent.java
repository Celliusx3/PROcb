package com.pro.cryptobot.di.component;

import com.pro.cryptobot.di.module.HomeModule;
import com.pro.cryptobot.presentation.view.activity.MainActivity;

import dagger.Subcomponent;

/**
 * Created by coyanoh on 01/12/2017.
 */

@ActivityScope
@Subcomponent(modules = {HomeModule.class})
public interface HomeComponent {
    void inject(MainActivity mainActivity);
}

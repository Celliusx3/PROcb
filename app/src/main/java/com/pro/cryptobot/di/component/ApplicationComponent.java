package com.pro.cryptobot.di.component;

import android.content.Context;

import com.pro.cryptobot.di.module.ApplicationModule;
import com.pro.cryptobot.di.module.HomeModule;
import com.pro.cryptobot.di.module.RepositoryModule;
import com.pro.cryptobot.presentation.CryptoBotApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by coyanoh on 27/11/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class})
public interface ApplicationComponent {
    CryptoBotApplication getApplication();

    Context getApplicationContext();

    void inject(CryptoBotApplication cryptoBotApplication);

    HomeComponent plus(HomeModule homeModule);

}

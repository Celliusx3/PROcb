package com.pro.cryptobot.di.module;

import android.content.Context;

import com.pro.cryptobot.data.repository.PriceRepository;
import com.pro.cryptobot.interactor.scheduler.BaseSchedulerProvider;
import com.pro.cryptobot.interactor.viewmodel.MainViewModel;
import com.pro.cryptobot.interactor.viewmodel.impl.MainViewModelImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyanoh on 01/12/2017.
 */

@Module
public class HomeModule {

    private Context mContext;

    public HomeModule(Context context) {
        mContext = context;
    }

    @Provides
    MainViewModel provideMainViewModel(PriceRepository repository, BaseSchedulerProvider provider) {
        return new MainViewModelImpl(repository, provider);
    }

    /*@Provides
    NavigationViewModel provideNavigationViewModel(ConfigRepository repository, UserRepository userRepository, BaseSchedulerProvider provider) {
        return new NavigationViewModelImpl(repository, userRepository, provider);
    }*/
}

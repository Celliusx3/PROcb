package com.pro.cryptobot.di.module;

import com.pro.cryptobot.data.api.ApiService;
import com.pro.cryptobot.data.repository.PriceRepository;
import com.pro.cryptobot.data.repository.impl.PriceRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyanoh on 03/12/2017.
 */

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    PriceRepository providePriceRepository(ApiService apiService) {
        return new PriceRepositoryImpl(apiService);
    }
}


package com.pro.cryptobot.data.repository.impl;

import com.pro.cryptobot.data.api.ApiService;
import com.pro.cryptobot.data.entity.Currency;
import com.pro.cryptobot.data.repository.PriceRepository;

import io.reactivex.Observable;

/**
 * Created by coyanoh on 27/11/2017.
 */

public class PriceRepositoryImpl implements PriceRepository {

    private final ApiService apiService;

    public PriceRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<Currency> getPrice(String fsym, String tsyms, String e, String extraParams) {
        return apiService.getPrice(fsym,tsyms,e,extraParams);
    }
}

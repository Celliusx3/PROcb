package com.pro.cryptobot.data.repository;

import com.pro.cryptobot.data.entity.Currency;

import io.reactivex.Observable;

/**
 * Created by coyanoh on 27/11/2017.
 */

public interface PriceRepository {

    Observable<Currency> getPrice(String fsym, String tsyms, String e, String extraParams);
}

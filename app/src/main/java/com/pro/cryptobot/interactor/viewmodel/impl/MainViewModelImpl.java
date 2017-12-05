package com.pro.cryptobot.interactor.viewmodel.impl;

import android.databinding.ObservableBoolean;

import com.pro.cryptobot.data.repository.PriceRepository;
import com.pro.cryptobot.interactor.scheduler.BaseSchedulerProvider;
import com.pro.cryptobot.interactor.viewmodel.BaseViewModel;
import com.pro.cryptobot.interactor.viewmodel.MainViewModel;

import io.reactivex.Observable;

/**
 * Created by coyanoh on 01/12/2017.
 */

public class MainViewModelImpl extends BaseViewModel implements MainViewModel {

    private ObservableBoolean loading = new ObservableBoolean(false);

    public MainViewModelImpl(PriceRepository repository, BaseSchedulerProvider scheduler) {
        super(repository, scheduler);
    }


    @Override
    public Observable<String> getPrice(String fysm, String tsyms, String e, String extraParams) {
        loading.set(true);
        return priceRepository.getPrice(fysm, tsyms, e, extraParams)
                .map(price->Double.toString(price.getUSD()))
                .doFinally(()->loading.set(false));
    }

    @Override
    public ObservableBoolean getLoading() {
        return loading;
    }
}

package com.pro.cryptobot.interactor.viewmodel.impl;

import android.databinding.ObservableBoolean;

import com.pro.cryptobot.data.repository.PriceRepository;
import com.pro.cryptobot.interactor.model.CurrencyModel;
import com.pro.cryptobot.interactor.model.CurrencyRequestModel;
import com.pro.cryptobot.interactor.scheduler.BaseSchedulerProvider;
import com.pro.cryptobot.interactor.viewmodel.BaseViewModel;
import com.pro.cryptobot.interactor.viewmodel.MainViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by coyanoh on 01/12/2017.
 */

public class MainViewModelImpl extends BaseViewModel implements MainViewModel {

    private ObservableBoolean loading = new ObservableBoolean(false);

    public MainViewModelImpl(PriceRepository repository, BaseSchedulerProvider scheduler) {
        super(repository, scheduler);
    }


    @Override
    public Single<List<CurrencyModel>> getPrice(List<CurrencyRequestModel> currencyRequestModels) {
        loading.set(true);

        return Observable.fromIterable(currencyRequestModels)
                .flatMap(currencyRequestModel -> {
                    String fsym        = currencyRequestModel.getFsym();
                    String tsyms       = currencyRequestModel.getTsyms();
                    String e           = currencyRequestModel.getE();
                    String extraParams = currencyRequestModel.getExtraParams();
                    String[] seperatedTsyms = tsyms.split(",");
                    return priceRepository.getPrice(fsym, tsyms, e, extraParams)
                            .compose(applySchedulers(false))
                            .map(currency->CurrencyModel.create(currency,fsym, seperatedTsyms));
                }).toList();
    }

    @Override
    public ObservableBoolean getLoading() {
        return loading;
    }
}

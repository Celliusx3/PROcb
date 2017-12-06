package com.pro.cryptobot.interactor.viewmodel;

import android.databinding.ObservableBoolean;

import com.pro.cryptobot.interactor.model.CurrencyModel;
import com.pro.cryptobot.interactor.model.CurrencyRequestModel;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by coyanoh on 01/12/2017.
 */

public interface MainViewModel extends ViewModel {

    Single<List<CurrencyModel>> getPrice(List<CurrencyRequestModel> currencyRequestModels);

    ObservableBoolean getLoading();

}

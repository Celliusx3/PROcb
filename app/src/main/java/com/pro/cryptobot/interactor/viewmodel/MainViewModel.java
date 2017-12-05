package com.pro.cryptobot.interactor.viewmodel;

import android.databinding.ObservableBoolean;

import io.reactivex.Observable;

/**
 * Created by coyanoh on 01/12/2017.
 */

public interface MainViewModel extends ViewModel {

    Observable<String> getPrice(String fsym, String tsyms, String e, String extraParams);

    ObservableBoolean getLoading();

}

package com.pro.cryptobot.interactor.viewmodel;

import io.reactivex.Observable;

/**
 * Created by coyanoh on 01/12/2017.
 */

public interface ViewModel {
    void onCreateView();
    void onAttachView();
    void onDetachView();
    Observable<Boolean> getLogout();
    Observable<Boolean> getLogoutLoading();
    Observable<String> getShowToastMessage();
}

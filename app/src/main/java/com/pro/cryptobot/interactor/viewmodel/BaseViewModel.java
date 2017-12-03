package com.pro.cryptobot.interactor.viewmodel;

import com.pro.cryptobot.data.exception.RetrofitException;
import com.pro.cryptobot.data.repository.PriceRepository;
import com.pro.cryptobot.interactor.scheduler.BaseSchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyanoh on 01/12/2017.
 */

public abstract class BaseViewModel {
    private static final String TAG = BaseViewModel.class.getSimpleName();

    //protected final UserRepository userRepository;
    protected final PriceRepository priceRepository;
    private final BaseSchedulerProvider scheduler;
    protected CompositeDisposable subscriptions;
    protected PublishSubject<Boolean> logout = PublishSubject.create();

    protected PublishSubject<Boolean> logoutLoading = PublishSubject.create();
    protected PublishSubject<String> showToastMessage = PublishSubject.create();

    /*public BaseViewModel(ConfigRepository configRepository, UserRepository userRepository,
                         BaseSchedulerProvider scheduler) {
        this.configRepository = configRepository;
        this.userRepository = userRepository;
        this.scheduler = scheduler;

    }*/

    public BaseViewModel(PriceRepository priceRepository, BaseSchedulerProvider scheduler) {
        this.scheduler = scheduler;
        this.priceRepository = priceRepository;

    }

    protected BaseSchedulerProvider getScheduler() {
        return scheduler;
    }

    public void onAttachView() {
        if (subscriptions == null || subscriptions.isDisposed())
            subscriptions = new CompositeDisposable();
    }

    public void onDetachView() {
        if (subscriptions != null)
            subscriptions.dispose();
    }

    public void onCreateView() {
        subscriptions = new CompositeDisposable();
    }

    public Observable<Boolean> getLogoutLoading() {
        return logoutLoading;
    }

    public Observable<Boolean> getLogout() {
        return logout;
    }

    public Observable<String> getShowToastMessage() {
        return showToastMessage;
    }

    /**
     * Utility method to apply default schedulers automatically.
     * See dhttp://blog.danlew.net/2015/03/02/dont-break-the-chain/
     *
     * @param <T> type
     * @return transformer
     */
    protected <T> ObservableTransformer<T, T> applySchedulers(boolean showApiException) {
        return observable -> observable
                .subscribeOn(getScheduler().io())
                .observeOn(getScheduler().ui())
                .doOnError(throwable -> {

                    /*if (isUnauthorizedAccess(throwable) || isUnauthorizedAccessByResponseCode(throwable)) {
                        handleUnauthorizedAccessException(throwable);
                        return;
                    }*/

                    /*if (throwable instanceof ApiException && showApiException) {
                        ApiException apiException = ((ApiException) throwable);
                        handleApiException(apiException);
                    } else if (throwable instanceof RetrofitException) {
                        RetrofitException retrofitException = (RetrofitException) throwable;
                        switch (retrofitException.getKind()) {
                            case HTTP:
                                handleHttpException(retrofitException);
                                break;
                            case NETWORK:
                                handleNetworkException(retrofitException);
                                break;
                            case UNEXPECTED:
                                handleUnexpectedException(retrofitException);
                        }
                    }*/

                });
    }

    protected <T> ObservableTransformer<T, T> applySchedulers() {
        return applySchedulers(true);
    }

    private void handleUnauthorizedAccessException(Throwable throwable) {
        //userRepository.setSSOTicket("");
        //userRepository.setAuthorizationToken("");
        logout.onNext(true);
    }

    /*protected void handleApiException(ApiException apiException) {
        String message = String.format("Error retrieving information from server. Code %1$s (%2$s)",
                apiException.getResponseCode(), apiException.getResponseMessage());
        showToastMessage.onNext(message);
    }*/

    protected void handleHttpException(RetrofitException retrofitException) {
        showToastMessage.onNext("Failed to get response from server. Code " + retrofitException.getResponse().code());
    }

    protected void handleNetworkException(RetrofitException retrofitException) {
        showToastMessage.onNext("Please check your internet connection.");
    }

    protected void handleUnexpectedException(RetrofitException retrofitException) throws RuntimeException {
        throw new RuntimeException(retrofitException);
    }

    protected boolean isUnauthorizedAccess(@NonNull Throwable throwable) {
        if (throwable instanceof RetrofitException) {
            RetrofitException exception = (RetrofitException) throwable;
            if (exception.getKind() == RetrofitException.Kind.HTTP) {
                if (exception.getResponse().code() == 401 || exception.getResponse().code() == 403) {
                    return true;
                }
            }
        }
        return false;
    }

    /*protected boolean isUnauthorizedAccessByResponseCode(@NonNull Throwable throwable) {
        if (throwable instanceof ApiException) {
            ApiException exception = (ApiException) throwable;
            if (exception.getResponseCode() == 401 || exception.getResponseCode() == 403) {
                return true;
            }
        }
        return false;
    }*/


    /*protected String getBaseImageUrl(int width, int height) {
        String baseUrlFormat = BuildConfig.BASE_IMAGE_URL;
        String imageEndpoint = configRepository.getAllConfigFromDisk().getImageEndpoint();
        return String.format(baseUrlFormat, imageEndpoint, width, height);
    }*/

    /*public String getBaseImageUrl(int height) {
        return getBaseImageUrl(0, height);
    }*/

    /*protected String createCompleteImageUrl(String imageUrl, int width, int height) {
        return getBaseImageUrl(width, height) + imageUrl;
    }*/

    /*protected String createCompleteImageUrl(String imageUrl, int height) {
        return createCompleteImageUrl(imageUrl, 0, height);
    }

    protected int getResizeHeightByType(int type) {
        return ImageResizeHelper.getResizeHeightByType(type);
    }*/
}

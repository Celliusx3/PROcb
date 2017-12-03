package com.pro.cryptobot.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.pro.cryptobot.data.api.ApiRoutes;
import com.pro.cryptobot.data.api.ApiService;
import com.pro.cryptobot.data.exception.RxErrorHandlingCallAdapterFactory;
import com.pro.cryptobot.data.pref.CryptoBotPref;
import com.pro.cryptobot.data.pref.impls.CryptoBotSharedPref;
import com.pro.cryptobot.interactor.scheduler.BaseSchedulerProvider;
import com.pro.cryptobot.interactor.scheduler.SchedulerProvider;
import com.pro.cryptobot.presentation.CryptoBotApplication;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by coyanoh on 28/11/2017.
 */

@Module
public class ApplicationModule {
    private final CryptoBotApplication mApplication;
    private SharedPreferences mPrefs;

    public ApplicationModule(final CryptoBotApplication cryptoBotApplication) {
        mApplication = cryptoBotApplication;
    }

    @Provides
    @Singleton
    CryptoBotApplication provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext(CryptoBotApplication cryptoBotApplication) {
        return cryptoBotApplication;
    }

    @Provides
    @Singleton
    BaseSchedulerProvider provideScheduler() {
        return SchedulerProvider.getInstance();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(CryptoBotPref pref) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    Request request = builder.build();
                    Response response = chain.proceed(request);

                    return response;
                })
                .addInterceptor(logging)
                .build();
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {
        String baseUrl = ApiRoutes.CRYPTOCOMPARE_BASE_URL;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }


    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        if (mPrefs == null) {
            String key = context.getPackageName();
            if (key == null) {
                throw new NullPointerException("Prefs key may not be null");
            }
            mPrefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        }
        return mPrefs;
    }

    @Provides
    @Singleton
    CryptoBotPref provideCryptoBotPref(SharedPreferences sharedPreferences) {
        return  new CryptoBotSharedPref(sharedPreferences);
    }
//    @Provides
//    @Singleton
//    SharedPreferences provideSharedPreferences(Context context) {
//        if (mPrefs == null) {
//            String key = context.getPackageName();
//            if (key == null) {
//                throw new NullPointerException("Prefs key may not be null");
//            }
//            mPrefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
//        }
//        return mPrefs;
//    }
//
//    @Provides
//    @Singleton
//    AstroGoPref provideAstroGoPref(SharedPreferences sharedPreferences) {
//        return  new AstroGoSharedPref(sharedPreferences);
//    }
//
//    @Provides
//    @Singleton
//    AstroGoDb provideAstroGoDb() {
//        return new AstroGoDbRoom();
//    }
}

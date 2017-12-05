package com.pro.cryptobot.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.pro.cryptobot.R;
import com.pro.cryptobot.databinding.IncludeMainBinding;
import com.pro.cryptobot.di.module.HomeModule;
import com.pro.cryptobot.interactor.viewmodel.MainViewModel;
import com.pro.cryptobot.interactor.viewmodel.ViewModel;
import com.pro.cryptobot.presentation.CryptoBotApplication;
import com.pro.cryptobot.presentation.view.adapter.CurrencyListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainViewModel mainViewModel;

    @BindView(R.id.srl_main)
    SwipeRefreshLayout srlView;

    @BindView(R.id.rl_main)
    RelativeLayout rlRootView;

    @BindView(R.id.rv_currency_listing)
    RecyclerView rvCurrency;

    private CurrencyListAdapter currencyListAdapter;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected ViewModel getViewModel() {
        return mainViewModel;
    }

    @Override
    protected View getRootView() {
        return rlRootView;
    }


    @Override
    protected String getToolbarTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected void onInject() {
        Log.d(TAG,Boolean.toString(CryptoBotApplication.getInstance()== null));
        CryptoBotApplication.getInstance()
                .getApplicationComponent()
                .plus(new HomeModule(this))
                .inject(this);}

    @Override
    protected void onBindData(@Nullable View view) {
        IncludeMainBinding binding = DataBindingUtil.bind(view);
        binding.setViewModel(mainViewModel);
        onInitializeSwipeListener();
        setupUserDeviceAdapter(onInitializeList());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupUserDeviceAdapter(List<String> registeredCurrency) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL, false);
        rvCurrency.setLayoutManager(layoutManager);
        currencyListAdapter = new CurrencyListAdapter(registeredCurrency);
        subscribeSelectedRegisteredDevice();
        rvCurrency.setAdapter(currencyListAdapter);
    }

    private void subscribeSelectedRegisteredDevice() {
        if (currencyListAdapter == null)
            return;

        // Switch Device
        currencyListAdapter.getSelectedCurrency()
                .compose(bindToLifecycle())
                .observeOn(getUiScheduler())
                .subscribe(selectedCurrency -> {
                }, throwable -> {
                });
    }

    private List<String> onInitializeList(){
        List<String> registeredCurrency = new ArrayList<>();
        registeredCurrency.add("BTC");
        registeredCurrency.add("ETH");
        return registeredCurrency;
    }

    private void onInitializeSwipeListener(){
        srlView.setOnRefreshListener(() -> mainViewModel.getPrice("BTC","USD,MYR","Coinbase", null)
                .compose(bindToLifecycle())
                .subscribeOn(getIoScheduler())
                .observeOn(getUiScheduler())
                .doFinally(()->srlView.setRefreshing(false))
                .subscribe(price->{
                    Log.d(TAG,price);}));
    }
}


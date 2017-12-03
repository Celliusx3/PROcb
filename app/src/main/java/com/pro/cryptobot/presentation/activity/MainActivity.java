package com.pro.cryptobot.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.pro.cryptobot.R;
import com.pro.cryptobot.databinding.ActivityMainBinding;
import com.pro.cryptobot.di.module.HomeModule;
import com.pro.cryptobot.interactor.viewmodel.MainViewModel;
import com.pro.cryptobot.interactor.viewmodel.ViewModel;
import com.pro.cryptobot.presentation.CryptoBotApplication;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainViewModel mainViewModel;

    @BindView(R.id.rl_main)
    RelativeLayout rlRootView;


    /*@Inject
    OnBoardingViewModel onBoardingViewModel;

    @Inject
    AstroGoPref pref;

    @BindView(R.id.rlView)
    RelativeLayout rlRootView;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @BindView(R.id.btnRegister)
    Button btnRegister;

    @BindView(R.id.tvSkip)
    TextView tvSkip;

    @BindView(R.id.llSkipContainer)
    LinearLayout llSkipContainer;

    @BindView(R.id.tvLearnMore)
    TextView tvLearnMore;

    @BindView(R.id.ivLogo)
    View ivLogo;*/

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
    protected void onInject() {
        Log.d(TAG,Boolean.toString(CryptoBotApplication.getInstance()== null));
        CryptoBotApplication.getInstance()
                .getApplicationComponent()
                .plus(new HomeModule(this))
                .inject(this);}

    @Override
    protected void onBindData(@Nullable View view) {
        ActivityMainBinding binding = DataBindingUtil.bind(view);
        binding.setViewModel(mainViewModel);
        mainViewModel.getPrice("BTC","USD,EUR","Coinbase", null)
                .compose(bindToLifecycle())
                .observeOn(getUiScheduler())
                .subscribe(price->{
                    Log.d(TAG,price);});
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}


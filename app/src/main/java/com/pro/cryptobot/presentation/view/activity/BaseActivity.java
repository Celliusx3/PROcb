package com.pro.cryptobot.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.pro.cryptobot.R;
import com.pro.cryptobot.interactor.scheduler.BaseSchedulerProvider;
import com.pro.cryptobot.interactor.viewmodel.ViewModel;
import com.pro.cryptobot.presentation.navigation.Navigator;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Scheduler;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by coyanoh on 01/12/2017.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private static final long DOUBLE_BACK_TO_EXIT_DURATION = 2000;

    @Inject
    Navigator navigator;

    @Inject
    BaseSchedulerProvider scheduler;

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Unbinder unbinder;

    private long mBackButtonLastPressedElapsedTime;

    protected abstract @LayoutRes
    int getLayoutResource();

    protected abstract ViewModel getViewModel();

    protected abstract View getRootView();

    public Scheduler getUiScheduler() {
        return scheduler.ui();
    }

    protected Scheduler getIoScheduler() {
        return scheduler.io();
    }

    protected Navigator getNavigator() {
        return navigator;
    }

    @Nullable
    public Toolbar getToolbar() {
        return toolbar;
    }

    protected String getToolbarTitle() {
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onSetContentView();
        onSetOrientation();
        onGetInputData();
        onBindView();
        onInject();
        onBindData(getRootView());

        if (getViewModel() != null) {
            getViewModel().onCreateView();
            getViewModel().getLogout()
                    .compose(bindToLifecycle())
                    .observeOn(getUiScheduler())
                    .subscribe(logout -> getNavigator().navigateToMain(this));

            getViewModel().getShowToastMessage()
                    .compose(bindToLifecycle())
                    .observeOn(getUiScheduler())
                    .subscribe(this::showToastMessage);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*if (id == R.id.action_search) {
            getNavigator().navigateToSearch(this);
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    protected void onSetContentView() {
        if (getLayoutResource() != 0) {
            setContentView(getLayoutResource());
        }
    }

    protected void onSetOrientation() {
        // Set the default orientation handler for most of the activities. This can be overridden if necessary.
        //setOrientationHandler(OrientationManager.getHandler(this, OrientationManager.DEFAULT_ORIENTATION_SCHEME));
    }

    protected void onBindView() {
        unbinder = ButterKnife.bind(this);
        Toolbar toolbar = getToolbar();
        if (toolbar != null){
            if (getToolbarTitle() != null) {
                toolbar.setTitle(getToolbarTitle());
            }
            setSupportActionBar(toolbar);
        }
    }

    protected void onInject() {}

    protected void onBindData(@Nullable View view) {}

    protected void onGetInputData() {}

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Tell the orientationHandler that the page is about to be shown.
        /*if (getOrientationHandler() != null) {
            getOrientationHandler().initializeOrientation();
        }*/
        if (getViewModel() != null) {
            getViewModel().onAttachView();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (getViewModel() != null) {
            getViewModel().onDetachView();
        }
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    @Override
    public void startActivity(Intent intent) {
        try {
            super.startActivity(intent);
            startActivityTransitions();
        } catch (NullPointerException npe) {

        }
    }

    protected void startActivityTransitions() {
        //getNavigator().showPendingTransitions(this);
    }

    @Override
    public void finish() {
        super.finish();
        finishActivityTransitions();
    }

    protected void finishActivityTransitions() {
        //getNavigator().showPendingTransitions(this);
    }

    protected void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void setToolbarTitle(String title) {
        /*Toolbar toolbar = getToolbar();
        if (toolbar != null && !TextUtils.isEmpty(title)){
            toolbar.setTitle(title);
        }*/
    }

    private void promptExitOrFinish() {
        final long currentElapsedTime = SystemClock.elapsedRealtime();
        if (currentElapsedTime < DOUBLE_BACK_TO_EXIT_DURATION + this.mBackButtonLastPressedElapsedTime) {
            finishAffinity();
            return;
        }

        this.mBackButtonLastPressedElapsedTime = currentElapsedTime;

        // TODO translate this string from backend
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show();
    }

    public boolean requireDoubleBackPressToFinishActivity() {
        // Default is false. Override this in child activity
        // to get different behavior
        return false;
    }

    @Override
    public void onBackPressed() {
        if (this.requireDoubleBackPressToFinishActivity()) {
            this.promptExitOrFinish();
        }
        else {
            super.onBackPressed();
        }
    }

    /*protected boolean isDeepLink(@NonNull Intent intent) {
        return intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false);
    }*/
}

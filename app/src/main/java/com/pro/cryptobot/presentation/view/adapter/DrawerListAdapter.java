package com.pro.cryptobot.presentation.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pro.cryptobot.R;
import com.pro.cryptobot.databinding.ListItemDrawerBinding;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyanoh on 06/10/2017.
 */

public class DrawerListAdapter extends RecyclerView.Adapter<DrawerListAdapter.ViewHolder> {

    private List<String> navigationTabs;
    private PublishSubject<String> selectedNavigationTab = PublishSubject.create();

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemDrawerBinding binding;

        public ViewHolder(View view) {
            super(view);
        }

        public ViewHolder(ListItemDrawerBinding binding) {
            this(binding.getRoot());
            this.binding = binding;

        }
    }

    public DrawerListAdapter(List<String> navigationTabs) {
        this.navigationTabs = navigationTabs;
    }

    public Observable<String> getSelectedNavigationTab() {
        return selectedNavigationTab;
    }

    @Override
    public DrawerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemDrawerBinding binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.list_item_drawer, parent, false);
        return new DrawerListAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final DrawerListAdapter.ViewHolder holder, int position) {

        holder.binding.setModel(navigationTabs.get(position));

        /*holder.binding.setSwitchOutListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos >= 0) {
                selectedSwitchDeviceId.onNext(registeredDevices.get(pos).getDeviceId());
            }
        });

        holder.binding.setLogoutListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos >= 0) {
                selectedLogoutDeviceId.onNext(registeredDevices.get(pos).getDeviceId());
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return navigationTabs.size();
    }
}

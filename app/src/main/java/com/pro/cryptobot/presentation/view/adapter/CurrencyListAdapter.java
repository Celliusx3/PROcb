package com.pro.cryptobot.presentation.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pro.cryptobot.R;
import com.pro.cryptobot.databinding.ListItemCryptocurrencyBinding;
import com.pro.cryptobot.interactor.model.CurrencyModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyanoh on 06/10/2017.
 */

public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.ViewHolder> {

    private List<CurrencyModel> registeredCurrency;
    private PublishSubject<String> selectedCurrency = PublishSubject.create();

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemCryptocurrencyBinding binding;

        public ViewHolder(View view) {
            super(view);
        }

        public ViewHolder(ListItemCryptocurrencyBinding binding) {
            this(binding.getRoot());
            this.binding = binding;

        }
    }

    public CurrencyListAdapter(List<CurrencyModel> registeredCurrency) {
        this.registeredCurrency = registeredCurrency;
    }

    public Observable<String> getSelectedCurrency() {
        return selectedCurrency;
    }

    @Override
    public CurrencyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemCryptocurrencyBinding binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.list_item_cryptocurrency, parent, false);
        return new CurrencyListAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final CurrencyListAdapter.ViewHolder holder, int position) {

        holder.binding.setModel(registeredCurrency.get(position));

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
        return registeredCurrency.size();
    }
}

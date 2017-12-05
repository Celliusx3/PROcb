package com.pro.cryptobot.interactor.model

import com.pro.cryptobot.data.entity.Currency

/**
 * Created by OCCOYANC on 12/5/2017.
 */
class CurrencyModel {

    var MYR: Double? = 0.0
    var USD: Double? = 0.0

    companion object Factory {
        @JvmStatic fun create(currency: Currency): CurrencyModel {
            var model = CurrencyModel()
            model.MYR = currency.MYR
            model.USD = currency.USD
            return model
        }
    }

}
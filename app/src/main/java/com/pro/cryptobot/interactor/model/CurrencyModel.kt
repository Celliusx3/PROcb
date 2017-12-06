package com.pro.cryptobot.interactor.model

import com.pro.cryptobot.data.entity.Currency

/**
 * Created by OCCOYANC on 12/5/2017.
 */
class CurrencyModel {

    var CurrencyString: String? = ""
    var CurrencyIn1: String? = ""
    var CurrencyIn2: String? = ""

    companion object Factory {
        @JvmStatic
        fun create(currency: Currency, currencyString: String, currencyInCurrency: Array<String>): CurrencyModel {
            var model = CurrencyModel()

            model.CurrencyString = currencyString

            model.CurrencyIn1 = CurrencyInCurrency(currency,currencyInCurrency[0])
            if (currencyInCurrency.size>=2) {
                model.CurrencyIn2 = CurrencyInCurrency(currency, currencyInCurrency[1])
            }
            return model
        }

        fun CurrencyInCurrency(currency:Currency, currencyString: String): String? {
            when (currencyString) {
                "EUR" -> return "EUR " + currency.EUR.toString()
                "USD" -> return "USD " + currency.USD.toString()
                "MYR" -> return "MYR " + currency.MYR.toString()
                else -> { // Note the block
                    return null
                }
            }
        }
    }
}
package com.pro.cryptobot.data.api;

import com.pro.cryptobot.BuildConfig;

/**
 * Created by coyanoh on 26/11/2017.
 */

public interface ApiRoutes {
    String LUNO_BASE_URL      = BuildConfig.LUNO_BASE_URL;
    String LUNO_MARKET_DATA   = "ticker";

    String XBIT_BASE_URL      = BuildConfig.XBITASIA_BASE_URL;
    String XBIT_MARKET_DATA   = "bestOffer";

    String CRYPTOCOMPARE_BASE_URL = BuildConfig.CRYPTOCOMPARE_BASE_URL;
    String PRICE = "price";
    String PRICE_MULTI = "pricemulti";
    String PRICE_MULTI_FULL = "pricemultifull";
    String GENERATE_AVERAGE = "generateAvg";
    String DAY_AVERAGE = "dayAvg";
    String PRICE_HISTORICAL = "pricehistorical";
    String COIN_SNAPSHOT = "coinsnapshot";
    String COIN_SNAPSHOT_FULL_BY_ID = "coinsnapshotfullbyid";
    String SOCIAL_STATS = "socialstats";
    String HISTO_MINUTE = "histominute";
    String HISTO_HOUR = "histohour";
    String HISTO_DAY = "histoday";
    String MINING_CONTRACT = "miningcontract";
    String MINING_EQUIPMENT = "miningequipment";
    String TOP_PAIRS = "top/pairs";

}

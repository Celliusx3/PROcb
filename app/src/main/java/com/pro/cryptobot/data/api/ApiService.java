package com.pro.cryptobot.data.api;

import com.pro.cryptobot.data.entity.response.GetPriceResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by coyanoh on 26/11/2017.
 */

public interface ApiService {

    @GET(ApiRoutes.PRICE)
    Observable<GetPriceResponse> getPrice(@Query("fsym") String fysm, @Query("tsyms") String tsyms,
                                          @Query("e") String e, @Query("extraParams") String extraParams);

}

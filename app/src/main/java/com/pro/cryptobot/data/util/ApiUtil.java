package com.pro.cryptobot.data.util;

import com.pro.cryptobot.data.entity.response.ApiResponse;

/**
 * Created by coyanoh on 27/11/2017.
 */

public class ApiUtil {

    public static <T> ApiResponse<T> checkApiException(ApiResponse<T> apiResponse) throws Exception {
        if (apiResponse.getResponseCode() != 200) {
            String message = apiResponse.getResponseMessage();
            if (message == null) {
                message = apiResponse.getErrorMessage();
            }
            if (message == null) {
                message = "Something went wrong! Please try again later!";
            }
            //throw new ApiException(apiResponse.getResponseCode(), message);
        }
        return apiResponse;
    }

    public static <T> T getResponse(ApiResponse<T> apiResponse) throws Exception {
        checkApiException(apiResponse);
        return apiResponse.getResponse();
    }
}

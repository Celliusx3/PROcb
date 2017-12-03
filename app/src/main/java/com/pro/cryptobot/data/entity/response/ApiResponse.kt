package com.pro.cryptobot.data.entity.response

/**
 * Created by coyanoh on 27/11/2017.
 */
class ApiResponse<T> {
    val responseCode: Int = 0
    val responseMessage: String? = null
    val responseDateTime: Long = 0
    val response: T? = null

    // to handle error with response like
    // {"errorMessage":"2017-08-24T09:43:00.691Z 95321bbd-88b0-11e7-bd57-f7624636bec6 Task timed out after 15.00 seconds"}
    val errorMessage: String? = null
}
package com.pro.cryptobot.data.entity.request

/**
 * Created by coyanoh on 26/11/2017.
 */

class GetPriceRequest {
    var fysm: String = ""
    var tsyms: String = ""
    var e: String = ""
    var extraParams: String ?= ""
    var sign: Boolean = false
    var tryConversion: Boolean = false
}
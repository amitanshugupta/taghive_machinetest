package com.taghive.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TickersModel : Serializable {

    @SerializedName("symbol")
    @Expose
    var symbol: String = ""

    @SerializedName("baseAsset")
    @Expose
    var baseAsset: String = ""

    @SerializedName("quoteAsset")
    @Expose
    var quoteAsset: String = ""

    @SerializedName("openPrice")
    @Expose
    var openPrice: String = ""

    @SerializedName("lowPrice")
    @Expose
    var lowPrice: String = ""

    @SerializedName("highPrice")
    @Expose
    var highPrice: String = ""

    @SerializedName("lastPrice")
    @Expose
    var lastPrice: String = ""

    @SerializedName("volume")
    @Expose
    var volume: String = ""

    @SerializedName("bidPrice")
    @Expose
    var bidPrice: String = ""

    @SerializedName("askPrice")
    @Expose
    var askPrice: String = ""

    var from: String = ""
}
package com.taghive.repo

import com.google.gson.Gson
import com.taghive.helpers.ResponseType
import com.taghive.models.TickersModel
import com.taghive.networkInterfaces.RetrofitClient
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Repo {

    companion object {
        @Volatile
        private var INSTANCE: Repo? = null
        fun getInstance(): Repo =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Repo().also {
                    INSTANCE = it
                }
            }
    }

    fun getTickers(onResult: (status: Boolean, responseType: ResponseType, searchList: List<TickersModel>) -> Unit) {
        RetrofitClient.getAPIService().tickers.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    val tickersList = ArrayList<TickersModel>()
                    val jsonArray = JSONArray(response.body()!!.string())
                    if (jsonArray.length() > 0) {
                        if (jsonArray.length() > 0) {
                            for (i in 0 until jsonArray.length()) {
                                val jsonObject2 = jsonArray.getJSONObject(i)
                                val searchModel = Gson().fromJson(
                                    jsonObject2.toString(), TickersModel::class.java
                                )
                                tickersList.add(searchModel)
                            }
                        }
                        onResult(true, ResponseType.DATA_FOUND, tickersList)
                    } else {
                        onResult(false, ResponseType.NO_DATA, tickersList)
                    }
                } catch (er: java.lang.Exception) {
                    onResult(false, ResponseType.EXCEPTION, emptyList())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                onResult(false, ResponseType.SERVER_ERROR, emptyList())
            }
        })
    }
}
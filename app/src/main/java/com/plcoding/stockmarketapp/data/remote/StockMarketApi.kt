package com.plcoding.stockmarketapp.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface StockMarketApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getCompanyList(
            @Query("apiKey") apiKey : String = API_KEY
    ) : ResponseBody

    companion object {
        const val API_KEY = "ZGZ4FJ5XOBQWLQL1"
        const val BASE_URL = "https://alphavantage.co"
    }

}
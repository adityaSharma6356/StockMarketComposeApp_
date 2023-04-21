package com.plcoding.stockmarketapp.data.remote

import com.plcoding.stockmarketapp.data.remote.dto.CompanyInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming


interface StockMarketApi {

    @Streaming
    @GET("query?function=LISTING_STATUS")
    suspend fun getCompanyList(
            @Query("apikey") apiKey : String = API_KEY
    ) : ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY_2
    ): ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY_2
    ): CompanyInfoDto

    companion object {
        const val API_KEY = "ZGZ4FJ5XOBQWLQL1"
        const val API_KEY_2 = "1GDIXX1SVNBNL9R5"
        const val BASE_URL = "https://alphavantage.co"
    }
}
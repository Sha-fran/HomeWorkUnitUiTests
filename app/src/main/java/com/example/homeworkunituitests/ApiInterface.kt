package com.example.homeworkunituitests

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/v2/rates/bitcoin")
    suspend fun getCryptoByName(): BitcoinResponse
}

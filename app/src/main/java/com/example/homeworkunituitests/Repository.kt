package com.example.homeworkunituitests

import retrofit2.Response

class Repository(private val client:ApiClient) {
    private val apiInterface = client.client.create(ApiInterface::class.java)

    suspend fun getCurrencyByName(name:String): Response<BitcoinResponse> = apiInterface.getCryptoByName(name)
}

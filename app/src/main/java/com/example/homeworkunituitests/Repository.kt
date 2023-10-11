package com.example.homeworkunituitests

class Repository(client:ApiClient) {
    private val apiInterface = client.client.create(ApiInterface::class.java)

    suspend fun getCurrencyByName(): BitcoinResponse = apiInterface.getCryptoByName()
}

package com.radiusagent.facilitypicker.data.remote

import com.radiusagent.facilitypicker.data.remote.api.ApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://my-json-server.typicode.com/iranjith4/"

    fun create(): ApiService {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()

        return retrofit.create(ApiService::class.java)
    }
}
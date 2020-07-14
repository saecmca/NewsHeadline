package com.example.news

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AppClient {
    // todo verify singleton logic, if needed add otherwise ignore for getting same client everytime
    companion object {
        val BASE_URL = "https://newsapi.org/v2/"

        fun getApiClient(): ApiInterface {
            val httpInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)


            val builder = OkHttpClient.Builder()
            builder
                .addInterceptor(httpInterceptor)

            val okhttpClient = builder.build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiInterface = retrofit.create(ApiInterface::class.java)
            return apiInterface
        }
    }
}

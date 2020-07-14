package com.example.news

import retrofit2.Call
import retrofit2.http.GET
interface ApiInterface {
    @GET("top-headlines?country=us&apiKey=6243f746838743039af6d5de807525db")
    fun getCurrentNews(): Call<NewsResponse>
}
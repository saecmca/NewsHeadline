package com.example.news

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsModel(val listener: CallBackResult) {
    fun getVersionDetails() {

        val appInterface: ApiInterface = AppClient.getApiClient()
        val call : Call<NewsResponse> = appInterface.getCurrentNews()
        call.enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>?, t: Throwable?) {
                listener.onFailure(t?.message!!)
            }
            override fun onResponse(call: Call<NewsResponse>?, response: Response<NewsResponse>?) {
                val respData: NewsResponse? = response!!.body()
                if (response.isSuccessful) {
                    listener.onSuccess(respData)
                } else {
                    listener.onFailure("Please try again after sometime")
                }
            }
        })
    }
}

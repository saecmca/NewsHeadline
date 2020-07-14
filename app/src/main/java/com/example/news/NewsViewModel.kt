package com.example.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel() : ViewModel(), CallBackResult {

    var versionData: MutableLiveData<NewsResponse> = MutableLiveData()

    var versionRepo: NewsModel
    var isApiError: MutableLiveData<String> = MutableLiveData()

    init {
        versionRepo = NewsModel(this)
    }

    override fun onFailure(e: String) {
        isApiError.postValue(e)
    }

    override fun <T> onSuccess(result: T) {
        versionData.postValue(result as NewsResponse)
    }

    fun getVersionDetails() {
        versionRepo.getVersionDetails()
    }

    fun getLiveVersionDetails(): MutableLiveData<NewsResponse> {
        getVersionDetails()
        return versionData
    }

    fun getApiErrorAlert(): MutableLiveData<String> {
        return isApiError
    }
}



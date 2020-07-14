package com.example.news

interface CallBackResult {
    fun <T> onSuccess(result: T)
    fun onFailure(e: String)
}

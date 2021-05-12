package com.submission.movieandtvshow.webapi

interface CallbackInterfaces<T> {
    fun onSuccess(item: T)
    fun onFailure(throwable: Throwable)
}
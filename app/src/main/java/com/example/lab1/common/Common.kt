package com.example.lab1.common

import com.example.lab1.retrofit.RetrofitClient
import com.example.lab1.service.RetrofitService

object Common {
    private const val BASE_URL = "https://develtop.ru/study/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}
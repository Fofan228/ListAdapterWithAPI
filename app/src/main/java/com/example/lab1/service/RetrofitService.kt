package com.example.lab1.service

import com.example.lab1.request.CardRequest
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("new_text.json")
    fun getCardList(): Call<MutableList<CardRequest>>
}
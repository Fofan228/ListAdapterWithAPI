package com.example.lab1.request

data class CardRequest(
    val title: String,
    val subtitle: String,
    val isCircle: Boolean?,
    val hasBag: String?,
    val img: String?
)
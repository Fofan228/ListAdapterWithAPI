package com.example.lab1.model

sealed class Card(
    open val title: String? = null,
    open val subtitle: String? = null
){
    data class CardEmpty(
        override val title: String,
        override val subtitle: String
    ): Card(title, subtitle)

    data class CardWithCollapsedImg(
        val isCircle: Boolean?,
        val img: String?,

        override val title: String,
        override val subtitle: String
    ): Card(title, subtitle)

    data class CardWithColor(
        val img: String?,
        val hasBag: String?,

        override val title: String,
        override val subtitle: String
    ): Card(title, subtitle)

    data class CardWithImg(
        val img: String?,

        override val title: String,
        override val subtitle: String
    ): Card(title, subtitle)
}
package com.example.lab1.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab1.model.Card
import com.example.lab1.request.CardRequest

class CardVm : ViewModel() {
    private val cardLiveData: MutableLiveData<List<Card>> = MutableLiveData<List<Card>>()

    fun getCardLiveData(): LiveData<List<Card>> {
        return cardLiveData
    }

    fun setData(data: List<CardRequest>) {
        val cards = mutableListOf<Card>()

        for (card in data) {
            when (getCardType(card)) {
                TYPE_CARD_IMG -> {
                    cards += Card.CardWithImg(card.img!!, card.title, card.subtitle)
                }
                TYPE_CARD_COLLAPSED -> {
                    cards += Card.CardWithCollapsedImg(
                        card.isCircle!!,
                        card.img!!,
                        card.title,
                        card.subtitle
                    )
                }
                TYPE_CARD_COLOR -> {
                    cards += Card.CardWithColor(
                        card.img!!,
                        card.hasBag!!,
                        card.title,
                        card.subtitle
                    )
                }
                TYPE_CARD_EMPTY -> {
                    cards += Card.CardEmpty(card.title, card.subtitle)
                }
            }
        }

        cardLiveData.value = cards
    }

    private fun getCardType(request: CardRequest): Int {
        if (request.isCircle != null) {
            return TYPE_CARD_COLLAPSED
        }
        if (request.hasBag != null) {
            return TYPE_CARD_COLOR
        }
        if (request.img != null) {
            return TYPE_CARD_IMG
        }
        return TYPE_CARD_EMPTY
    }

    companion object {
        private const val TYPE_CARD_EMPTY = 0
        private const val TYPE_CARD_IMG = 1
        private const val TYPE_CARD_COLOR = 2
        private const val TYPE_CARD_COLLAPSED = 3
    }
}
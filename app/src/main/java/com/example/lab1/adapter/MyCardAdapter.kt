package com.example.lab1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.lab1.R
import com.example.lab1.model.Card


class MyCardAdapter :
    ListAdapter<Card, MyCardAdapterViewHolder>(CardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCardAdapterViewHolder {
        val layout = when (viewType) {
            TYPE_CARD_EMPTY -> R.layout.card_empty_layout
            TYPE_CARD_IMG -> R.layout.card_with_img
            TYPE_CARD_COLLAPSED -> R.layout.card_collapsed_layout
            TYPE_CARD_COLOR -> R.layout.card_with_color
            else -> throw IllegalArgumentException("Invalid type")
        }

        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)

        return MyCardAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyCardAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Card.CardEmpty -> TYPE_CARD_EMPTY
            is Card.CardWithImg -> TYPE_CARD_IMG
            is Card.CardWithCollapsedImg -> TYPE_CARD_COLLAPSED
            else -> TYPE_CARD_COLOR
        }
    }

    class CardDiffCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        private const val TYPE_CARD_EMPTY = 0
        private const val TYPE_CARD_IMG = 1
        private const val TYPE_CARD_COLOR = 2
        private const val TYPE_CARD_COLLAPSED = 3
    }
}
package com.example.lab1.adapter

import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.R
import com.example.lab1.model.Card
import com.example.lab1.model.Card.*
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

class MyCardAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private fun bindEmpty(card: CardEmpty) {
        itemView.findViewById<AppCompatTextView>(R.id.header)?.text = card.title
        itemView.findViewById<AppCompatTextView>(R.id.subheader)?.text = card.subtitle
    }

    private fun bindWithColor(card: CardWithColor) {
        itemView.findViewById<ConstraintLayout>(R.id.container_text)
            ?.setBackgroundColor(Color.parseColor(card.hasBag))
        itemView.findViewById<AppCompatTextView>(R.id.header)?.text = card.title
        itemView.findViewById<AppCompatTextView>(R.id.sub_header)?.text = card.subtitle
        Picasso.get().load(card.img)
            .placeholder(R.drawable.animeted_loading)
            .error(R.drawable.ic_broken_image)
            .into(itemView.findViewById<AppCompatImageView>(R.id.image))
    }

    private fun bindWithImg(card: CardWithImg) {
        itemView.findViewById<AppCompatTextView>(R.id.header)?.text = card.title
        itemView.findViewById<AppCompatTextView>(R.id.sub_header)?.text = card.subtitle
        Picasso.get().load(card.img)
            .placeholder(R.drawable.animeted_loading)
            .error(R.drawable.ic_broken_image)
            .into(itemView.findViewById<AppCompatImageView>(R.id.image))
    }

    private fun bindWithCollapsedImg(card: CardWithCollapsedImg) {
        itemView.findViewById<AppCompatTextView>(R.id.header)?.text = card.title
        itemView.findViewById<AppCompatTextView>(R.id.subhead)?.text = card.subtitle
        Picasso.get().load(card.img)
            .placeholder(R.drawable.animeted_loading)
            .error(R.drawable.ic_broken_image)
            .into(itemView.findViewById<RoundedImageView>(R.id.imageView))
    }

    fun bind(card: Card) {
        when (card) {
            is CardEmpty -> bindEmpty(card)
            is CardWithColor -> bindWithColor(card)
            is CardWithImg -> bindWithImg(card)
            is CardWithCollapsedImg -> bindWithCollapsedImg(card)
        }
    }
}
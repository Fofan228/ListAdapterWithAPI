package com.example.lab1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.R
import com.example.lab1.model.Card
import com.example.lab1.request.CardRequest

class MyCardAdapter(private val cardList: MutableList<CardRequest>):
    RecyclerView.Adapter<MyCardAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.title)
        val subTitle: TextView = itemView.findViewById(R.id.subtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = cardList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = cardList[position].title
        holder.subTitle.text = cardList[position].subtitle
    }
}
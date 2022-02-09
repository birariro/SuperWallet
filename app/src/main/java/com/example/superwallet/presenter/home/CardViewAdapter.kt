package com.example.superwallet.presenter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.superwallet.R

class CardViewAdapter : RecyclerView.Adapter<CardViewAdapter.ViewHolder>(){

    var itemClick : ((Int)-> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var card_image: ImageView = itemView.findViewById(R.id.card_image)
        public var card_title: TextView = itemView.findViewById(R.id.card_title)
        public var card_code: TextView = itemView.findViewById(R.id.card_code)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.home_card_view, parent,false)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.card_title.text = "테스트 타이틀"
        holder.card_code.text = "테스트 코드"
        holder.itemView.setOnClickListener {
            this.itemClick?.invoke(position)
        }

    }

    override fun getItemCount(): Int {
        return 1
    }
}
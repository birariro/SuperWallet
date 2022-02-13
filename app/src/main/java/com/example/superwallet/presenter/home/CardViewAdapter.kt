package com.example.superwallet.presenter.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.superwallet.R
import com.example.superwallet.domain.model.CardData

class CardViewAdapter : RecyclerView.Adapter<CardViewAdapter.ViewHolder>(){

    private var cardDataList = listOf<CardData>()
    var itemShowClick : ((CardData)-> Unit)? = null
    var itemEditClick : ((CardData)-> Unit)? = null
    var itemDeleteClick : ((CardData)-> Unit)? = null
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var card_image: ImageView = itemView.findViewById(R.id.card_image)
        public var card_title: TextView = itemView.findViewById(R.id.card_title)
        public var card_code: TextView = itemView.findViewById(R.id.card_code)
        public var edit_image: ImageView = itemView.findViewById(R.id.edit_image)
        public var delete_image: ImageView = itemView.findViewById(R.id.delete_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.home_card_view, parent,false)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.card_title.text = cardDataList[position].cardTitle
        holder.card_code.text = cardDataList[position].cardCode
        holder.itemView.setOnClickListener {
            this.itemShowClick?.invoke(cardDataList[position])
        }
        holder.edit_image.setOnClickListener {
            this.itemEditClick?.invoke(cardDataList[position])
        }
        holder.delete_image.setOnClickListener {
            this.itemDeleteClick?.invoke(cardDataList[position])
        }

    }

    override fun getItemCount(): Int {
        return cardDataList.size
    }
    fun setData(cardDataList : List<CardData>){
        this.cardDataList = cardDataList
        notifyDataSetChanged()
    }
}
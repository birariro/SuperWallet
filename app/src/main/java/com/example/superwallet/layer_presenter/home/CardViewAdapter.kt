package com.example.superwallet.layer_presenter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.superwallet.R
import com.example.superwallet.layer_domain.model.CardData
import com.example.superwallet.layer_domain.model.CardType

class CardViewAdapter : RecyclerView.Adapter<CardViewAdapter.ViewHolder>(){

    private var cardDataList = listOf<CardData>()
    var itemShowClick : ((CardData)-> Unit)? = null
    var itemEditClick : ((CardData)-> Unit)? = null
    var itemDeleteClick : ((CardData)-> Unit)? = null
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var card_image: ImageView = itemView.findViewById(R.id.card_image)
        var card_title: TextView = itemView.findViewById(R.id.card_title)
        var card_code: TextView = itemView.findViewById(R.id.card_code)
        var edit_image: ImageView = itemView.findViewById(R.id.edit_image)
        var delete_image: ImageView = itemView.findViewById(R.id.delete_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.home_card_view, parent,false)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(cardDataList[position].cardType){
            CardType.QR -> holder.card_image.setImageResource(R.drawable.icon_card_qr)
            CardType.BARCODE -> holder.card_image.setImageResource(R.drawable.icon_card_barcode)
            else -> holder.card_image.setImageResource(R.drawable.icon_card_barcode)
        }
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
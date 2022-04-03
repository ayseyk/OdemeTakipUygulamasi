package com.example.odemetakip.BLL.RCV

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.odemetakip.Model.OdemeKaydi
import com.example.odemetakip.R

class OdemeKaydiViewHolder(itemView : View, itemClick : (position : Int)->Unit) : RecyclerView.ViewHolder(itemView) {
    var tvTutar : TextView
    var tvTarih : TextView

    init {
        tvTutar = itemView.findViewById(R.id.tvPeriyot)
        tvTarih = itemView.findViewById(R.id.tvBaslik)


        itemView.setOnClickListener { itemClick(adapterPosition) }
    }

    fun bindData(context : Context, odemeKaydi : OdemeKaydi)
    {
        tvTutar.text = odemeKaydi.Tutar.toString()
        tvTarih.text = odemeKaydi.Tarih
    }

}
package com.example.odemetakip.BLL.RCV

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.odemetakip.BLL.OdemeTipiLogic
import com.example.odemetakip.Model.OdemeKaydi
import com.example.odemetakip.R

class OdemeKaydiViewHolder(itemView : View, itemClick : (position : Int)->Unit) : RecyclerView.ViewHolder(itemView) {
    var tvTutar : TextView

    init {
        tvTutar = itemView.findViewById(R.id.tvPeriyot)

        itemView.setOnClickListener { itemClick(adapterPosition) }
    }

    @SuppressLint("SetTextI18n")
    fun bindData(context : Context, odemeKaydi : OdemeKaydi)
    {
        var id = odemeKaydi.OdemeTipi
        var k = OdemeTipiLogic.idIleGetir(context,id)
        tvTutar.text = "${odemeKaydi.Tarih} Tarihinde ${odemeKaydi.Tutar
            .toString()} ₺ ödeme yapılmıştır."

    }

}
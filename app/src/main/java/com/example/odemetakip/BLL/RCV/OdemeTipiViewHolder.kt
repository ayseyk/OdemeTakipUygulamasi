package com.example.odemetakip.BLL.RCV

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.odemetakip.Model.OdemeTipi
import com.example.odemetakip.R

class OdemeTipiViewHolder(itemView : View, itemClick : (position : Int)->Unit, btnKayitEkleClick
: (position : Int)->Unit) : RecyclerView.ViewHolder(itemView) {

    var tvBaslik : TextView
    var tvPeriyot : TextView
    var tvPeriyotGunu : TextView
    var btnOdemeEkle : Button


    init {
        tvBaslik = itemView.findViewById(R.id.tvBaslik)
        tvPeriyot = itemView.findViewById(R.id.tvPeriyot)
        tvPeriyotGunu = itemView.findViewById(R.id.tvPeriyotGunu)
        btnOdemeEkle = itemView.findViewById(R.id.btnOdemeEkleAna)

        itemView.setOnClickListener { itemClick(adapterPosition) }

        btnOdemeEkle.setOnClickListener { btnKayitEkleClick(adapterPosition) }
    }

    fun bindData(context : Context, odemeTipi : OdemeTipi)
    {
        tvBaslik.text = odemeTipi.Baslik
        tvPeriyot.text = odemeTipi.Periyot
        tvPeriyotGunu.text = "${odemeTipi.PeriyotGunu.toString()}. gün"
       /* if(odemeTipi.Periyot != null)
        {
            tvPeriyot.text = odemeTipi.Periyot
        }
        else {
            tvPeriyot.text = ""
        }
        if(odemeTipi.PeriyotGunu !=null)
        {
            tvPeriyotGunu.text = odemeTipi.PeriyotGunu.toString()
        }
        else{
            tvPeriyotGunu.text = ""
        }*/

    }
}




package com.example.odemetakip.BLL.RCV

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.odemetakip.Model.OdemeTipi
import com.example.odemetakip.R

class OdemeTipiAdapter(val context : Context, var liste : ArrayList<OdemeTipi>, val itemClick :
    (position : Int)->Unit, val btnKayitEkleClick: (position : Int)->Unit) : RecyclerView
.Adapter<OdemeTipiViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OdemeTipiViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.rcv_odeme_tip, parent, false)

        return OdemeTipiViewHolder(v, itemClick,btnKayitEkleClick)
    }

    override fun onBindViewHolder(holder: OdemeTipiViewHolder, position: Int) {
        holder.bindData(context, liste.get(position))
    }

    override fun getItemCount(): Int {
        return liste.size
    }

}
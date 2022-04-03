package com.example.odemetakip.BLL.RCV

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.odemetakip.Model.OdemeKaydi
import com.example.odemetakip.R

class OdemeKaydiAdapter(val context : Context, var liste : ArrayList<OdemeKaydi>, val itemClick :
    (position : Int)->Unit) : RecyclerView.Adapter<OdemeKaydiViewHolder> ()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OdemeKaydiViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.rcv_odeme_kayit, parent, false)

        return OdemeKaydiViewHolder(v, itemClick)
    }

    override fun onBindViewHolder(holder: OdemeKaydiViewHolder, position: Int) {
        holder.bindData(context, liste.get(position))
    }

    override fun getItemCount(): Int {
        return liste.size
    }


}
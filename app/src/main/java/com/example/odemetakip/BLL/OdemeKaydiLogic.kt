package com.example.odemetakip.BLL

import android.content.Context
import com.example.odemetakip.DAL.OdemeKaydiOperation
import com.example.odemetakip.DAL.OdemeTipiOperation
import com.example.odemetakip.Model.OdemeKaydi
import com.example.odemetakip.Model.OdemeTipi

class OdemeKaydiLogic {
    companion object
    {
        fun ekle(context : Context, odemeKaydi : OdemeKaydi)
        {
            if(odemeKaydi != null){
                val yo = OdemeKaydiOperation(context)
                yo.odemeKaydiEkle(odemeKaydi)
            }
        }
        fun sil(context : Context, odemeKaydi : OdemeKaydi)
        {
            val yo = OdemeKaydiOperation(context)
            yo.odemeKaydiSil(odemeKaydi)
        }
        fun tumOdemeKayitlariniGetir(context : Context) : ArrayList<OdemeKaydi>
        {
            return OdemeKaydiOperation(context).odemeKaydiGetir()
        }
    }
}
package com.example.odemetakip.BLL

import android.content.Context
import android.widget.Toast
import com.example.odemetakip.DAL.OdemeKaydiOperation
import com.example.odemetakip.DAL.OdemeTipiOperation
import com.example.odemetakip.Model.OdemeKaydi
import com.example.odemetakip.Model.OdemeTipi

class OdemeKaydiLogic {
    companion object
    {
        fun ekle(context : Context, odemeKaydi : OdemeKaydi)
        {
            val yo = OdemeKaydiOperation(context)
            yo.odemeKaydiEkle(odemeKaydi)
        }
        fun sil(context : Context, odemeKaydi : OdemeKaydi)
        {
            val yo = OdemeKaydiOperation(context)
            yo.odemeKaydiSil(odemeKaydi)
        }
        fun tumOdemeKayitlariniGetir(context : Context, id : Int) : ArrayList<OdemeKaydi>
        {
            var oKaydiList = OdemeKaydiOperation(context).odemeKaydiGetir(id)
            Toast.makeText(context,id.toString(),Toast.LENGTH_LONG).show()
            return oKaydiList
        }
       /* fun idIleGetir(context : Context,id : Int) : OdemeKaydi?{
            val yo = OdemeKaydiOperation(context)
            var odemeKaydi =yo.odemeKaydiIdGetir(id)
            return odemeKaydi
        }*/
    }
}
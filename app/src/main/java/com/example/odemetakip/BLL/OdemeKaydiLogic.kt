package com.example.odemetakip.BLL

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
            return oKaydiList
        }
        fun tumOdemeKaydiSilId (context: Context, id: Int){
            val y0 = OdemeKaydiOperation(context)
            y0.tumOdemeKaydiSilId(id)
        }
        fun hataGoster(context : Context,msg : String){
            val adb : AlertDialog.Builder = AlertDialog.Builder(context)
            adb.setTitle("Tarih Seçilemedi!").setMessage(msg)
                .setPositiveButton("Tamam",null).show()
        }
    }
}
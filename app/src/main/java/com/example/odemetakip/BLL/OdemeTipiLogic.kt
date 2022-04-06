package com.example.odemetakip.BLL

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.odemetakip.DAL.OdemeTipiOperation
import com.example.odemetakip.Model.OdemeTipi
import com.example.odemetakip.R

class OdemeTipiLogic {
    companion object
    {
        fun ekle(context : Context, odemeTipi : OdemeTipi)
        {
            val yo = OdemeTipiOperation(context)
            yo.odemeTipiEkle(odemeTipi)
        }

        fun tumOdemeTipleriGetir(context : Context) : ArrayList<OdemeTipi>
        {
            return OdemeTipiOperation(context).odemeTipiGetir()
        }
        fun guncelle(context : Context,id:Int,odemeTipi : OdemeTipi) :OdemeTipi
        {
            val yo = OdemeTipiOperation(context)
            var odemeTipi = yo.odemeTipiGuncelleId(id, odemeTipi)
            return odemeTipi
        }
        fun sil(context : Context,id : Int){
            val yo = OdemeTipiOperation(context)
            yo.odemeTipiSil(id)
        }
        fun idIleGetir(context : Context,id : Int) : OdemeTipi?{
            val yo = OdemeTipiOperation(context)
            var odemeTipi =yo.odemeTipIdGetir(id)
            return odemeTipi
        }
        fun periyotGirisKontrol(odemeTipi : OdemeTipi, periyot: String):Boolean{
            var hataYok = true
            if(odemeTipi.PeriyotGunu == null) return hataYok
            if(periyot == "Haftalık" ){
                hataYok = (odemeTipi.PeriyotGunu!! in 1..7)
            }else if(periyot == "Aylık") {
                hataYok = (odemeTipi.PeriyotGunu!! in 1..31)
            }else if (periyot == "Yıllık") {
                hataYok = (odemeTipi.PeriyotGunu!! in 1..12)
            }
            return hataYok

        }
        fun hataGoster(context : Context,msg : String){
            val adb : AlertDialog.Builder = AlertDialog.Builder(context)
            adb.setTitle("Kayıt Yapılamadı!").setMessage(msg)
                .setPositiveButton("Tamam",null).show()
        }
        fun baslikIleGetir(context : Context,baslik : String) : OdemeTipi?{
            val yo = OdemeTipiOperation(context)
            var odemeTipi =yo.baslikIleGetir(baslik)
            return odemeTipi
        }
        fun baslikKontrol(context : Context,odemeTipi : OdemeTipi) : Boolean{
            if(baslikIleGetir(context,odemeTipi.Baslik)?.Baslik == odemeTipi.Baslik ){
                hataGoster(context,"Girdiğiniz ödeme tipinde daha önceden kayıt yapılmıştır. Yeni" +
                        " bir başlık giriniz.")
                return false
            }
            return true
        }
    }

}
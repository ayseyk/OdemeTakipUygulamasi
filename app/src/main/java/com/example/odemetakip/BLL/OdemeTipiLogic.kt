package com.example.odemetakip.BLL

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.odemetakip.DAL.OdemeTipiOperation
import com.example.odemetakip.Model.OdemeTipi

class OdemeTipiLogic {
    companion object
    {
        fun ekle(context : Context, odemeTipi : OdemeTipi) : Boolean
        {
            var hataVar = false
            val yo = OdemeTipiOperation(context)
            var hata = yo.odemeTipiEkle(odemeTipi)

            hataVar = hata!=null
            return hataVar
        }
        fun hataGoster(context : Context){
            val adb : AlertDialog.Builder = AlertDialog.Builder(context)
            adb.setTitle("Kayıt Yapılamadı!").setMessage("Girdiğiniz verileri kontrol ediniz.")
                .setPositiveButton("Tamam",null).show()
        }

        fun tumOdemeTipleriGetir(context : Context) : ArrayList<OdemeTipi>
        {
            return OdemeTipiOperation(context).odemeTipiGetir()
        }
        fun guncelle(context : Context,id:Int,odemeTipi : OdemeTipi) :OdemeTipi
        {
            val yo = OdemeTipiOperation(context)
            var odemeTipi =yo.odemeTipiGuncelleId(id, odemeTipi)
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
    }

}
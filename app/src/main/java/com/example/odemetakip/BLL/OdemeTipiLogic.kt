package com.example.odemetakip.BLL

import android.content.Context
import com.example.odemetakip.DAL.OdemeTipiOperation
import com.example.odemetakip.Model.OdemeTipi

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
    }
}
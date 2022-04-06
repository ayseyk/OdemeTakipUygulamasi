package com.example.odemetakip.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import com.example.odemetakip.Model.OdemeTipi


class OdemeTipiOperation (context: Context) {
    var hata : String? = null
    var dbOpenHelper: DatabaseOpenHelper
    var OdemeTakipDatabase: SQLiteDatabase? = null
    init {
        dbOpenHelper = DatabaseOpenHelper(context, "OdemeTakipDb", null, 1)
    }

    fun open() {
        OdemeTakipDatabase = dbOpenHelper.writableDatabase
    }
    fun close() {
        if (OdemeTakipDatabase != null && OdemeTakipDatabase!!.isOpen) {
            OdemeTakipDatabase!!.close()
        }
    }
    fun odemeTipiEkle(odemeTipi : OdemeTipi) : String?  {
        val cv = ContentValues()
        cv.put("Baslik", odemeTipi.Baslik)
        cv.put("Periyot", odemeTipi.Periyot)
        cv.put("PeriyotGunu", odemeTipi.PeriyotGunu)
        open()
        OdemeTakipDatabase!!.insert("OdemeTipi", null, cv)
        close()

        return hata
    }
    fun odemeTipiSil(id : Int) {
        open()
        OdemeTakipDatabase!!.delete("OdemeTipi", "Id = ?", arrayOf(id.toString()))
        close()
    }
    private fun tumOdemeTipleriGetir() : Cursor
    {
        val sorgu = "Select * from OdemeTipi"

        return OdemeTakipDatabase!!.rawQuery(sorgu, null)
    }

    private fun odemeTipiGetirId(id : Int) : Cursor
    {
        val sorgu = "Select * from OdemeTipi Where Id = ?"

        return OdemeTakipDatabase!!.rawQuery(sorgu, arrayOf(id.toString()))
    }
    private fun baslikGetir(baslik:String) : Cursor
    {
        val sorgu = "Select * from OdemeTipi Where Baslik = ?"

        return OdemeTakipDatabase!!.rawQuery(sorgu, arrayOf(baslik))
    }

    @SuppressLint("Range")
    fun odemeTipiGetir() : ArrayList<OdemeTipi>
    {
        val oTipiList = ArrayList<OdemeTipi>()
        var odemeTipi : OdemeTipi

        open()
        var c : Cursor = tumOdemeTipleriGetir()

        if (c.moveToFirst())
        {
            do {
                odemeTipi = OdemeTipi()
                odemeTipi.Id = c.getInt(0)//(c.getColumnIndex("Id"))
                odemeTipi.Baslik = c.getString(c.getColumnIndex("Baslik"))
                odemeTipi.Periyot= c.getString(c.getColumnIndex("Periyot"))
                odemeTipi.PeriyotGunu= c.getInt(c.getColumnIndex("PeriyotGunu"))
                oTipiList.add(odemeTipi)
            }while (c.moveToNext())
        }
        close()
        return oTipiList
    }
    fun odemeTipiGuncelleId(id : Int, odemeTipi: OdemeTipi): OdemeTipi {
        val cv = ContentValues()
        cv.put("Baslik", odemeTipi.Baslik)
        cv.put("Periyot", odemeTipi.Periyot)
        cv.put("PeriyotGunu", odemeTipi.PeriyotGunu)

        open()
        OdemeTakipDatabase!!.update("OdemeTipi", cv, "Id = ?", arrayOf(id.toString()))
        close()
        return odemeTipi
    }

    @SuppressLint("Range")
    fun odemeTipIdGetir(id : Int) : OdemeTipi?
    {
        var odemeTipi : OdemeTipi? = null

        open()
        var c : Cursor = odemeTipiGetirId(id)

        if (c.moveToFirst())
        {
            odemeTipi = OdemeTipi()
            odemeTipi.Id = c.getColumnIndex("Id")
            odemeTipi.Baslik = c.getString(c.getColumnIndex("Baslik"))
            odemeTipi.Periyot= c.getString(c.getColumnIndex("Periyot"))
            odemeTipi.PeriyotGunu= c.getInt(c.getColumnIndex("PeriyotGunu"))

        }
        close()
        return odemeTipi
    }
    @SuppressLint("Range")
    fun baslikIleGetir(baslik: String) : OdemeTipi?
    {
        var odemeTipi : OdemeTipi? = null

        open()
        var c : Cursor = baslikGetir(baslik)

        if (c.moveToFirst())
        {
            odemeTipi = OdemeTipi()
            odemeTipi.Id = c.getColumnIndex("Id")
            odemeTipi.Baslik = c.getString(c.getColumnIndex("Baslik"))
            odemeTipi.Periyot= c.getString(c.getColumnIndex("Periyot"))
            odemeTipi.PeriyotGunu= c.getInt(c.getColumnIndex("PeriyotGunu"))

        }
        close()
        return odemeTipi
    }

}
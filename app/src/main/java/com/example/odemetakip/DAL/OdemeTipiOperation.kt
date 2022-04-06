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
        try {
             OdemeTakipDatabase!!.insert("OdemeTipi", null, cv)
        }
        catch (e: SQLiteConstraintException){
            hata = "Bu ödeme tipinde başka bir kaydınız bulunmaktadır."
        }
        catch (e:SQLException){
            hata =  "SQLiteException: ${e.message}"
        }
        finally {
            close()
        }
        return hata

    }

    fun odemeTipiGuncelle(odemeTipi : OdemeTipi) {
        val cv = ContentValues()
        cv.put("Baslik", odemeTipi.Baslik) //sutun isimleri
        cv.put("Periyot", odemeTipi.Periyot)
        cv.put("PeriyotGunu", odemeTipi.PeriyotGunu)

        open()
        OdemeTakipDatabase!!.update("OdemeTipi", cv, "Id = ?", arrayOf(odemeTipi.Id.toString()))
        close()
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

    private fun tumOdemeTipleriGetirId(id : Int) : Cursor
    {
        val sorgu = "Select * from OdemeTipi Where Id = ?"

        return OdemeTakipDatabase!!.rawQuery(sorgu, arrayOf(id.toString()))
    }

    private fun tumOdemeTipleriGetirBaslik(baslik : String) : Cursor
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
        cv.put("Baslik", odemeTipi.Baslik) //sutun isimleri
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
        val oTipiList = ArrayList<OdemeTipi>()
        var odemeTipi : OdemeTipi? = null

        open()
        var c : Cursor = tumOdemeTipleriGetirId(id)

        if (c.moveToFirst())
        {
            do {
                odemeTipi = OdemeTipi()
                odemeTipi.Id = c.getColumnIndex("Id")
                odemeTipi.Baslik = c.getString(c.getColumnIndex("Baslik"))
                odemeTipi.Periyot= c.getString(c.getColumnIndex("Periyot"))
                odemeTipi.PeriyotGunu= c.getInt(c.getColumnIndex("PeriyotGunu"))
                oTipiList.add(odemeTipi)
            }while (c.moveToNext())
        }

        close()
        return odemeTipi
    }
    @SuppressLint("Range")
    fun odemeTipiBasliklariGetir() : ArrayList<String>
    {
        val oTipiBasliklariList = ArrayList<String>()
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
                oTipiBasliklariList.add(odemeTipi.Baslik)
            }while (c.moveToNext())
        }

        close()
        return oTipiBasliklariList
    }

}
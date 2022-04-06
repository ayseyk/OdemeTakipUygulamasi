package com.example.odemetakip.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.odemetakip.Model.OdemeKaydi
import com.example.odemetakip.Model.OdemeTipi

class OdemeKaydiOperation (context: Context) {
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

    fun odemeKaydiEkle(odemeKaydi: OdemeKaydi) {
        val cv = ContentValues()
        cv.put("OdemeTipi", odemeKaydi.OdemeTipi)
        cv.put("Tarih", odemeKaydi.Tarih)
        cv.put("Tutar", odemeKaydi.Tutar)

        open()
        OdemeTakipDatabase!!.insert("OdemeKaydi", null, cv)
        close()

    }

    fun odemeKaydiGuncelle(odemeKaydi: OdemeKaydi) {
        val cv = ContentValues()
        //cv.put("Id",odemeKaydi.Id)
        cv.put("OdemeTipi", odemeKaydi.OdemeTipi)
        cv.put("Tarih", odemeKaydi.Tarih)
        cv.put("Tutar", odemeKaydi.Tutar)

        open()

        OdemeTakipDatabase!!.update("OdemeKaydi", cv, "OdemeTipi = ? and Tarih = ?", arrayOf
            (odemeKaydi.OdemeTipi.toString(), odemeKaydi.Tarih))

        close()
    }

    fun odemeKaydiSil(odemeKaydi: OdemeKaydi) {
        open()
        OdemeTakipDatabase!!.delete("OdemeKaydi", "OdemeTipi = ? and Tarih = ?", arrayOf
            (odemeKaydi.OdemeTipi.toString(), odemeKaydi.Tarih))
        close()
    }

    private fun tumOdemeKaydiGetir(id:Int): Cursor {
        val sorgu = "Select * from OdemeKaydi Where OdemeTipi = ?"
        var k = OdemeTakipDatabase!!.rawQuery(sorgu, arrayOf(id.toString()))
        return OdemeTakipDatabase!!.rawQuery(sorgu, arrayOf(id.toString()))
        println()
    }

    private fun tumOdemeKaydiGetirTip(odemeKaydi: OdemeKaydi): Cursor {
        val sorgu = "Select * from OdemeKaydi Where OdemeTipi = ?"

        return OdemeTakipDatabase!!.rawQuery(sorgu, arrayOf(odemeKaydi.OdemeTipi.toString()))
    }

    @SuppressLint("Range")
    fun odemeKaydiGetir(id:Int): ArrayList<OdemeKaydi> {
        val oKaydiList = ArrayList<OdemeKaydi>()
        var odemeKaydi: OdemeKaydi

        open()
        var c: Cursor = tumOdemeKaydiGetir(id)

        if (c.moveToFirst()) {
            do {
                odemeKaydi = OdemeKaydi()
               // odemeKaydi.Id = c.getInt(0)//(c.getColumnIndex("Id"))
                odemeKaydi.OdemeTipi = c.getInt(c.getColumnIndex("OdemeTipi"))
                odemeKaydi.Tarih = c.getString(c.getColumnIndex("Tarih"))
                odemeKaydi.Tutar = c.getDouble(c.getColumnIndex("Tutar"))
                oKaydiList.add(odemeKaydi)
            } while (c.moveToNext())
        }
        close()
        println()
        return oKaydiList
    }
}
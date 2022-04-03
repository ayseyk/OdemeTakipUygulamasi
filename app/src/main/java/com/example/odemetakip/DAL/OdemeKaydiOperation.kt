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

    fun odemeKaydiEkle(odemeKaydi: OdemeKaydi): Long {
        val cv = ContentValues()
        cv.put("Id",odemeKaydi.Id)
        cv.put("OdemeTipi", odemeKaydi.OdemeTipi.Id)
        cv.put("Tarih", odemeKaydi.Tarih)
        cv.put("Tutar", odemeKaydi.Tutar)

        open()
        val etkilenenKayit = OdemeTakipDatabase!!.insert("OdemeKaydi", null, cv)
        close()
        return etkilenenKayit
    }

    fun odemeKaydiGuncelle(odemeKaydi: OdemeKaydi) {
        val cv = ContentValues()
        cv.put("Id",odemeKaydi.Id)
        cv.put("OdemeTipi", odemeKaydi.OdemeTipi.Id)
        cv.put("Tarih", odemeKaydi.Tarih)
        cv.put("Tutar", odemeKaydi.Tutar)

        open()

        OdemeTakipDatabase!!.update("OdemeKaydi", cv, "Id = ?", arrayOf(odemeKaydi.Id.toString()))

        close()
    }

    fun odemeKaydiSil(id: Int) {
        open()
        OdemeTakipDatabase!!.delete("OdemeKaydi", "Id = ?", arrayOf(id.toString()))
        close()
    }

    private fun tumOdemeKaydiGetir(): Cursor {
        val sorgu = "Select * from OdemeKaydi"

        return OdemeTakipDatabase!!.rawQuery(sorgu, null)
    }

    private fun tumOdemeKaydiGetirTip(odemeTipiId : Int): Cursor {
        val sorgu = "Select * from OdemeKaydi Where OdemeTipi = ?"

        return OdemeTakipDatabase!!.rawQuery(sorgu, arrayOf(odemeTipiId.toString()))
    }

    @SuppressLint("Range")
    fun odemeKaydiGetir(): ArrayList<OdemeKaydi> {
        val oKaydiList = ArrayList<OdemeKaydi>()
        var odemeKaydi: OdemeKaydi

        open()
        var c: Cursor = tumOdemeKaydiGetir()

        if (c.moveToFirst()) {
            do {
                odemeKaydi = OdemeKaydi()
                odemeKaydi.Id = c.getInt(0)//(c.getColumnIndex("Id"))
                odemeKaydi.OdemeTipi.Id = c.getInt(c.getColumnIndex("OdemeTipi"))
                odemeKaydi.Tarih = c.getString(c.getColumnIndex("Tarih"))
                odemeKaydi.Tutar = c.getDouble(c.getColumnIndex("Tutar"))
                oKaydiList.add(odemeKaydi)
            } while (c.moveToNext())
        }

        close()
        return oKaydiList
    }
}
package com.example.odemetakip.DAL

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseOpenHelper(context: Context, name : String, factory: SQLiteDatabase.CursorFactory?, version : Int) : SQLiteOpenHelper(context, name, factory, version)
{
    override fun onCreate(p0: SQLiteDatabase) {

        val sorgu1 = "CREATE TABLE OdemeTipi(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "Baslik TEXT NOT NULL UNIQUE, " +
                "OdemePeriyodu TEXT, PeriyotGunu INTEGER)"
        p0.execSQL(sorgu1)

        val sorgu2 = "CREATE TABLE OdemeKaydi(/*Id INTEGER NOT NULL UNIQUE,*/OdemeTipi INTEGER NOT NULL, Tarih TEXT NOT NULL, " +
                "Tutar NUMERIC NOT NULL, FOREIGN KEY (OdemeTipi) REFERENCES OdemeTipi(Id))"
        p0.execSQL(sorgu2)
    }
/*CREATE TABLE "OdemeKaydi" (
	"Id"	INTEGER NOT NULL UNIQUE,
	"OdemeTipi"	INTEGER NOT NULL,
	"Tarih"	TEXT NOT NULL,
	"Tutar"	NUMERIC NOT NULL,
	FOREIGN KEY("OdemeTipi") REFERENCES "OdemeTipi"("Id"),
	PRIMARY KEY("Tarih","OdemeTipi")
);
CREATE TABLE "OdemeTipi" (
	"Id"	INTEGER NOT NULL,
	"Baslik"	TEXT NOT NULL UNIQUE,
	"OdemePeriyodu"	TEXT,
	"PeriyotGunu"	INTEGER,
	PRIMARY KEY("Id" AUTOINCREMENT)
);

*/


    override fun onUpgrade(p0: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion == 1)
        {

        }
        else if (oldVersion == 2)
        {

        }
    }

}
package com.example.odemetakip.Model

import kotlin.properties.Delegates

class OdemeTipi {
    var Id by Delegates.notNull<Int>()  //null olmayacak ama baslangıç beklemeden belleğe çıkar
    var Baslik by Delegates.notNull<String>()
    var Periyot : String? = null
    var PeriyotGunu : Int? = null

}
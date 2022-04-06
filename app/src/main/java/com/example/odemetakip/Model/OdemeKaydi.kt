package com.example.odemetakip.Model

import java.io.Serializable
import kotlin.properties.Delegates

class OdemeKaydi : Serializable {
    //var Id by Delegates.notNull<Int>()
    var OdemeTipi by Delegates.notNull<Int>()
    var Tutar : Double? = null
    var Tarih : String? = null
}
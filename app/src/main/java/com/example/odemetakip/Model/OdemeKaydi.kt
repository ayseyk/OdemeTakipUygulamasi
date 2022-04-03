package com.example.odemetakip.Model

import kotlin.properties.Delegates

class OdemeKaydi {
    var Id by Delegates.notNull<Int>()
    var OdemeTipi by Delegates.notNull<OdemeTipi>()
    var Tutar : Double? = null
    var Tarih : String? = null
}
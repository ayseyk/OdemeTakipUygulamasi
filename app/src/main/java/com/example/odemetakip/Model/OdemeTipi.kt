package com.example.odemetakip.Model

import java.io.Serializable
import kotlin.properties.Delegates

class OdemeTipi {
    var Id by Delegates.notNull<Int>()
    var Baslik by Delegates.notNull<String>()
    var Periyot : String? = null
    var PeriyotGunu : Int? = null

}
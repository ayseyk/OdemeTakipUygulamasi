package com.example.odemetakip.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.odemetakip.BLL.OdemeKaydiLogic
import com.example.odemetakip.BLL.OdemeTipiLogic
import com.example.odemetakip.BLL.RCV.OdemeKaydiAdapter
import com.example.odemetakip.BLL.RCV.OdemeTipiAdapter
import com.example.odemetakip.Model.OdemeKaydi
import com.example.odemetakip.Model.OdemeTipi
import com.example.odemetakip.databinding.ActivityDetayGoruntuleBinding


class DetayGoruntule : AppCompatActivity() {
    lateinit var binding : ActivityDetayGoruntuleBinding
    var oKaydiList = ArrayList<OdemeKaydi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
        initializeEvents()
        setDefaults()

    }
    private fun initializeViews() {
        binding = ActivityDetayGoruntuleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvOdemeKaydi.layoutManager = layoutManager

        var odemeTipi = intent.getSerializableExtra("odemeTipi") as OdemeTipi
        detayDoldur(odemeTipi)
    }
    private fun initializeEvents(){
        binding.btnOdemeEkle.setOnClickListener {
            yeniOdemeKaydiEkle()
        }
        binding.btnDuzenle.setOnClickListener {
            tipiDuzenle()
        }
    }
    private fun setDefaults() {
        binding.rvOdemeKaydi.adapter = OdemeKaydiAdapter(this, oKaydiList, ::odemeKaydiItemClick)
        oKaydiList = OdemeKaydiLogic.tumOdemeKayitlariniGetir(this)
    }
    fun odemeKaydiItemClick(position : Int)
    {
        //kaydı silecek
    }
    fun yeniOdemeKaydiEkle()
    {
        //ödeme ekle ekranına gidecek
    }
    fun tipiDuzenle(){
        //içindeki bilgilerle yeni ödeme tipi ekle ekranına gidecek
    }

    fun detayDoldur(odemeTipi : OdemeTipi){
        binding.tvBaslik.text = odemeTipi.Baslik
        binding.tvPeriyot.text = odemeTipi.Periyot
        binding.tvPeriyotGunu.text = odemeTipi.PeriyotGunu.toString()
    }

}
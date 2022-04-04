package com.example.odemetakip.View

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.odemetakip.BLL.OdemeKaydiLogic
import com.example.odemetakip.Model.OdemeKaydi
import com.example.odemetakip.Model.OdemeTipi
import com.example.odemetakip.databinding.ActivityOdemeEkleBinding


class OdemeEkle : AppCompatActivity() {
    lateinit var binding : ActivityOdemeEkleBinding
    var odemeTipi : OdemeTipi? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
        initializeEvents()
        setDefaults()
    }
    private fun initializeViews() {
        binding = ActivityOdemeEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        odemeTipi = intent.getSerializableExtra("OdemeTipi") as OdemeTipi


    }
    private fun initializeEvents(){
        binding.btnKaydetOdeme.setOnClickListener {
            //ödeme kaydı kaydedilecek
            odemeKaydiEkle()
        }
    }
    private fun setDefaults() {
        //tarihin içini bilgisayarın tarihiyle otomatik doldur.
    }
    fun odemeKaydiEkle(){
        var odemeKaydi = OdemeKaydi()
        if(odemeTipi != null){
            odemeKaydi.OdemeTipi = odemeTipi!!
            odemeKaydi.Tarih = binding.etTarih.text.toString()
            odemeKaydi.Tutar = binding.etTutar.text.toString().toDouble()
        }
        OdemeKaydiLogic.ekle(this, odemeKaydi)
        finish()
    }


}
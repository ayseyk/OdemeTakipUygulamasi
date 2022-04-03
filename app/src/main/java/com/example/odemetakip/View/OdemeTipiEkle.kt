package com.example.odemetakip.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.odemetakip.BLL.OdemeTipiLogic
import com.example.odemetakip.Model.OdemeTipi
import com.example.odemetakip.databinding.ActivityOdemeTipiEkleBinding

class OdemeTipiEkle : AppCompatActivity() {
    lateinit var binding : ActivityOdemeTipiEkleBinding
    var odemeTipi : OdemeTipi? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
        initializeEvents()

    }
    private fun initializeViews() {
        binding = ActivityOdemeTipiEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    private fun initializeEvents(){
        binding.btnKaydetTip.setOnClickListener {
            yeniTipEkle()
        }
    }

    fun yeniTipEkle() {
        if (!binding.etBaslik.text.isEmpty())
        {
            odemeTipi!!.Baslik = binding.etBaslik.text.toString()
            //odemeTipi!!.Soyad = binding.etSoyad.text.toString()
            odemeTipi!!.PeriyotGunu = binding.etPeriyotGunu.text.toString().toInt()

            OdemeTipiLogic.ekle(this,odemeTipi!!)

            setResult(RESULT_OK, intent)
            finish()
        }
        else
        {
            Toast.makeText(this, "Başlık boş bırakılamaz.", Toast.LENGTH_LONG).show()
        }
    }
}

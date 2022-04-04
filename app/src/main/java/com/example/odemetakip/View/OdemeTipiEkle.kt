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
    var isAnUpdate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
        initializeEvents()

    }
    private fun initializeViews() {
        binding = ActivityOdemeTipiEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        odemeTipi = intent.getSerializableExtra("odemeTipi") as OdemeTipi?
        Toast.makeText(this,"${odemeTipi != null}",Toast.LENGTH_LONG).show()
        if(odemeTipi != null){
            isAnUpdate = true
           // tipiGuncelle(odemeTipi!!)
        }

    }
    private fun initializeEvents(){
        binding.btnKaydetTip.setOnClickListener {
            if(isAnUpdate){
                tipiGuncelle(odemeTipi!!)
            }
            else{
                yeniTipEkle()
            }

        }
        binding.btnSil.setOnClickListener {
            odemeTipSil()
        }
    }
    fun odemeTipSil(){
        //sadece geri detay görüntülemeden gelince görünecek, kaydı silecek.
    }

    fun yeniTipEkle() {
        if (!binding.etBaslik.text.isEmpty())
        {
            odemeTipi = OdemeTipi()
            odemeTipi!!.Baslik = binding.etBaslik.text.toString()
            odemeTipi!!.Periyot = binding.spPeriyot.text.toString()
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
    fun tipiGuncelle(odemeTipi : OdemeTipi){

        odemeTipi!!.Baslik = binding.etBaslik.text.toString()
        //periyot
        odemeTipi!!.PeriyotGunu = binding.etPeriyotGunu.text.toString().toInt()
        OdemeTipiLogic.guncelle(this,odemeTipi)

        var intent = Intent()
        intent.putExtra("odemeTipi", odemeTipi)
        setResult(RESULT_OK, intent)
        finish()
    }
}

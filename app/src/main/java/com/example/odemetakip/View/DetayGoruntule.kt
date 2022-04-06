package com.example.odemetakip.View

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
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
    internal var oKaydiList = ArrayList<OdemeKaydi>()
    var odemeTipi: OdemeTipi?=null
    var tipId : Int? = null
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

        tipId = intent.getStringExtra("odemeTipiMainden")!!.toInt()
        odemeTipi = OdemeTipiLogic.idIleGetir(this, tipId!!)

        if(odemeTipi != null){
            detayDoldur(odemeTipi!!)
        }
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
        if(tipId!= null){
            Toast.makeText(this,"null değil",Toast.LENGTH_LONG).show()
        }

        oKaydiList = OdemeKaydiLogic.tumOdemeKayitlariniGetir(this,tipId!!)//id ile getir.
        //Toast.makeText(this,"${oKaydiList.first().Tarih}",Toast.LENGTH_LONG).show()
        binding.rvOdemeKaydi.adapter = OdemeKaydiAdapter(this, oKaydiList, ::odemeKaydiItemClick)
    }
    fun odemeKaydiItemClick(position : Int)
    {
        /*val adb : AlertDialog.Builder = AlertDialog.Builder(this)
        adb.setTitle("Ödeme Kaydını Sil").setMessage("Ödeme kaydını silmek istediğinizden emin " +
                "misiniz?").setPositiveButton("Sil",DialogInterface.OnClickListener { dialogInterface, i ->
            OdemeKaydiLogic.sil(this, oKaydiList.get(position))
            finish()
        }).setNegativeButton("Vazgeç",null).show()

        kayitListesiGüncelle()*/
    }
    fun kayitListesiGüncelle(){/*
        oKaydiList = OdemeKaydiLogic.tumOdemeKayitlariniGetir(this)
        binding.rvOdemeKaydi.adapter!!.notifyDataSetChanged()*/
    }
    fun yeniOdemeKaydiEkle()
    {/*
        var intent = Intent(this, OdemeEkle::class.java)
        intent.putExtra("OdemeTipi", odemeTipi)
        startActivity(intent)*/
    }
    fun tipiDuzenle(){
        var intent = Intent(this, OdemeTipiEkle::class.java)
        intent.putExtra("OdemeTipiId",tipId.toString())
        resultLauncher.launch(intent)
    }
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            tipId = result.data!!.getStringExtra("odemeTipi")!!.toInt()
            odemeTipi = OdemeTipiLogic.idIleGetir(this, tipId!!)

            if(odemeTipi != null){
                detayDoldur(odemeTipi!!)
            }
        }
        if(result.resultCode == 0){
            var intent = Intent()
            setResult(0)
            finish()
        }

    }
    @SuppressLint("SetTextI18n")
    fun detayDoldur(odemeTipi : OdemeTipi){
        binding.tvBaslik.text = "Ödeme Başlığı: ${odemeTipi.Baslik}"
        binding.tvPeriyot.text = "Ödemenin Periyodu: ${odemeTipi.Periyot}"
        binding.tvPeriyotGunu.text = "Ödemenin Periyot Günü: ${odemeTipi.PeriyotGunu.toString()}"
    }
}
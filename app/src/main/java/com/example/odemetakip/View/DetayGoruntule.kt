package com.example.odemetakip.View

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.odemetakip.BLL.OdemeKaydiLogic
import com.example.odemetakip.BLL.OdemeTipiLogic
import com.example.odemetakip.BLL.RCV.OdemeKaydiAdapter
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
        kayitListesiYenile()
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

    @SuppressLint("SetTextI18n")
    fun detayDoldur(odemeTipi : OdemeTipi) {
        binding.tvBaslik.text = "Ödeme Başlığı: ${odemeTipi.Baslik}"

        if (odemeTipi.Periyot == null) {
            binding.tvPeriyot.text = "-"
        } else {
            binding.tvPeriyot.text = "Ödeme Periyodu: ${odemeTipi.Periyot}"
        }
        if (odemeTipi.PeriyotGunu == null || odemeTipi.Periyot == null || odemeTipi.PeriyotGunu == 0) {
            binding.tvPeriyotGunu.text = "-"
        } else {
            binding.tvPeriyotGunu.text = "Ödeme Periyot Günü: ${odemeTipi.PeriyotGunu.toString()}"
        }
    }
    fun odemeKaydiItemClick(position : Int)
    {
        val adb : AlertDialog.Builder = AlertDialog.Builder(this)
        adb.setTitle("Ödeme Kaydını Sil").setMessage("Ödeme kaydını silmek istediğinizden emin misiniz?").setPositiveButton("Sil",DialogInterface.OnClickListener { dialogInterface, i ->
            OdemeKaydiLogic.sil(this, oKaydiList.get(position))
            kayitListesiYenile()})
            .setNegativeButton("Vazgeç",null).show()
    }
    fun kayitListesiYenile(){
        oKaydiList = OdemeKaydiLogic.tumOdemeKayitlariniGetir(this,tipId!!)
        binding.rvOdemeKaydi.adapter = OdemeKaydiAdapter(this, oKaydiList, ::odemeKaydiItemClick)
    }
    fun yeniOdemeKaydiEkle()
    {
        var intent = Intent(this, OdemeEkle::class.java)
        intent.putExtra("tipIdKayit",tipId.toString())
        intent.putExtra("detayEkranindan","true")
        resultLauncher.launch(intent)
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
            setResult(0,intent)
            finish()
        }
        if(result.resultCode == 1){
            kayitListesiYenile()
        }

    }

}
package com.example.odemetakip.View

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.odemetakip.BLL.OdemeKaydiLogic
import com.example.odemetakip.BLL.OdemeTipiLogic
import com.example.odemetakip.Model.OdemeTipi
import com.example.odemetakip.R
import com.example.odemetakip.databinding.ActivityOdemeTipiEkleBinding

class OdemeTipiEkle : AppCompatActivity() {
    lateinit var binding : ActivityOdemeTipiEkleBinding
    var odemeTipi : OdemeTipi? = null
    var isAnUpdate = false
    var spList = ArrayList<String>()
    var tipId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
        initializeEvents()

    }
    private fun initializeViews() {
        binding = ActivityOdemeTipiEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSil.isVisible = false

        tipId = intent.getStringExtra("OdemeTipiId")?.toInt()

        if(tipId != null){
            odemeTipi = OdemeTipiLogic.idIleGetir(this, tipId!!)
        }

        if(odemeTipi != null)
        {
            isAnUpdate = true
            binding.btnSil.isVisible = true
            tipeGoreDoldur()
        }
        else
        {
            odemeTipi = OdemeTipi()
        }
        spDoldur()
    }
    fun tipeGoreDoldur(){
        binding.etBaslik.setText(odemeTipi!!.Baslik)
        /*if(odemeTipi!!.PeriyotGunu != null){
            binding.etPeriyotGunu.setText(odemeTipi.PeriyotGunu!!)
        }*/
        binding.tvDuzenleEkle.text = "Ödeme Tipi Düzenle"

        /*
        if(odemeTipi.PeriyotGunu != null) {
            binding.etPeriyotGunu.setText(odemeTipi.PeriyotGunu!!)
        }else binding.etPeriyotGunu.setText("")
        var k = 0
        if(odemeTipi.Periyot=="Haftalık"){
            k=1
        }else if (odemeTipi.Periyot=="Aylık"){
            k=2
        }else if(odemeTipi.Periyot=="Yıllık"){
            k=3
        }else{
            k=0
        }
        if(odemeTipi.Periyot != null){
            binding.spPeriyot.setSelection(k)
        }
*/
    }
    fun spDoldur(){
        spList.add("Periyot Seçiniz..")
        spList.add("Haftalık")
        spList.add("Aylık")
        spList.add("Yıllık")
        var adb : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, spList)
        binding.spPeriyot.adapter = adb
    }
    private fun initializeEvents(){
        spSecim()
        binding.btnKaydetTip.setOnClickListener {
            if(isAnUpdate)
            {
                tipiGuncelle(odemeTipi!!)
            }
            else
            {
                yeniTipEkle()
            }
        }
        if(binding.btnSil.isVisible){
            binding.btnSil.setOnClickListener {
                odemeTipSil()
            }
        }

    }
    fun odemeTipSil(){
        val adb : AlertDialog.Builder = AlertDialog.Builder(this)
        adb.setTitle("Tipi silmek istediğinizden emin misiniz?").setMessage("Tipi silerseniz kayıtlar da silinir.").setPositiveButton("Sil",DialogInterface.OnClickListener { dialogInterface, i ->
            OdemeTipiLogic.sil(this,tipId!!)
            finish()
            mainegec()
        }).setNegativeButton("Vazgeç",null).show()
    }

    fun mainegec(){
        var intent = Intent()
        setResult(0)
        finish()
    }
    fun yeniTipEkle() {
        if (!binding.etBaslik.text.isEmpty())
        {
            odemeTipi!!.Baslik = binding.etBaslik.text.toString()
            if(binding.etPeriyotGunu.text.toString() == ""){
                odemeTipi!!.PeriyotGunu = null
            } else{
                odemeTipi!!.PeriyotGunu = binding.etPeriyotGunu.text.toString().toInt()
            }
            /*var hataVarMi = OdemeTipiLogic.ekle(this,odemeTipi!!)
            if(hataVarMi){

                OdemeTipiLogic.hataGoster(this)
            }
            else{
                var intent = Intent()
                setResult(RESULT_OK, intent)
                finish()
            }*/
            OdemeTipiLogic.ekle(this,odemeTipi!!)
            var intent = Intent()
            setResult(RESULT_OK, intent)
            finish()

        }
        else
        {
            Toast.makeText(this, "Başlık boş bırakılamaz.", Toast.LENGTH_LONG).show()
        }
    }

    fun spSecim() {
        binding.spPeriyot.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
               odemeTipi!!.Periyot = spList.get(p2)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                odemeTipi!!.Periyot = null
            }
        }
    }

    fun tipiGuncelle(odemeTipi : OdemeTipi){
        isAnUpdate = false
        odemeTipi.Baslik = binding.etBaslik.text.toString()
        if(binding.etPeriyotGunu.text.toString() == ""){
            odemeTipi.PeriyotGunu = null
        } else{
            odemeTipi.PeriyotGunu = binding.etPeriyotGunu.text.toString().toInt()
        }

        OdemeTipiLogic.guncelle(this,tipId!!,odemeTipi)

        var intent = Intent()
        intent.putExtra("odemeTipi", tipId.toString())
        setResult(RESULT_OK, intent)
        finish()
    }
}

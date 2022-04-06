package com.example.odemetakip.View

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
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
    var isUpdateSp = false
    var tipId : Int? = null
    var index = 0
    lateinit var periyotlar : Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
        initializeEvents()
        setDefaults()
    }
    private fun initializeViews() {
        periyotlar = resources.getStringArray(R.array.periyotlar)
        binding = ActivityOdemeTipiEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSil.isVisible = false
        spDoldur()
        tipId = intent.getStringExtra("OdemeTipiId")?.toInt()

    }
    private fun setDefaults(){
        if(tipId != null) odemeTipi = OdemeTipiLogic.idIleGetir(this, tipId!!)

        if(odemeTipi != null)
        {
            isAnUpdate = true
            binding.btnSil.isVisible = true
            tipeGoreDoldur()
        }
        else odemeTipi = OdemeTipi()
    }
    private fun initializeEvents(){
        spSecim()
        binding.btnKaydetTip.setOnClickListener {
            if(isAnUpdate) tipiGuncelle(odemeTipi!!) else yeniTipEkle()
        }
        binding.btnSil.setOnClickListener {
            odemeTipSil()
        }
    }
    fun tipeGoreDoldur(){
        binding.etBaslik.setText(odemeTipi!!.Baslik)

        if(odemeTipi!!.PeriyotGunu != 0){
            binding.etPeriyotGunu.setText(odemeTipi!!.PeriyotGunu!!.toString())
        }
        else binding.etPeriyotGunu.text.clear()

        for(i in periyotlar.indices){
            if(odemeTipi!!.Periyot == periyotlar.get(i)){
                binding.spPeriyot.setSelection(i)
                index = i
                break
            }
        }
    }

    fun spDoldur(){
        val periyotlar= resources.getStringArray(R.array.periyotlar)
        val spinnerAdapter= object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, periyotlar) {
            override fun isEnabled(position: Int): Boolean {
                return true
            }
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view: TextView = super.getDropDownView(position, convertView, parent) as TextView
                if(position == 0 && !isUpdateSp) {
                    view.setTextColor(Color.GRAY)
                }
                return view
            }
        }
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spPeriyot.adapter = spinnerAdapter
    }
    fun spSecim() {
        binding.spPeriyot.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                odemeTipi!!.Periyot = null
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val value = parent!!.getItemAtPosition(position).toString()
                odemeTipi!!.Periyot = value
                index = position
                if(value == periyotlar[0] ){
                    odemeTipi!!.Periyot = null
                }
            }
        }
    }
    fun odemeTipSil(){
        val adb : AlertDialog.Builder = AlertDialog.Builder(this)
        adb.setTitle("Tipi silmek istediğinizden emin misiniz?").setMessage("Tipi silerseniz kayıtlar da silinir.").setPositiveButton("Sil",DialogInterface.OnClickListener { dialogInterface, i ->
            OdemeTipiLogic.sil(this,tipId!!)
            OdemeKaydiLogic.tumOdemeKaydiSilId(this,tipId!!)
            val intent = Intent()
            setResult(0,intent)
            finish()
        }).setNegativeButton("Vazgeç",null).show()
    }

    fun yeniTipEkle() {
        if (binding.etBaslik.text.isEmpty()){
            OdemeTipiLogic.hataGoster(this,"Ödeme Tipi kısmı boş bırakılamaz.")
        }
        else
        {
            odemeTipi!!.Baslik = binding.etBaslik.text.toString()
            if(!OdemeTipiLogic.baslikKontrol(this, odemeTipi!!)) return

            if(binding.etPeriyotGunu.text.toString() == "" || odemeTipi!!.Periyot == null) odemeTipi!!.PeriyotGunu = null
            else odemeTipi!!.PeriyotGunu = binding.etPeriyotGunu.text.toString().toInt()

            if(!OdemeTipiLogic.periyotGirisKontrol(odemeTipi!!,periyotlar.get(index))){
                OdemeTipiLogic.hataGoster(this,"Girdiğiniz periyot günü seçtiğiniz periyoda uygun olmalıdır.")
            }
            else{
                OdemeTipiLogic.ekle(this,odemeTipi!!)
                val intent = Intent()
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    fun tipiGuncelle(odemeTipi : OdemeTipi){
        isUpdateSp = true

        odemeTipi.Baslik = binding.etBaslik.text.toString()
        spSecim()
        if(odemeTipi.Periyot == null)  binding.etPeriyotGunu.text.clear()

        if(binding.etPeriyotGunu.text.isEmpty()) odemeTipi.PeriyotGunu = null
        else odemeTipi.PeriyotGunu = binding.etPeriyotGunu.text.toString().toInt()

        if(!OdemeTipiLogic.periyotGirisKontrol(odemeTipi, periyotlar.get(index))){
            OdemeTipiLogic.hataGoster(this,"Girdiğiniz periyot günü seçtiğiniz periyoda uygun olmalıdır.")
        }
        else{
            isAnUpdate = false
            OdemeTipiLogic.guncelle(this,tipId!!,odemeTipi)
            val intent = Intent()
            intent.putExtra("odemeTipi", tipId.toString())
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}

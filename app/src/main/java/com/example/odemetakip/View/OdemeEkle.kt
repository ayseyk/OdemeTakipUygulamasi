package com.example.odemetakip.View

import android.R
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.odemetakip.BLL.OdemeKaydiLogic
import com.example.odemetakip.BLL.OdemeTipiLogic
import com.example.odemetakip.Model.OdemeKaydi
import com.example.odemetakip.Model.OdemeTipi
import com.example.odemetakip.databinding.ActivityOdemeEkleBinding
import java.util.*


class OdemeEkle : AppCompatActivity() {
    lateinit var binding : ActivityOdemeEkleBinding
    var odemeTipi : OdemeTipi? = null
    var tipId : Int? = null
    var isDetay = false
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
        initializeEvents()
        setDefaults()
    }

    private fun initializeViews() {
        binding = ActivityOdemeEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tipId = intent.getStringExtra("tipIdKayit")!!.toInt()
        isDetay = intent.getStringExtra("detayEkranindan").toBoolean()
    }
    private fun initializeEvents(){
        binding.btnKaydetOdeme.setOnClickListener {
            odemeKaydiEkle()
        }
    }
    @SuppressLint("SetTextI18n")
    private fun setDefaults() {
        val now = Calendar.getInstance()
        binding.etTarih.setText("${now.get(Calendar.DAY_OF_MONTH)}.${now.get(Calendar.MONTH)
        }.${now.get(Calendar.YEAR)}")
        binding.tarihView.setOnClickListener {
            val datePicker = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    binding.etTarih.setText("$dayOfMonth.$month.$year")
                }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
            datePicker.getDatePicker().setMaxDate(now.getTimeInMillis());
            datePicker.show()
        }
    }
    fun odemeKaydiEkle(){
        val odemeKaydi = OdemeKaydi()
        odemeKaydi.OdemeTipi = tipId!!

        if(binding.etTutar.text.isEmpty()){
            OdemeKaydiLogic.hataGoster(this,"Ödeme Tutarı kısmı boş bırakılamaz.")
            return
        }else{
            odemeKaydi.Tutar = binding.etTutar.text.toString().toDouble()
        }
        odemeKaydi.Tarih = binding.etTarih.text.toString()
        OdemeKaydiLogic.ekle(this, odemeKaydi)

        if(isDetay)
        {
            val intent = Intent()
            setResult(1,intent)
            finish()
        }
        finish()
    }
    override fun onBackPressed() {
        if(isDetay){
            val intent = Intent()
            setResult(1, intent)
            finish()
        }
        else super.onBackPressed()
    }

}
package com.example.odemetakip.View

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
import com.example.odemetakip.BLL.RCV.OdemeTipiAdapter
import com.example.odemetakip.Model.OdemeKaydi
import com.example.odemetakip.Model.OdemeTipi
import com.example.odemetakip.databinding.ActivityDetayGoruntuleBinding


class DetayGoruntule : AppCompatActivity() {
    lateinit var binding : ActivityDetayGoruntuleBinding
    internal var oKaydiList = ArrayList<OdemeKaydi>()
    var odemeTipi : OdemeTipi? = null
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

        odemeTipi = intent.getSerializableExtra("odemeTipi") as OdemeTipi
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
        oKaydiList = OdemeKaydiLogic.tumOdemeKayitlariniGetir(this)
        binding.rvOdemeKaydi.adapter = OdemeKaydiAdapter(this, oKaydiList, ::odemeKaydiItemClick)
    }
    fun odemeKaydiItemClick(position : Int)
    {
        val adb : AlertDialog.Builder = AlertDialog.Builder(this)
        adb.setTitle("Ödeme Kaydını Sil").setMessage("Ödeme kaydını silmek istediğinizden emin " +
                "misiniz?").setPositiveButton("Sil",DialogInterface.OnClickListener { dialogInterface, i ->
            OdemeKaydiLogic.sil(this, oKaydiList.get(position))
            finish()
        }).setNegativeButton("Vazgeç",null).show()

        kayitListesiGüncelle()
    }
    fun kayitListesiGüncelle(){
        oKaydiList = OdemeKaydiLogic.tumOdemeKayitlariniGetir(this)
        binding.rvOdemeKaydi.adapter!!.notifyDataSetChanged()
    }
    fun yeniOdemeKaydiEkle()
    {
        var intent = Intent(this, OdemeEkle::class.java)
        intent.putExtra("OdemeTipi", odemeTipi)
        startActivity(intent)
    }
    fun tipiDuzenle(){
        //içindeki bilgilerle yeni ödeme tipi ekle ekranına gidecek
        var intent = Intent(this, OdemeTipiEkle::class.java)
        intent.putExtra("OdemeTipi", odemeTipi)
        resultLauncher.launch(intent)
    }
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            odemeTipi = result.data!!.getSerializableExtra("odemeTipi") as OdemeTipi
            detayDoldur(odemeTipi!!)
        }
    }
    fun detayDoldur(odemeTipi : OdemeTipi){
        binding.tvBaslik.text = odemeTipi.Baslik
        binding.tvPeriyot.text = odemeTipi.Periyot
        binding.tvPeriyotGunu.text = odemeTipi.PeriyotGunu.toString()
    }

}
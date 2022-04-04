package com.example.odemetakip.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.odemetakip.BLL.RCV.OdemeTipiAdapter
import com.example.odemetakip.BLL.OdemeTipiLogic
import com.example.odemetakip.Model.OdemeTipi
import com.example.odemetakip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var oTipiList = ArrayList<OdemeTipi>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
        initializeEvents()
        setDefaults()
    }

    private fun initializeViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvTipList.layoutManager = layoutManager
    }
    private fun initializeEvents(){
        binding.btnOdemeTipiEkle.setOnClickListener {
            yeniTipEkle()
        }
    }
    private fun setDefaults() {
        oTipiList = OdemeTipiLogic.tumOdemeTipleriGetir(this)
        binding.rvTipList.adapter = OdemeTipiAdapter(this, oTipiList, ::odemeTipiItemClick,
            ::yeniKayitEkle)
    }
    fun odemeTipiItemClick(position : Int)
    {
        var intent = Intent(this, DetayGoruntule::class.java)
        intent.putExtra("odemeTipi", oTipiList.get(position))
        startActivity(intent)
    }
    fun yeniKayitEkle(position: Int)
    {
        var intent = Intent(this, OdemeEkle::class.java)
        startActivity(intent)
    }
    fun yeniTipEkle(){
        var intent = Intent(this, OdemeTipiEkle::class.java)
        resultLauncher.launch(intent)
    }
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            oTipiList = OdemeTipiLogic.tumOdemeTipleriGetir(this)
            binding.rvTipList.adapter!!.notifyDataSetChanged()
        }
    }




    /*fun bunonaBastı() {

        var y = OdemeTipi()
        y.Baslik = "Su faturası"
        y.Periyot = "Aylık"
        y.PeriyotGunu = 5
        OdemeTipiLogic.ekle(this, y)

        y = OdemeTipi()
        y.Baslik = "Elektrik faturası"
        y.Periyot = "Yıllık"
        y.PeriyotGunu = 4
        OdemeTipiLogic.ekle(this, y)

        oTipiList = OdemeTipiLogic.tumOdemeTipleriGetir(this)


        println()

    }*/




}
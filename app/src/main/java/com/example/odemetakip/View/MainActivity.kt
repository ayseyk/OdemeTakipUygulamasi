package com.example.odemetakip.View

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
        listeyiGuncelle()
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
    private fun listeyiGuncelle() {
        oTipiList = OdemeTipiLogic.tumOdemeTipleriGetir(this)
        binding.rvTipList.adapter = OdemeTipiAdapter(this, oTipiList, ::odemeTipiItemClick,
            ::yeniKayitEkle)
    }
    fun odemeTipiItemClick(position : Int)
    {
        var intent = Intent(this, DetayGoruntule::class.java)
        var tipId = oTipiList.get(position).Id
        intent.putExtra("odemeTipiMainden",tipId.toString())
        resultLauncher.launch(intent)
    }
    //@SuppressLint("NotifyDataSetChanged")
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_CANCELED) {
            listeyiGuncelle()
        }
        if (result.resultCode == 0){
            listeyiGuncelle()
        }

    }

    fun yeniKayitEkle(position: Int)
    {
        var intent = Intent(this, OdemeEkle::class.java)
        var tipId = oTipiList.get(position).Id
        intent.putExtra("tipIdKayit",tipId.toString())
        startActivity(intent)
        //resultLauncher.launch(intent)
    }
    fun yeniTipEkle(){
        var intent = Intent(this, OdemeTipiEkle::class.java)
        startActivity(intent)
    }


}
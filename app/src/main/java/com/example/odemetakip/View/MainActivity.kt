package com.example.odemetakip.View

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.odemetakip.BLL.RCV.OdemeTipiAdapter
import com.example.odemetakip.BLL.OdemeTipiLogic
import com.example.odemetakip.Model.OdemeTipi
import com.example.odemetakip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var oTipiList = ArrayList<OdemeTipi>()
    var tipId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
        initializeEvents()
        listeyiYenile()
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
    private fun listeyiYenile() {
        oTipiList = OdemeTipiLogic.tumOdemeTipleriGetir(this)
        binding.rvTipList.adapter = OdemeTipiAdapter(this, oTipiList, ::odemeTipiItemClick,
            ::yeniKayitEkle)
    }
    fun odemeTipiItemClick(position : Int)
    {
        tipId = oTipiList.get(position).Id
        val intent = Intent(this, DetayGoruntule::class.java)
        intent.putExtra("odemeTipiMainden",tipId.toString())
        resultLauncher.launch(intent)
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_CANCELED) listeyiYenile()
        if (result.resultCode == RESULT_OK) listeyiYenile()
    }

    fun yeniKayitEkle(position: Int)
    {
        var intent = Intent(this, OdemeEkle::class.java)
        tipId = oTipiList.get(position).Id
        intent.putExtra("tipIdKayit",tipId.toString())
        startActivity(intent)
    }
    fun yeniTipEkle(){
        var intent = Intent(this, OdemeTipiEkle::class.java)
        resultLauncher.launch(intent)
    }

}
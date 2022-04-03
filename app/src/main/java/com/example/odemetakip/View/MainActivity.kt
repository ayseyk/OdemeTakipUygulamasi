package com.example.odemetakip.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.odemetakip.BLL.OdemeTipiLogic
import com.example.odemetakip.Model.OdemeTipi
import com.example.odemetakip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
        setDefaults()

        binding.btnOdemeTipiEkle.setOnClickListener{
            bunonaBastı()
        }


    }

    private fun initializeViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvTipList.layoutManager = layoutManager

       // binding.rvList.adapter = ResimAdapter(this, uriList, ::itemClick, ::itemLongClick)
    }
    private fun setDefaults() {
        //binding.rvList.adapter = ResimAdapter(this, uriList, ::itemClick, ::itemLongClick)
    }




    fun bunonaBastı() {

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

        var yList = OdemeTipiLogic.tumOdemeTipleriGetir(this)


        println()

    }




}
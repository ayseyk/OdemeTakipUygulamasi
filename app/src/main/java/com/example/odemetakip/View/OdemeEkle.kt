package com.example.odemetakip.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.odemetakip.databinding.ActivityOdemeEkleBinding

class OdemeEkle : AppCompatActivity() {
    lateinit var binding : ActivityOdemeEkleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()


    }


    private fun initializeViews() {
        binding = ActivityOdemeEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.rvList.layoutManager = GridLayoutManager(this, 2)
    }
}
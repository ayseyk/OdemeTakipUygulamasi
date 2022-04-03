package com.example.odemetakip.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.odemetakip.databinding.ActivityDetayGoruntuleBinding


class DetayGoruntule : AppCompatActivity() {
    lateinit var binding : ActivityDetayGoruntuleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()


    }


    private fun initializeViews() {
        binding = ActivityDetayGoruntuleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.rvList.layoutManager = GridLayoutManager(this, 2)
    }
}
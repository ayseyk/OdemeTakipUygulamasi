package com.example.odemetakip.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.odemetakip.databinding.ActivityOdemeTipiEkleBinding

class OdemeTipiEkle : AppCompatActivity() {
    lateinit var binding : ActivityOdemeTipiEkleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()


    }


    private fun initializeViews() {
        binding = ActivityOdemeTipiEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.rvList.layoutManager = GridLayoutManager(this, 2)
    }
}

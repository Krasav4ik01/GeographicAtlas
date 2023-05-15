package com.example.geographicatlas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CountryDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)


        val name = intent.getStringExtra("name")
        val capital = intent.getStringExtra("capital")
        val flag = intent.getStringExtra("flagImage")
        val postalCode = intent.getStringExtra("tvPostalCode")
        val population = intent.getStringExtra("tvPopulation")
        val area = intent.getStringExtra("tvArea")
        val currency = intent.getStringExtra("tvCurrency")
        val region = intent.getStringExtra("tvRegion")

        val flagImage = findViewById<ImageView>(R.id.detailFlagImage)

        Glide.with(flagImage).load(flag).into(flagImage)
        val tvCapital = findViewById<TextView>(R.id.tvDetailCapital)
        val tvCapitalCoordinates = findViewById<TextView>(R.id.tvCapitalCoordinates)
        val tvPopulation = findViewById<TextView>(R.id.tvPopulation)
        val tvArea = findViewById<TextView>(R.id.tvArea)
        val tvCurrency = findViewById<TextView>(R.id.tvCurrency)
        val tvRegion = findViewById<TextView>(R.id.tvDetailRegion)

        tvCapital.text = "Capital: "+ capital
        tvCapitalCoordinates.text = postalCode
        tvPopulation.text = "Population: "+ population
        tvArea.text = "Area: "+ area
        tvCurrency.text = "Currency: "+ currency
        tvRegion.text = "Region: "+ region



        supportActionBar?.title = name

    }


}
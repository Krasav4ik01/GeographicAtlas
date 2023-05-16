package com.example.geographicatlas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geographicatlas.adapter.CountryDetailAdapter
import com.example.geographicatlas.viewmodel.CountryViewModel

class CountryDetailsActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: CountryDetailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)
        recyclerView = findViewById(R.id.detailsRecyclerView)


        val name = intent.getStringExtra("name")



        supportActionBar?.title = name
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        initRecyclerView()
        initViewModel()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Handle home button click
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = CountryDetailAdapter(this)
        recyclerView.adapter = recyclerAdapter

    }

    private fun initViewModel() {
        val cca2 = intent.getStringExtra("cca2")
        val viewModel: CountryViewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if(it != null) {
                recyclerAdapter.setCountryList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        if (cca2 != null) {
            viewModel.makeAPICallForCountryDetails(cca2 = cca2 )
        }
    }
}
package com.example.geographicatlas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geographicatlas.adapter.CountryListAdapter
import com.example.geographicatlas.viewmodel.CountryViewModel

class MainActivity : AppCompatActivity() {
    lateinit var recyclerAdapter: CountryListAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        initRecyclerView()
        initViewModel()
        supportActionBar?.title = "World Countries"
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = CountryListAdapter(this)
        recyclerView.adapter =recyclerAdapter

    }

    private fun initViewModel() {
        val viewModel: CountryViewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if(it != null) {
                recyclerAdapter.setCountryList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Ошибка при получении списка", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall()
    }
}
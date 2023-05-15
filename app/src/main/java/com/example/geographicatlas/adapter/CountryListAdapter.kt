package com.example.geographicatlas.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geographicatlas.CountryDetailsActivity
import com.example.geographicatlas.R
import com.example.geographicatlas.models.CountriesItem
import com.example.geographicatlas.models.Currencies

class CountryListAdapter(private val activity: Activity) : RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder>() {

    private var countryList: List<CountriesItem> = emptyList()

    fun setCountryList(countryList: List<CountriesItem>) {
        this.countryList = countryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_list_row, parent, false)
        return CountryListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    override fun getItemCount(): Int = countryList.size

    inner class CountryListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val flagImage: ImageView = view.findViewById(R.id.flagImage)
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvCapital: TextView = view.findViewById(R.id.tvCapital)

        fun bind(data: CountriesItem) {
            tvName.text = data.name.common
            tvCapital.text = ""+ data.capital

            itemView.setOnClickListener {
                val intent = Intent(activity, CountryDetailsActivity::class.java)
                intent.apply {
                    putExtra("name", data.name.common)
                    putExtra("flagImage", data.flags.png)
                    putExtra("capital", ""+ data.capital)
                    putExtra("tvPostalCode", ""+ data.postalCode)
                    putExtra("tvPopulation", ""+ data.population)
                    putExtra("tvArea", ""+ data.area)
                    putExtra("tvRegion", data.region)
                    putExtra("tvCurrency", data.currencies?.toString() ?: "")
                }
                activity.startActivity(intent)
            }

            Glide.with(flagImage).load(data.flags.png).into(flagImage)
        }
    }
}
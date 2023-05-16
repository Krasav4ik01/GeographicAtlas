package com.example.geographicatlas.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geographicatlas.R
import com.example.geographicatlas.models.CountriesItem
import com.example.geographicatlas.models.Currencies
import kotlin.math.ceil

class CountryDetailAdapter(val activity: Activity): RecyclerView.Adapter<CountryDetailAdapter.CountryDetailViewHolder>() {

    private var countryList: List<CountriesItem>? = null


    fun setCountryList(countryList: List<CountriesItem>?) {
        this.countryList = countryList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryDetailAdapter.CountryDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_detail_layout, parent, false)

        return CountryDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryDetailAdapter.CountryDetailViewHolder, position: Int) {
        holder.bind(countryList?.get(position)!!, activity)

    }

    override fun getItemCount(): Int {
        if(countryList == null)return 0
        else return countryList?.size!!
    }

    class CountryDetailViewHolder(view : View): RecyclerView.ViewHolder(view){
        val flagImage = view.findViewById<ImageView>(R.id.detailFlagImage)
        val tvCapital = view.findViewById<TextView>(R.id.tvDetailCapital)
        val tvCapitalCoordinates = view.findViewById<TextView>(R.id.tvCapitalCoordinates)
        val tvPopulation = view.findViewById<TextView>(R.id.tvPopulation)
        val tvArea = view.findViewById<TextView>(R.id.tvArea)
        val tvCurrency = view.findViewById<TextView>(R.id.tvDetailCurrency)
        val tvRegion = view.findViewById<TextView>(R.id.tvDetailRegion)
        val tvTimeZone = view.findViewById<TextView>(R.id.tvDetailTimeZone)
        var currency = ArrayList<Currencies>()
        fun bind(data: CountriesItem, activity: Activity) {
            val capitalList = data.capital
            val capitalString = capitalList.joinToString(", ")
            tvCapital.text = ""+ capitalString
            val capCoorList = data.capitalInfo.latlng
            val capitalCoorString = capCoorList.joinToString(", ")
            tvCapitalCoordinates.text = ""+ capitalCoorString
            tvPopulation.text = ""+ ceil(data.population.toDouble()/1000000).toInt() + " mln"
            tvArea.text = "" + data.area.toInt() + " km²"
            tvRegion.text = "" + data.region
//            tvCurrency.text = ""+ data.currencies

            val timezonesList = data.timezones
            val timezonesString = timezonesList.joinToString(", ")
            tvTimeZone.text = ""+ timezonesString


            val stringBuilder = StringBuilder()
            if (data.currencies.isNotEmpty()){



                data.currencies.keys.let {


//                    stringBuilder.append(data.name?.common)



                    for ((i, key) in it.withIndex()) {
                        val currencyName = data.currencies[key]?.name
                        val currencySymbol = data.currencies[key]?.symbol
                        tvCurrency.text = currencyName + " (" + currencySymbol +")"

                        if (it.size > 1 && i < it.size - 1)
                            stringBuilder.append(" , ")

                    }
                    stringBuilder.append(".")
                }
            }
            else{
                tvCurrency.text = ""
            }





            Glide.with(flagImage).load(data.flags.png).into(flagImage)

        }
    }

}

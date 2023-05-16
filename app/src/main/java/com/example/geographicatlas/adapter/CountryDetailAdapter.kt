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
        //        val tvCapitalCoordinates = view.findViewById<TextView>(R.id.tvCapitalCoordinates)
        val tvPopulation = view.findViewById<TextView>(R.id.tvPopulation)
        val tvArea = view.findViewById<TextView>(R.id.tvArea)
        val tvCurrency = view.findViewById<TextView>(R.id.tvDetailCurrency)
        val tvRegion = view.findViewById<TextView>(R.id.tvDetailRegion)
        var currency = ArrayList<Currencies>()
        fun bind(data: CountriesItem, activity: Activity) {
            tvCapital.text = "Capital: "+ data.capital +""
//            tvCapitalCoordinates.text = "Postal Code format: "+ data.postalCode
            tvPopulation.text = "population: "+ data.population
            tvArea.text = "Area: " + data.area
            tvRegion.text = "Region: " + data.region
            tvCurrency.text = "Currency: "+ data.currencies
//            tvCurrency.text = "Currency: "+ data.currencies.EUR



//            for (i in data.currencies.toString().toList()){
//                if (data.currencies.EUR != null){
//                    tvCurrency.text = ""+ data.currencies.EUR
//                }
//                else{
//                    tvCurrency.text = ""
//                }
//            }


            Glide.with(flagImage).load(data.flags.png).into(flagImage)

        }
    }

}

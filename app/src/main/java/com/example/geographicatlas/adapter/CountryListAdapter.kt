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
import com.example.geographicatlas.R
import com.example.geographicatlas.models.CountriesItem
import com.example.geographicatlas.models.Currencies

class CountryListAdapter(val activity: Activity): RecyclerView.Adapter<CountryListAdapter.MyViewHolder>() {

    private var countryList: List<CountriesItem>? = null


    fun setCountryList(countryList: List<CountriesItem>?) {
        this.countryList = countryList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_list_row, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryListAdapter.MyViewHolder, position: Int) {
        holder.bind(countryList?.get(position)!!, activity)
    }

    override fun getItemCount(): Int {
        if(countryList == null)return 0
        else return countryList?.size!!
    }

    class MyViewHolder(view : View): RecyclerView.ViewHolder(view){
        val flagImage = view.findViewById<ImageView>(R.id.flagImage)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvCapital = view.findViewById<TextView>(R.id.tvCapital)
//        val tvRegion = view.findViewById<TextView>(R.id.tvRegion)
        var currency = ArrayList<Currencies>()
        fun bind(data: CountriesItem, activity: Activity) {
            tvName.text = ""+data.name.common+""
            tvCapital.text = ""+ data.capital +""





            Glide.with(flagImage).load(data.flags.png).into(flagImage)

        }
    }
}
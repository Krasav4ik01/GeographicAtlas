package com.example.geographicatlas.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geographicatlas.CountryDetailsActivity
import com.example.geographicatlas.R
import com.example.geographicatlas.models.CountriesItem
import com.example.geographicatlas.models.Currencies
import com.example.geographicatlas.models.ListItem
import kotlin.math.ceil

class CountryListAdapter(private val activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList: List<ListItem> = emptyList()

    fun setCountryList(countryList: List<CountriesItem>) {
        val groupedMap = countryList.groupBy { it.region }.toSortedMap()
//        val sortedMap = groupedMap.toSortedMap()
        val itemList = mutableListOf<ListItem>()

        for ((region, countries) in groupedMap) {
            itemList.add(ListItem.HeaderItem(region))
            for (country in countries) {
                itemList.add(ListItem.CountryItem(country))
            }
        }

        this.itemList = itemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_COUNTRY -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.country_list_row, parent, false)
                CountryListViewHolder(view)
            }
            ITEM_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.header_design, parent, false)
                HeaderViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CountryListViewHolder -> {
                val countryItem = itemList[position] as ListItem.CountryItem
                holder.bind(countryItem.country)

                val isExpanded: Boolean = countryItem.country.isExpandable

                holder.expandableRelativeLayout.visibility =
                    if (isExpanded) View.VISIBLE else View.GONE
                holder.learnMoreBtn.visibility = if (isExpanded) View.VISIBLE else View.GONE

                if (isExpanded) {
                    holder.arrowDown.setImageResource(R.drawable.arrow_up)
                } else {
                    holder.arrowDown.setImageResource(R.drawable.arrow_down)
                }

                holder.arrowDown.setOnClickListener {
                    val country = itemList[position] as ListItem.CountryItem
                    country.country.isExpandable = !country.country.isExpandable
                    notifyItemChanged(position)
                }
            }
            is HeaderViewHolder -> {
                val headerItem = itemList[position] as ListItem.HeaderItem
                holder.bind(headerItem.region)
            }
            else -> throw IllegalArgumentException("Invalid view holder type")
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is ListItem.CountryItem -> ITEM_TYPE_COUNTRY
            is ListItem.HeaderItem -> ITEM_TYPE_HEADER
        }
    }

    companion object {
        private const val ITEM_TYPE_COUNTRY = 0
        private const val ITEM_TYPE_HEADER = 1
    }

    inner class CountryListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val flagImage: ImageView = view.findViewById(R.id.flagImage)
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvCapital: TextView = view.findViewById(R.id.tvCapital)
        private val tvPopulation: TextView = view.findViewById(R.id.tvPopupPopulation)
        private val tvArea: TextView = view.findViewById(R.id.tvPopupArea)
        private val tvCurrency: TextView = view.findViewById(R.id.tvPopupCurrency)
        val expandableRelativeLayout: RelativeLayout = view.findViewById(R.id.expandableRelativeLayout)
        val learnMoreBtn : Button = view.findViewById(R.id.btnLearnMore)
        val arrowDown: ImageView = view.findViewById(R.id.arrow_down)
        fun bind(data: CountriesItem) {
            tvName.text = data.name.common
            if (data.capital != null){
                val capitalList = data.capital
                val capitalString = capitalList.joinToString(", ")
                tvCapital.text = ""+ capitalString
            }

            tvPopulation.text = "Population: "+ ceil(data.population.toDouble()/1000000).toInt() + " mln"
            tvArea.text = "Area: "+ data.area.toInt() + " km2"
            val stringBuilder = StringBuilder()
            if (data.currencies != null && data.currencies.isNotEmpty()) {
                data.currencies.keys.let { keys ->


                    data.currencies.keys.let {


//                    stringBuilder.append(data.name?.common)


                        for ((i, key) in it.withIndex()) {
                            val currencyName = data.currencies[key]?.name
                            val currencySymbol = data.currencies[key]?.symbol
                            tvCurrency.text =
                                "Currency: " + currencyName + " (" + currencySymbol + ")"

                            if (it.size > 1 && i < it.size - 1)
                                stringBuilder.append(" , ")

                        }
                        stringBuilder.append(".")
                    }
                }
            }

            else{
                tvCurrency.text = ""
            }





            learnMoreBtn.setOnClickListener {
                val intent = Intent(activity, CountryDetailsActivity::class.java)
                intent.apply {
                    putExtra("name", data.name.common)

                    putExtra("cca2", data.cca2)
                }
                activity.startActivity(intent)
            }

            Glide.with(flagImage).load(data.flags.png).into(flagImage)
        }
    }

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvContinent: TextView = view.findViewById(R.id.tvContinent)
        fun bind(data: String) {
            tvContinent.text = data
        }
    }
}
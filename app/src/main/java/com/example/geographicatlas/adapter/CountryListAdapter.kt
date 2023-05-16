package com.example.geographicatlas.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
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

        val isExpanded : Boolean = countryList[position].isExpandable

        holder.expandableRelativeLayout.visibility = if(isExpanded) View.VISIBLE else View.GONE
        holder.learnMoreBtn.visibility = if (isExpanded) View.VISIBLE else View.GONE
//        holder.arrowDown.setImageResource(R.drawable.arrow_down) = if (isExpanded) holder.arrowDown.setImageResource(R.drawable.arrow_up) else holder.arrowDown.setImageResource(R.drawable.arrow_down)


        if(isExpanded){
            holder.arrowDown.setImageResource(R.drawable.arrow_up)
        }
        else{
            holder.arrowDown.setImageResource(R.drawable.arrow_down)
        }

        holder.arrowDown.setOnClickListener {
            val countries = countryList[position]
            countries.isExpandable = !countries.isExpandable
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = countryList.size

    inner class CountryListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val flagImage: ImageView = view.findViewById(R.id.flagImage)
        private val tvName: TextView = view.findViewById(R.id.tvName)
        private val tvCapital: TextView = view.findViewById(R.id.tvCapital)
        private val tvPopulation: TextView = view.findViewById(R.id.tvPopupPopulation)
        private val tvArea: TextView = view.findViewById(R.id.tvPopupArea)
        private val tvCurrency: TextView = view.findViewById(R.id.tvPopupCurrency)
        val expandableRelativeLayout: LinearLayout = view.findViewById(R.id.expandableRelativeLayout)
        val learnMoreBtn : Button = view.findViewById(R.id.btnLearnMore)
        val arrowDown: ImageView = view.findViewById(R.id.arrow_down)
        fun bind(data: CountriesItem) {
            tvName.text = data.name.common
            tvCapital.text = ""+ data.capital
            tvPopulation.text = "Population: "+ data.population
            tvArea.text = "Area: "+ data.area.toInt() + " km2"





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
}

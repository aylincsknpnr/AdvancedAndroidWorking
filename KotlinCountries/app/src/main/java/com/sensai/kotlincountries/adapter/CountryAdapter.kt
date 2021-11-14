package com.sensai.kotlincountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sensai.kotlincountries.R
import com.sensai.kotlincountries.model.Country
import com.sensai.kotlincountries.util.downloadFromUrl
import com.sensai.kotlincountries.util.placeholderProgressbar
import com.sensai.kotlincountries.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*
import java.util.zip.Inflater

class CountryAdapter(val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    class CountryViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.name.text = countryList[position].countryName
        holder.view.region.text = countryList[position].countryRegion
        holder.view.setOnClickListener {
            val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }
        holder.view.imageview.downloadFromUrl(countryList[position].imageUrl, placeholderProgressbar(holder.view.context))
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newList: List<Country>) {
        countryList.clear()
        countryList.addAll(newList)
        notifyDataSetChanged()
    }
}
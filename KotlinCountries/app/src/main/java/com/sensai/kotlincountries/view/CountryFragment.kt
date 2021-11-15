package com.sensai.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.sensai.kotlincountries.R
import com.sensai.kotlincountries.adapter.CountryAdapter
import com.sensai.kotlincountries.util.downloadFromUrl
import com.sensai.kotlincountries.util.placeholderProgressbar
import com.sensai.kotlincountries.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*


class CountryFragment : Fragment() {
    private lateinit var viewModel: CountryViewModel

    private var countryUuid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }
        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(uuid = countryUuid)
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country->
            country?.let {
                countryName.text=country.countryName
                countryRegion.text=country.countryRegion
                countryCapital.text=country.countryCapital
                countryCurrency.text=country.countryCurrency
                countryLang.text=country.countryLang
                context?.let {
                    countryImage.downloadFromUrl(country.imageUrl, placeholderProgressbar(it))
                }
            }

        })
    }
}
package com.sensai.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sensai.kotlincountries.model.Country

class FeedViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        val country = Country("Turkey", "Asia", "Ankara", "TRY", "Turkish", "www.ss.com")
        val country2 = Country("Fransa", "Europe", "Paris", "EUR", "French", "www.bb.com")
        val country3 = Country("Almanya", "Europe", "Berlin", "EUR", "German", "www.cc.com")

        val countrylist = arrayListOf<Country>(country, country2, country3)
        countries.value = countrylist
        countryError.value=false
        countryLoading.value=false
    }
}
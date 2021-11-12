package com.sensai.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sensai.kotlincountries.model.Country

class CountryViewModel : ViewModel() {
    val countryLiveData = MutableLiveData<Country>()
    fun getDataFromRoom() {
        val country = Country("Turkey", "Asia", "Ankara", "TRY", "Turkish", "www.dd.com")
        countryLiveData.value = country
    }
}
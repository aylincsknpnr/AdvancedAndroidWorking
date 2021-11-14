package com.sensai.kotlincountries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sensai.kotlincountries.model.Country
import com.sensai.kotlincountries.service.CountryDatabase
import com.sensai.kotlincountries.service.CountryService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FeedViewModel(application: Application) : BaseViewModel(application) {
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    private val countryService = CountryService()

    //Call işlemleri bittiğinde veriyi bu objeye yazıp fragment temizleneğinde işimiz bittğinde kullan at şeklinde bu objeyi ortadan kaldırıoyoruz
    private val disposable = CompositeDisposable()


    fun refreshData() {
        getDataFromApi()
        /*  val country = Country("Turkey", "Asia", "Ankara", "TRY", "Turkish", "www.ss.com")
          val country2 = Country("Fransa", "Europe", "Paris", "EUR", "French", "www.bb.com")
          val country3 = Country("Almanya", "Europe", "Berlin", "EUR", "German", "www.cc.com")

          val countrylist = arrayListOf<Country>(country, country2, country3)
          countries.value = countrylist
          countryError.value=false
          countryLoading.value=false*/
    }

    private fun getDataFromApi() {
        countryLoading.value = true
        disposable.add(
            countryService
                .getData()
                .subscribeOn(Schedulers.newThread()) //nerede zamanlayarak yapacağımızı belirtiyoruz
                .observeOn(AndroidSchedulers.mainThread()) //ana threadde göstereceğimizi söylüyoruz
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {
                        storeInSqlite(t)
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }

                })

        )
    }

    private fun showCountries(countryList: List<Country>) {
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

    private fun storeInSqlite(list: List<Country>) {
        //coroutine
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
           val listLong= dao.insertAll(*list.toTypedArray())
            var i=0
            while(i<list.size){
                list[i].uuid=listLong[i].toInt()
                i=i+1
            }
            showCountries(list)
        }
    }


}
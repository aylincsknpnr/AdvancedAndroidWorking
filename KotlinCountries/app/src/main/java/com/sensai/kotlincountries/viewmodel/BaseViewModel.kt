package com.sensai.kotlincountries.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

//Android view model kullnarak application context kullanıp sayfalar değişip silindipinde çökme olmaması için
abstract  class BaseViewModel(application :Application) : AndroidViewModel(application), CoroutineScope {
    private val job=Job()
    override val coroutineContext: CoroutineContext
        get() =job + Dispatchers.Main // işini yap ardından main threade dön !

    override fun onCleared() {//app kapatıldığında job iptal edilsin
        super.onCleared()
        job.cancel()
    }
}
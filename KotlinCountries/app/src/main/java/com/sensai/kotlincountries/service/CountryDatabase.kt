package com.sensai.kotlincountries.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sensai.kotlincountries.model.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    //Singleton oluşturulmalı Farklı yerlerden verştabanına aynı anda erişmek gerekirse cakışma olmaması için birkez oluşturacağız
    companion object {
        private var instance: CountryDatabase? = null

        //instance yaratırken birden fazla yaratma isteği  olduğunda senkronize sırayla hallet anlamında
        private val lock = Any()
        //başlatıcı da instance yoksa yani veritabanı oluşmamışsa oluşturmak için
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance=it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CountryDatabase::class.java, "countrydatabase"
        ).build()
    }
}
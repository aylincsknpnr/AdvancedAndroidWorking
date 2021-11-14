package com.sensai.kotlincountries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sensai.kotlincountries.model.Country

@Dao
interface CountryDao {
    @Insert
    suspend fun insertAll(vararg countries: Country):List<Long> // primary key döneceği için tipi long

    @Query("SELECT * FROM country ")
    suspend fun getAllCountries():List<Country>

    @Query("SELECT * FROM country WHERE uuid= :countryId")
    suspend fun getCountry(countryId:Int):Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}
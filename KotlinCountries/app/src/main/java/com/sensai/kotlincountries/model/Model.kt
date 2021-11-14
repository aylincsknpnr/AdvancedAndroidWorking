package com.sensai.kotlincountries.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    @ColumnInfo(name= "name")
    @SerializedName("name")
    val countryName: String?,

    @ColumnInfo(name= "region")
    @SerializedName("region")
    val countryRegion: String?,

    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    val countryCapital: String?,

    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val countryCurrency: String?,

    @ColumnInfo(name = "language")
    @SerializedName("language")
    val countryLang: String?,

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    val imageUrl: String?
){
    //Consturctor a eklemedik cunku retrofitte yok ve her class cağırdığımızda primarykey oluşturmak zorunda kalmamak için
    @PrimaryKey(autoGenerate = true)
    var uuid: Int =0
}
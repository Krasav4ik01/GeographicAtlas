package com.example.geographicatlas.constants

import com.example.geographicatlas.models.CountriesItem
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {
    var country: String

    @GET("all")
    fun getCountryList(): Call<List<CountriesItem>>

    @GET("alpha/")
    fun getCountryDetails(): Call<List<CountriesItem>>
}
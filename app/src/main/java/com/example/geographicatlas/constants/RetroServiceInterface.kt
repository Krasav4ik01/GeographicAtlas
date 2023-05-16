package com.example.geographicatlas.constants

import com.example.geographicatlas.models.CountriesItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetroServiceInterface {
    var country: String

    @GET("all")
    fun getCountryList(): Call<List<CountriesItem>>

    @GET("alpha/{cca2}")
    fun getCountryDetail(@Path("cca2") id: String): Call<List<CountriesItem>>
}
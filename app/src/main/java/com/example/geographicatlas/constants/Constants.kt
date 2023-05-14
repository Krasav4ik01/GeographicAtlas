package com.example.geographicatlas.constants

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Constants {

    companion object{
        val API = "https://restcountries.com/v3.1/"
        fun getRetroInstance(): Retrofit{
            return Retrofit.Builder().baseUrl(API).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }



}
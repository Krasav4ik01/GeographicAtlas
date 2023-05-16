package com.example.geographicatlas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geographicatlas.constants.Constants
import com.example.geographicatlas.constants.RetroServiceInterface
import com.example.geographicatlas.models.CountriesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryViewModel: ViewModel()  {
    lateinit var liveDataList: MutableLiveData<List<CountriesItem>>

    init {
        liveDataList = MutableLiveData()
    }


    fun getLiveDataObserver(): MutableLiveData<List<CountriesItem>> {
        return liveDataList
    }

    fun makeAPICall() {
        val retroInstance = Constants.getRetroInstance()
        val retroService  = retroInstance.create(RetroServiceInterface::class.java)
        val call  = retroService.getCountryList()
        call.enqueue(object : Callback<List<CountriesItem>> {
            override fun onFailure(call: Call<List<CountriesItem>>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(
                call: Call<List<CountriesItem>>,
                response: Response<List<CountriesItem>>
            ) {
                liveDataList.postValue(response.body())
            }
        })


    }

    fun makeAPICallForCountryDetails(cca2: String){
        val retroInstance = Constants.getRetroInstance()
        val retroService  = retroInstance.create(RetroServiceInterface::class.java)
        val call  = retroService.getCountryDetail(cca2)
        call.enqueue(object : Callback<List<CountriesItem>> {
            override fun onFailure(call: Call<List<CountriesItem>>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(
                call: Call<List<CountriesItem>>,
                response: Response<List<CountriesItem>>
            ) {
                liveDataList.postValue(response.body())
            }
        })
    }
}
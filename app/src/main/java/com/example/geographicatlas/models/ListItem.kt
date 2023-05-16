package com.example.geographicatlas.models

sealed class ListItem {
    data class CountryItem(val country: CountriesItem) : ListItem()
    data class HeaderItem(val region: String) : ListItem()
}
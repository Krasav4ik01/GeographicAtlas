package com.example.geographicatlas.models

data class CountriesItem(
    val altSpellings: List<String>,
    val area: Double,
    val borders: List<String>,
    val capital: List<String>,
    val capitalInfo: CapitalInfo,
    val car: Car,
    val cca2: String,
    val cca3: String,
    val ccn3: String,
    val cioc: String,
    val coatOfArms: CoatOfArms,
    val continents: List<String>,
    val currencies: Currencies,
    val flag: String,
    val flags: Flags,
    val languages: Languages,
    val maps: Maps,
    val name: Name,
    val population: Int,
    val postalCode: PostalCode,
    val region: String,
    val subregion: String,
    val timezones: List<String>,
    val unMember: Boolean
)
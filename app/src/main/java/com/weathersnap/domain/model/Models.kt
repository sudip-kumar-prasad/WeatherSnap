package com.weathersnap.domain.model

data class City(
    val id: Long,
    val name: String,
    val country: String,
    val admin1: String?,
    val latitude: Double,
    val longitude: Double
)

data class WeatherData(
    val cityName: String,
    val country: String,
    val temperature: Double,
    val humidity: Int,
    val windSpeed: Double,
    val pressure: Double,
    val weatherCode: Int,
    val condition: String
)

data class Report(
    val id: Long = 0,
    val cityName: String,
    val condition: String,
    val temperature: Double,
    val humidity: Int,
    val windSpeed: Double,
    val pressure: Double,
    val imagePath: String,
    val originalSizeBytes: Long,
    val compressedSizeBytes: Long,
    val notes: String,
    val timestamp: Long
)

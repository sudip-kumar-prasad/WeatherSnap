package com.weathersnap.data.repository

import com.weathersnap.data.remote.api.GeocodingApi
import com.weathersnap.data.remote.api.WeatherApi
import com.weathersnap.domain.model.City
import com.weathersnap.domain.model.WeatherData
import com.weathersnap.util.WeatherCodeMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val geocodingApi: GeocodingApi,
    private val weatherApi: WeatherApi
) {
    suspend fun searchCity(query: String): List<City> = withContext(Dispatchers.IO) {
        val response = geocodingApi.searchCity(query)
        response.results?.map {
            City(it.id, it.name, it.country, it.admin1, it.latitude, it.longitude)
        } ?: emptyList()
    }

    suspend fun getWeather(city: City): WeatherData = withContext(Dispatchers.IO) {
        val response = weatherApi.getWeather(city.latitude, city.longitude)
        val current = response.current
        WeatherData(
            cityName = city.name,
            country = "${city.admin1 ?: ""}, ${city.country}",
            temperature = current.temperature_2m,
            humidity = current.relative_humidity_2m,
            windSpeed = current.wind_speed_10m,
            pressure = current.surface_pressure,
            weatherCode = current.weather_code,
            condition = WeatherCodeMapper.mapCodeToString(current.weather_code)
        )
    }
}

package com.weathersnap.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.weathersnap.data.repository.WeatherRepository
import com.weathersnap.domain.model.City
import com.weathersnap.domain.model.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.net.URLEncoder
import javax.inject.Inject

sealed class WeatherUiState {
    object Idle : WeatherUiState()
    object Loading : WeatherUiState()
    data class Success(val weather: WeatherData) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}

@OptIn(FlowPreview::class)
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _suggestions = MutableStateFlow<List<City>>(emptyList())
    val suggestions = _suggestions.asStateFlow()

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private var selectedCity: City? = null

    private val suggestionsCache = HashMap<String, List<City>>()

    init {
        _query
            .debounce(400)
            .filter { it.length > 2 }
            .distinctUntilChanged()
            .onEach { q ->
                // Don't search for suggestions if the query matches the selected city
                if (q == selectedCity?.name) {
                    _suggestions.value = emptyList()
                    return@onEach
                }
                
                if (suggestionsCache.containsKey(q)) {
                    _suggestions.value = suggestionsCache[q]!!
                } else {
                    try {
                        val results = repository.searchCity(q)
                        suggestionsCache[q] = results
                        _suggestions.value = results
                    } catch (e: Exception) {
                        _suggestions.value = emptyList()
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
        if (newQuery.length <= 2) {
            _suggestions.value = emptyList()
        }
    }

    fun onCitySelected(city: City) {
        _suggestions.value = emptyList()
        _query.value = city.name
        selectedCity = city
    }

    fun searchWeather() {
        selectedCity?.let { fetchWeather(it) }
    }

    private fun fetchWeather(city: City) {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            try {
                val weather = repository.getWeather(city)
                _uiState.value = WeatherUiState.Success(weather)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    fun getWeatherDataJson(weather: WeatherData): String {
        return URLEncoder.encode(Gson().toJson(weather), "UTF-8")
    }

    fun retry() {
        // Retry logic could be implemented here if needed
    }
}

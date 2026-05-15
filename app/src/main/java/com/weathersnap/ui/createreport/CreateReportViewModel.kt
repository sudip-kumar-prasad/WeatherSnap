package com.weathersnap.ui.createreport

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.weathersnap.data.repository.ReportRepository
import com.weathersnap.domain.model.Report
import com.weathersnap.domain.model.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreateReportViewModel @Inject constructor(
    private val repository: ReportRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherData?>(null)
    val weatherData = _weatherData.asStateFlow()

    private val _imagePath = savedStateHandle.getStateFlow<String?>("image_path", null)
    val imagePath = _imagePath

    private val _notes = savedStateHandle.getStateFlow("notes", "")
    val notes = _notes

    private val _originalSizeBytes = savedStateHandle.getStateFlow("original_size", 0L)
    val originalSizeBytes = _originalSizeBytes

    private val _compressedSizeBytes = savedStateHandle.getStateFlow("compressed_size", 0L)
    val compressedSizeBytes = _compressedSizeBytes

    private var draftDiscarded = false

    fun initWeatherData(json: String) {
        if (_weatherData.value == null) {
            _weatherData.value = Gson().fromJson(json, WeatherData::class.java)
        }
    }

    fun onImageCaptured(data: String) {
        val parts = data.split("|")
        if (parts.size == 3) {
            savedStateHandle["image_path"] = parts[0]
            savedStateHandle["original_size"] = parts[1].toLong()
            savedStateHandle["compressed_size"] = parts[2].toLong()
        }
    }

    fun onNotesChange(newNotes: String) {
        savedStateHandle["notes"] = newNotes
    }

    fun saveReport(onSuccess: () -> Unit) {
        val weather = _weatherData.value ?: return
        val path = _imagePath.value ?: return
        
        viewModelScope.launch {
            val report = Report(
                cityName = weather.cityName,
                condition = weather.condition,
                temperature = weather.temperature,
                humidity = weather.humidity,
                windSpeed = weather.windSpeed,
                pressure = weather.pressure,
                imagePath = path,
                originalSizeBytes = _originalSizeBytes.value,
                compressedSizeBytes = _compressedSizeBytes.value,
                notes = _notes.value,
                timestamp = System.currentTimeMillis()
            )
            repository.saveReport(report)
            clearDraft()
            onSuccess()
        }
    }

    private fun clearDraft() {
        savedStateHandle["image_path"] = null
        savedStateHandle["notes"] = ""
        savedStateHandle["original_size"] = 0L
        savedStateHandle["compressed_size"] = 0L
        draftDiscarded = true
    }

    override fun onCleared() {
        super.onCleared()
        if (!draftDiscarded) {
            _imagePath.value?.let { path ->
                val file = File(path)
                if (file.exists()) {
                    file.delete()
                }
            }
        }
    }
}

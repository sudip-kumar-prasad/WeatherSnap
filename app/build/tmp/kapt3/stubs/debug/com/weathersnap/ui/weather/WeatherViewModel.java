package com.weathersnap.ui.weather;

import androidx.lifecycle.ViewModel;
import com.google.gson.Gson;
import com.weathersnap.data.repository.WeatherRepository;
import com.weathersnap.domain.model.City;
import com.weathersnap.domain.model.WeatherData;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.flow.StateFlow;
import java.net.URLEncoder;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\nH\u0002J\u000e\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\nJ\u000e\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u0007J\u0006\u0010!\u001a\u00020\u0019J\u0006\u0010\"\u001a\u00020\u0019R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R \u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010\u00a8\u0006#"}, d2 = {"Lcom/weathersnap/ui/weather/WeatherViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/weathersnap/data/repository/WeatherRepository;", "(Lcom/weathersnap/data/repository/WeatherRepository;)V", "_query", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_suggestions", "", "Lcom/weathersnap/domain/model/City;", "_uiState", "Lcom/weathersnap/ui/weather/WeatherUiState;", "query", "Lkotlinx/coroutines/flow/StateFlow;", "getQuery", "()Lkotlinx/coroutines/flow/StateFlow;", "selectedCity", "suggestions", "getSuggestions", "suggestionsCache", "Ljava/util/HashMap;", "uiState", "getUiState", "fetchWeather", "", "city", "getWeatherDataJson", "weather", "Lcom/weathersnap/domain/model/WeatherData;", "onCitySelected", "onQueryChange", "newQuery", "retry", "searchWeather", "app_debug"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.FlowPreview.class})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class WeatherViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.weathersnap.data.repository.WeatherRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _query = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> query = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.weathersnap.domain.model.City>> _suggestions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.weathersnap.domain.model.City>> suggestions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.weathersnap.ui.weather.WeatherUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.weathersnap.ui.weather.WeatherUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private com.weathersnap.domain.model.City selectedCity;
    @org.jetbrains.annotations.NotNull()
    private final java.util.HashMap<java.lang.String, java.util.List<com.weathersnap.domain.model.City>> suggestionsCache = null;
    
    @javax.inject.Inject()
    public WeatherViewModel(@org.jetbrains.annotations.NotNull()
    com.weathersnap.data.repository.WeatherRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.weathersnap.domain.model.City>> getSuggestions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.weathersnap.ui.weather.WeatherUiState> getUiState() {
        return null;
    }
    
    public final void onQueryChange(@org.jetbrains.annotations.NotNull()
    java.lang.String newQuery) {
    }
    
    public final void onCitySelected(@org.jetbrains.annotations.NotNull()
    com.weathersnap.domain.model.City city) {
    }
    
    public final void searchWeather() {
    }
    
    private final void fetchWeather(com.weathersnap.domain.model.City city) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWeatherDataJson(@org.jetbrains.annotations.NotNull()
    com.weathersnap.domain.model.WeatherData weather) {
        return null;
    }
    
    public final void retry() {
    }
}
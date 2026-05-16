package com.weathersnap.data.repository;

import com.weathersnap.data.remote.api.GeocodingApi;
import com.weathersnap.data.remote.api.WeatherApi;
import com.weathersnap.domain.model.City;
import com.weathersnap.domain.model.WeatherData;
import com.weathersnap.util.WeatherCodeMapper;
import kotlinx.coroutines.Dispatchers;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/weathersnap/data/repository/WeatherRepository;", "", "geocodingApi", "Lcom/weathersnap/data/remote/api/GeocodingApi;", "weatherApi", "Lcom/weathersnap/data/remote/api/WeatherApi;", "(Lcom/weathersnap/data/remote/api/GeocodingApi;Lcom/weathersnap/data/remote/api/WeatherApi;)V", "getWeather", "Lcom/weathersnap/domain/model/WeatherData;", "city", "Lcom/weathersnap/domain/model/City;", "(Lcom/weathersnap/domain/model/City;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchCity", "", "query", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class WeatherRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.weathersnap.data.remote.api.GeocodingApi geocodingApi = null;
    @org.jetbrains.annotations.NotNull()
    private final com.weathersnap.data.remote.api.WeatherApi weatherApi = null;
    
    @javax.inject.Inject()
    public WeatherRepository(@org.jetbrains.annotations.NotNull()
    com.weathersnap.data.remote.api.GeocodingApi geocodingApi, @org.jetbrains.annotations.NotNull()
    com.weathersnap.data.remote.api.WeatherApi weatherApi) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchCity(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.weathersnap.domain.model.City>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getWeather(@org.jetbrains.annotations.NotNull()
    com.weathersnap.domain.model.City city, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.weathersnap.domain.model.WeatherData> $completion) {
        return null;
    }
}
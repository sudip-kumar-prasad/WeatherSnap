package com.weathersnap.data.remote.api;

import com.weathersnap.data.remote.model.GeocodingResponse;
import com.weathersnap.data.remote.model.WeatherResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J6\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/weathersnap/data/remote/api/WeatherApi;", "", "getWeather", "Lcom/weathersnap/data/remote/model/WeatherResponse;", "latitude", "", "longitude", "current", "", "timezone", "(DDLjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface WeatherApi {
    
    @retrofit2.http.GET(value = "v1/forecast")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getWeather(@retrofit2.http.Query(value = "latitude")
    double latitude, @retrofit2.http.Query(value = "longitude")
    double longitude, @retrofit2.http.Query(value = "current")
    @org.jetbrains.annotations.NotNull()
    java.lang.String current, @retrofit2.http.Query(value = "timezone")
    @org.jetbrains.annotations.NotNull()
    java.lang.String timezone, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.weathersnap.data.remote.model.WeatherResponse> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}
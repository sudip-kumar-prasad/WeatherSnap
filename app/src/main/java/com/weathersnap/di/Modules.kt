package com.weathersnap.di

import android.content.Context
import androidx.room.Room
import com.weathersnap.BuildConfig
import com.weathersnap.data.local.dao.ReportDao
import com.weathersnap.data.local.db.WeatherSnapDatabase
import com.weathersnap.data.remote.api.GeocodingApi
import com.weathersnap.data.remote.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }
        .build()

    @Provides
    @Singleton
    fun provideGeocodingApi(client: OkHttpClient): GeocodingApi = Retrofit.Builder()
        .baseUrl("https://geocoding-api.open-meteo.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GeocodingApi::class.java)

    @Provides
    @Singleton
    fun provideWeatherApi(client: OkHttpClient): WeatherApi = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherSnapDatabase =
        Room.databaseBuilder(context, WeatherSnapDatabase::class.java, "weathersnap.db").build()

    @Provides
    fun provideReportDao(db: WeatherSnapDatabase): ReportDao = db.reportDao()
}

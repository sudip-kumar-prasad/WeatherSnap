package com.weathersnap;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = WeatherSnapApp.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface WeatherSnapApp_GeneratedInjector {
  void injectWeatherSnapApp(WeatherSnapApp weatherSnapApp);
}

package com.weathersnap.ui.navigation;

import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.runtime.Composable;
import androidx.navigation.NavType;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0007\b\t\nB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0004\u000b\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/weathersnap/ui/navigation/Screen;", "", "route", "", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "Camera", "CreateReport", "Reports", "Weather", "Lcom/weathersnap/ui/navigation/Screen$Camera;", "Lcom/weathersnap/ui/navigation/Screen$CreateReport;", "Lcom/weathersnap/ui/navigation/Screen$Reports;", "Lcom/weathersnap/ui/navigation/Screen$Weather;", "app_debug"})
public abstract class Screen {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String route = null;
    
    private Screen(java.lang.String route) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRoute() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/weathersnap/ui/navigation/Screen$Camera;", "Lcom/weathersnap/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Camera extends com.weathersnap.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.weathersnap.ui.navigation.Screen.Camera INSTANCE = null;
        
        private Camera() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u00a8\u0006\u0006"}, d2 = {"Lcom/weathersnap/ui/navigation/Screen$CreateReport;", "Lcom/weathersnap/ui/navigation/Screen;", "()V", "createRoute", "", "weatherJson", "app_debug"})
    public static final class CreateReport extends com.weathersnap.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.weathersnap.ui.navigation.Screen.CreateReport INSTANCE = null;
        
        private CreateReport() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String createRoute(@org.jetbrains.annotations.NotNull()
        java.lang.String weatherJson) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/weathersnap/ui/navigation/Screen$Reports;", "Lcom/weathersnap/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Reports extends com.weathersnap.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.weathersnap.ui.navigation.Screen.Reports INSTANCE = null;
        
        private Reports() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/weathersnap/ui/navigation/Screen$Weather;", "Lcom/weathersnap/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Weather extends com.weathersnap.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.weathersnap.ui.navigation.Screen.Weather INSTANCE = null;
        
        private Weather() {
        }
    }
}
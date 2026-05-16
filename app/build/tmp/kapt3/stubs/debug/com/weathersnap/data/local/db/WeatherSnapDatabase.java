package com.weathersnap.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.weathersnap.data.local.dao.ReportDao;
import com.weathersnap.data.local.entity.ReportEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0005"}, d2 = {"Lcom/weathersnap/data/local/db/WeatherSnapDatabase;", "Landroidx/room/RoomDatabase;", "()V", "reportDao", "Lcom/weathersnap/data/local/dao/ReportDao;", "app_debug"})
@androidx.room.Database(entities = {com.weathersnap.data.local.entity.ReportEntity.class}, version = 1, exportSchema = false)
public abstract class WeatherSnapDatabase extends androidx.room.RoomDatabase {
    
    public WeatherSnapDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.weathersnap.data.local.dao.ReportDao reportDao();
}
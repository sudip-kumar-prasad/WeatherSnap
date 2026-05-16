package com.weathersnap.data.repository;

import com.weathersnap.data.local.dao.ReportDao;
import com.weathersnap.data.local.entity.ReportEntity;
import com.weathersnap.domain.model.Report;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\fJ\f\u0010\r\u001a\u00020\b*\u00020\u000eH\u0002J\f\u0010\u000f\u001a\u00020\u000e*\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/weathersnap/data/repository/ReportRepository;", "", "reportDao", "Lcom/weathersnap/data/local/dao/ReportDao;", "(Lcom/weathersnap/data/local/dao/ReportDao;)V", "getAllReports", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/weathersnap/domain/model/Report;", "saveReport", "", "report", "(Lcom/weathersnap/domain/model/Report;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDomain", "Lcom/weathersnap/data/local/entity/ReportEntity;", "toEntity", "app_debug"})
public final class ReportRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.weathersnap.data.local.dao.ReportDao reportDao = null;
    
    @javax.inject.Inject()
    public ReportRepository(@org.jetbrains.annotations.NotNull()
    com.weathersnap.data.local.dao.ReportDao reportDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.weathersnap.domain.model.Report>> getAllReports() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveReport(@org.jetbrains.annotations.NotNull()
    com.weathersnap.domain.model.Report report, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    private final com.weathersnap.domain.model.Report toDomain(com.weathersnap.data.local.entity.ReportEntity $this$toDomain) {
        return null;
    }
    
    private final com.weathersnap.data.local.entity.ReportEntity toEntity(com.weathersnap.domain.model.Report $this$toEntity) {
        return null;
    }
}
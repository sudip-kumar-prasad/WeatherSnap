package com.weathersnap.ui.reports;

import androidx.lifecycle.ViewModel;
import com.weathersnap.data.repository.ReportRepository;
import com.weathersnap.domain.model.Report;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/weathersnap/ui/reports/ReportsViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/weathersnap/data/repository/ReportRepository;", "(Lcom/weathersnap/data/repository/ReportRepository;)V", "reports", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/weathersnap/domain/model/Report;", "getReports", "()Lkotlinx/coroutines/flow/StateFlow;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ReportsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.weathersnap.domain.model.Report>> reports = null;
    
    @javax.inject.Inject()
    public ReportsViewModel(@org.jetbrains.annotations.NotNull()
    com.weathersnap.data.repository.ReportRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.weathersnap.domain.model.Report>> getReports() {
        return null;
    }
}
package com.weathersnap.ui.createreport;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.gson.Gson;
import com.weathersnap.data.repository.ReportRepository;
import com.weathersnap.domain.model.Report;
import com.weathersnap.domain.model.WeatherData;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.io.File;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u000e\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u000bJ\b\u0010\"\u001a\u00020\u001fH\u0014J\u000e\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u000bJ\u000e\u0010%\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\u000bJ\u0014\u0010\'\u001a\u00020\u001f2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001f0)R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013\u00a8\u0006*"}, d2 = {"Lcom/weathersnap/ui/createreport/CreateReportViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/weathersnap/data/repository/ReportRepository;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "(Lcom/weathersnap/data/repository/ReportRepository;Landroidx/lifecycle/SavedStateHandle;)V", "_compressedSizeBytes", "Lkotlinx/coroutines/flow/StateFlow;", "", "_imagePath", "", "_notes", "_originalSizeBytes", "_weatherData", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/weathersnap/domain/model/WeatherData;", "compressedSizeBytes", "getCompressedSizeBytes", "()Lkotlinx/coroutines/flow/StateFlow;", "draftDiscarded", "", "imagePath", "getImagePath", "notes", "getNotes", "originalSizeBytes", "getOriginalSizeBytes", "weatherData", "getWeatherData", "clearDraft", "", "initWeatherData", "json", "onCleared", "onImageCaptured", "data", "onNotesChange", "newNotes", "saveReport", "onSuccess", "Lkotlin/Function0;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CreateReportViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.weathersnap.data.repository.ReportRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.SavedStateHandle savedStateHandle = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.weathersnap.domain.model.WeatherData> _weatherData = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.weathersnap.domain.model.WeatherData> weatherData = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> _imagePath = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> imagePath = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> _notes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> notes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> _originalSizeBytes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> originalSizeBytes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> _compressedSizeBytes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> compressedSizeBytes = null;
    private boolean draftDiscarded = false;
    
    @javax.inject.Inject()
    public CreateReportViewModel(@org.jetbrains.annotations.NotNull()
    com.weathersnap.data.repository.ReportRepository repository, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.weathersnap.domain.model.WeatherData> getWeatherData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getImagePath() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getNotes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getOriginalSizeBytes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getCompressedSizeBytes() {
        return null;
    }
    
    public final void initWeatherData(@org.jetbrains.annotations.NotNull()
    java.lang.String json) {
    }
    
    public final void onImageCaptured(@org.jetbrains.annotations.NotNull()
    java.lang.String data) {
    }
    
    public final void onNotesChange(@org.jetbrains.annotations.NotNull()
    java.lang.String newNotes) {
    }
    
    public final void saveReport(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess) {
    }
    
    private final void clearDraft() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}
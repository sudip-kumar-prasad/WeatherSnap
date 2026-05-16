package com.weathersnap.data.repository

import com.weathersnap.data.local.dao.ReportDao
import com.weathersnap.data.local.entity.ReportEntity
import com.weathersnap.domain.model.Report
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReportRepository @Inject constructor(
    private val reportDao: ReportDao
) {
    fun getAllReports(): Flow<List<Report>> {
        return reportDao.getAllReports().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun saveReport(report: Report) = withContext(Dispatchers.IO) {
        reportDao.insert(report.toEntity())
    }

    suspend fun deleteReport(report: Report) = withContext(Dispatchers.IO) {
        // Delete from DB
        reportDao.deleteReport(report.id)
        // Delete image file from disk
        try {
            val file = java.io.File(report.imagePath)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun ReportEntity.toDomain() = Report(
        id = id,
        cityName = cityName,
        condition = condition,
        temperature = temperature,
        humidity = humidity,
        windSpeed = windSpeed,
        pressure = pressure,
        imagePath = imagePath,
        originalSizeBytes = originalSizeBytes,
        compressedSizeBytes = compressedSizeBytes,
        notes = notes,
        timestamp = timestamp
    )

    private fun Report.toEntity() = ReportEntity(
        cityName = cityName,
        condition = condition,
        temperature = temperature,
        humidity = humidity,
        windSpeed = windSpeed,
        pressure = pressure,
        imagePath = imagePath,
        originalSizeBytes = originalSizeBytes,
        compressedSizeBytes = compressedSizeBytes,
        notes = notes,
        timestamp = timestamp
    )
}

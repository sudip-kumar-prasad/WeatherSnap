package com.weathersnap.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

data class CompressResult(
    val compressedFile: File,
    val originalSizeBytes: Long,
    val compressedSizeBytes: Long
)

object ImageCompressor {
    suspend fun compress(originalFile: File, context: Context): CompressResult = withContext(Dispatchers.IO) {
        val originalSizeBytes = originalFile.length()
        
        val bitmap = BitmapFactory.decodeFile(originalFile.absolutePath)
        val compressedFile = File(context.cacheDir, "compressed_${System.currentTimeMillis()}.jpg")
        
        FileOutputStream(compressedFile).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out)
        }
        
        val compressedSizeBytes = compressedFile.length()
        
        // Cleanup original file as per requirement
        if (originalFile.exists()) {
            originalFile.delete()
        }
        
        CompressResult(compressedFile, originalSizeBytes, compressedSizeBytes)
    }
}

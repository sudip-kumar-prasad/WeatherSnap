package com.weathersnap.ui.createreport

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.weathersnap.ui.weather.WeatherCard
import com.weathersnap.domain.model.WeatherData
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReportScreen(
    weatherJson: String,
    onNavigateToCamera: () -> Unit,
    onNavigateToReports: () -> Unit,
    onBack: () -> Unit,
    navController: NavController,
    viewModel: CreateReportViewModel = hiltViewModel()
) {
    LaunchedEffect(weatherJson) {
        viewModel.initWeatherData(weatherJson)
    }

    val weatherData by viewModel.weatherData.collectAsState()
    val imagePath by viewModel.imagePath.collectAsState()
    val notes by viewModel.notes.collectAsState()
    val originalSize by viewModel.originalSizeBytes.collectAsState()
    val compressedSize by viewModel.compressedSizeBytes.collectAsState()

    // Retrieve result from camera screen
    val capturedPath = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>("captured_image_path")
        ?.observeAsState()

    LaunchedEffect(capturedPath?.value) {
        capturedPath?.value?.let { path ->
            viewModel.onImageCaptured(path)
            navController.currentBackStackEntry?.savedStateHandle?.remove<String>("captured_image_path")
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Create Report")
                        Text("Capture, compress, annotate", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            weatherData?.let { data ->
                WeatherCardPreview(data)
            }

            Spacer(modifier = Modifier.height(24.dp))

            ImagePreviewArea(imagePath, onNavigateToCamera)

            if (imagePath != null) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Original: ${originalSize / 1024} KB", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
                    Text("Compressed: ${compressedSize / 1024} KB", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = notes,
                onValueChange = { viewModel.onNotesChange(it) },
                label = { Text("Field Notes") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 5,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.saveReport(onNavigateToReports) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = imagePath != null,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Save Report", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
fun WeatherCardPreview(weather: WeatherData) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(weather.cityName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(weather.condition, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
            }
            Text("${weather.temperature.toInt()}°C", style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Composable
fun ImagePreviewArea(imagePath: String?, onCapture: () -> Unit) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth().height(200.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = onCapture
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (imagePath == null) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.PhotoCamera, contentDescription = null, modifier = Modifier.size(48.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Photo preview", style = MaterialTheme.typography.bodyMedium)
                    Text("Tap to capture", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
                }
            } else {
                AnimatedVisibility(visible = true, enter = fadeIn()) {
                    AsyncImage(
                        model = File(imagePath),
                        contentDescription = "Captured Photo",
                        modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

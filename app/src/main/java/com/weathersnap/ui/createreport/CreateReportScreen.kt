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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.weathersnap.ui.weather.WeatherCard
import com.weathersnap.domain.model.WeatherData
import java.io.File
import java.util.Date

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
        containerColor = Color(0xFF1A1C14) // Dark Forest Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Header Card (Matching Theme)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFC4D596))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFFE2F4A6), Color(0xFF94A56E))
                            )
                        )
                        .padding(24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Create Report",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1C14)
                        )
                        Text(
                            "Capture, compress, annotate",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF1A1C14).copy(alpha = 0.7f)
                        )
                    }
                    Button(
                        onClick = onBack,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A1C14)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Back", color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Weather Details Card
            weatherData?.let { data ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF23261D))
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(data.cityName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
                                Text(data.condition, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                            }
                            Text("${data.temperature.toInt()}°C", style = MaterialTheme.typography.headlineLarge, color = Color(0xFFC4D596))
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            WeatherStatBox("Humidity", "${data.humidity}%", Color(0xFF4CAF50).copy(alpha = 0.2f), Color(0xFF4CAF50))
                            WeatherStatBox("Wind", "${data.windSpeed} m/s", Color(0xFF2196F3).copy(alpha = 0.2f), Color(0xFF2196F3))
                            WeatherStatBox("Pressure", "${data.pressure.toInt()}", Color(0xFFFF9800).copy(alpha = 0.2f), Color(0xFFFF9800))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Photo Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF23261D))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFF4A5534), Color(0xFF1E352F))
                                ),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (imagePath != null) {
                            AsyncImage(
                                model = File(imagePath!!),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(16.dp)),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Text("Photo preview", color = Color.White.copy(alpha = 0.5f))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onNavigateToCamera,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (imagePath != null) Color(0xFFC4D596) else Color(0xFF4A5534)
                        ),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Text(
                            if (imagePath != null) "Retake Photo" else "Capture Photo",
                            color = if (imagePath != null) Color(0xFF1A1C14) else Color.White
                        )
                    }

                    if (imagePath != null) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color(0xFF322A1F), RoundedCornerShape(12.dp))
                                    .padding(12.dp)
                            ) {
                                Column {
                                    Text("Original", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                                    Text("${originalSize / 1024} KB", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = Color(0xFFE69138))
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color(0xFF1F2D26), RoundedCornerShape(12.dp))
                                    .padding(12.dp)
                            ) {
                                Column {
                                    Text("Compressed", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                                    Text("${compressedSize / 1024} KB", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Notes Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF23261D))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Field Notes", style = MaterialTheme.typography.titleSmall, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = notes,
                        onValueChange = { viewModel.onNotesChange(it) },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Enter observations...", color = Color.Gray) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFC4D596),
                            unfocusedBorderColor = Color(0xFF44483D),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 5. Save Button
            Button(
                onClick = { viewModel.saveReport(onNavigateToReports) },
                modifier = Modifier.fillMaxWidth().height(64.dp),
                enabled = imagePath != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC4D596),
                    disabledContainerColor = Color(0xFF2D3126)
                ),
                shape = RoundedCornerShape(32.dp)
            ) {
                Text("Save Report", style = MaterialTheme.typography.titleMedium, color = Color(0xFF1A1C14))
            }
        }
    }
}

@Composable
fun WeatherStatBox(label: String, value: String, bgColor: Color, textColor: Color) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .background(bgColor, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Text(value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = textColor)
    }
}

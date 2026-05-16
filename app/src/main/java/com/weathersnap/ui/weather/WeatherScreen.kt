package com.weathersnap.ui.weather

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.weathersnap.domain.model.City
import com.weathersnap.domain.model.WeatherData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    onNavigateToCreateReport: (String) -> Unit,
    onNavigateToReports: () -> Unit,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val query by viewModel.query.collectAsState()
    val suggestions by viewModel.suggestions.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = Color(0xFF1A1C14) // Dark Forest Background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Header Card
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
                            "WeatherSnap",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1C14)
                        )
                        Text(
                            "Live weather reports with camera evidence",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF1A1C14).copy(alpha = 0.7f)
                        )
                    }
                    Button(
                        onClick = onNavigateToReports,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF38431E)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Reports", color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Search Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF23261D))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = query,
                            onValueChange = { viewModel.onQueryChange(it) },
                            label = { Text("City") },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFC4D596),
                                unfocusedBorderColor = Color(0xFF44483D)
                            )
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Button(
                            onClick = { /* ViewModel handles this via suggestions */ },
                            modifier = Modifier.height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC4D596)),
                            shape = RoundedCornerShape(28.dp)
                        ) {
                            Text("Search", color = Color(0xFF1A1C14))
                        }
                    }
                    Text(
                        "Enter more than 2 letters to start city suggestions.",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFC4D596).copy(alpha = 0.6f),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // City Suggestions (Overlay logic)
            Box(modifier = Modifier.fillMaxWidth().zIndex(1f)) {
                Column {
                    AnimatedVisibility(
                        visible = suggestions.isNotEmpty(),
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFF2D3126))
                        ) {
                            LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
                                items(suggestions) { city ->
                                    ListItem(
                                        headlineContent = { Text(city.name, color = Color.White) },
                                        supportingContent = { Text("${city.admin1 ?: ""}, ${city.country}", color = Color.Gray) },
                                        modifier = Modifier.clickable { viewModel.onCitySelected(city) },
                                        colors = ListItemDefaults.colors(containerColor = Color.Transparent)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Content Area (Idle/Success/Loading)
            AnimatedContent(
                targetState = uiState,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "WeatherState"
            ) { state ->
                when (state) {
                    is WeatherUiState.Idle -> {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF2D3126))
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(160.dp)
                                        .background(
                                            brush = Brush.linearGradient(
                                                colors = listOf(Color(0xFF4A5534), Color(0xFF1E352F))
                                            ),
                                            shape = RoundedCornerShape(16.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        "Search. Capture. Save.",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    "No weather loaded",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    "Enter more than 2 letters, choose a city, then search.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                    is WeatherUiState.Loading -> {
                        CircularProgressIndicator(color = Color(0xFFC4D596), modifier = Modifier.padding(32.dp))
                    }
                    is WeatherUiState.Success -> {
                        WeatherCard(
                            weather = state.weather,
                            onCreateReport = { onNavigateToCreateReport(viewModel.getWeatherDataJson(state.weather)) },
                            onSeeReports = onNavigateToReports
                        )
                    }
                    is WeatherUiState.Error -> {
                        Text(state.message, color = Color.Red, modifier = Modifier.padding(16.dp))
                        Button(onClick = { viewModel.retry() }) { Text("Retry") }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherCard(
    weather: WeatherData,
    onCreateReport: () -> Unit,
    onSeeReports: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(weather.cityName, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Text(weather.country, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text("${weather.temperature.toInt()}°C", fontSize = 64.sp, fontWeight = FontWeight.Light)
                Text(weather.condition, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    WeatherDetailChip("Humidity", "${weather.humidity}%")
                    WeatherDetailChip("Wind", "${weather.windSpeed} km/h")
                    WeatherDetailChip("Pressure", "${weather.pressure.toInt()} hPa")
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        FilledTonalButton(
            onClick = onCreateReport,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Create Report", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onSeeReports,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Reports", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun WeatherDetailChip(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        SuggestionChip(
            onClick = {},
            label = { Text(value, fontWeight = FontWeight.Bold) },
            colors = SuggestionChipDefaults.suggestionChipColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                labelColor = MaterialTheme.colorScheme.primary
            ),
            border = null
        )
        Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
    }
}

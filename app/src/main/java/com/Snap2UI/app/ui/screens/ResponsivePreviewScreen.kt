package com.Snap2UI.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Snap2UI.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponsivePreviewScreen(
    onNavigateBack: () -> Unit
) {
    var deviceMode by remember { mutableStateOf("Standard Phone") }
    val devices = listOf("Small Phone", "Standard Phone", "Large Phone", "Tablet")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Responsive UI Preview") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkNavyBackground)
            )
        },
        containerColor = DarkNavyBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScrollableTabRow(
                selectedTabIndex = devices.indexOf(deviceMode),
                containerColor = DarkNavyCard,
                contentColor = AccentBlue,
                edgePadding = 0.dp
            ) {
                devices.forEach { device ->
                    Tab(
                        selected = deviceMode == device,
                        onClick = { deviceMode = device },
                        text = { Text(device, color = if (deviceMode == device) AccentBlue else TextSecondary) }
                    )
                }
            }

            val widthFactor = when (deviceMode) {
                "Small Phone" -> 0.7f
                "Standard Phone" -> 0.85f
                "Large Phone" -> 0.95f
                else -> 1f
            }

            Card(
                modifier = Modifier
                    .fillMaxHeight(0.85f)
                    .fillMaxWidth(widthFactor),
                colors = CardDefaults.cardColors(containerColor = DarkNavyCard),
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(36.dp)
                            .background(AccentBlue.copy(alpha = 0.15f), MaterialTheme.shapes.small),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Device: $deviceMode Preview", color = AccentBlue, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Adaptive Layout Container", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text("Components automatically scale to fit $deviceMode screen dimensions.", fontSize = 13.sp, color = TextSecondary)

                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = {}, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = AccentBlue)) {
                        Text("Responsive Action Button", color = Color.White)
                    }
                }
            }
        }
    }
}

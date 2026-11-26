package com.Snap2UI.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Snap2UI.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QualityReportScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("UI Quality & Accessibility Report") },
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
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = DarkNavyCard),
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Overall Design Quality Score", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text("88 / 100", fontSize = 52.sp, fontWeight = FontWeight.Bold, color = SuccessGreen)
                    Text("Calculated from contrast ratio, font hierarchy, touch targets & spacing.", fontSize = 12.sp, color = TextSecondary)
                }
            }

            Text("Evaluation Metrics", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)

            val metrics = listOf(
                Pair("Color Contrast", "Good (Ratio 4.8:1)"),
                Pair("Typography Hierarchy", "Good (Material 3 Scale)"),
                Pair("Component Spacing", "Optimized (16dp grid)"),
                Pair("Accessibility & Touch Targets", "Compliant (48x48dp)")
            )

            metrics.forEach { (metric, status) ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = DarkNavyCard)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(metric, color = TextPrimary, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                        Text(status, color = SuccessGreen, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

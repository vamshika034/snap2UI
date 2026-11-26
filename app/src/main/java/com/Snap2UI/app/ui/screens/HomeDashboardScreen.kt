package com.Snap2UI.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Snap2UI.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDashboardScreen(
    onNavigateToUpload: () -> Unit,
    onNavigateToGenerator: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToVisualEditor: () -> Unit,
    onNavigateToAiChat: () -> Unit,
    onNavigateToMultiScreen: () -> Unit,
    onNavigateToResponsive: () -> Unit,
    onNavigateToQuality: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Snap2UI AI Platform", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkNavyBackground),
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = TextPrimary)
                    }
                }
            )
        },
        containerColor = DarkNavyBackground
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = DarkNavyCard),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = AccentBlue, modifier = Modifier.size(32.dp))
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("Advanced AI UI Platform", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            "Reconstruct screenshots, edit visually, chat with AI, generate multi-screen apps, and evaluate quality.",
                            fontSize = 14.sp,
                            color = TextSecondary
                        )
                    }
                }
            }

            item {
                Text("Core Actions", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ActionCard(
                        modifier = Modifier.weight(1f),
                        title = "Upload Screenshot",
                        subtitle = "Convert image to UI",
                        icon = Icons.Default.CameraAlt,
                        onClick = onNavigateToUpload
                    )
                    ActionCard(
                        modifier = Modifier.weight(1f),
                        title = "AI Prompt UI",
                        subtitle = "Generate from text",
                        icon = Icons.Default.EditNote,
                        onClick = onNavigateToGenerator
                    )
                }
            }

            item {
                Text("Advanced Platform Modules", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        AdvancedFeatureCard(
                            modifier = Modifier.weight(1f),
                            title = "Visual UI Editor",
                            icon = Icons.Default.Tune,
                            onClick = onNavigateToVisualEditor
                        )
                        AdvancedFeatureCard(
                            modifier = Modifier.weight(1f),
                            title = "AI Chat Assistant",
                            icon = Icons.Default.Chat,
                            onClick = onNavigateToAiChat
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        AdvancedFeatureCard(
                            modifier = Modifier.weight(1f),
                            title = "Multi-Screen Apps",
                            icon = Icons.Default.Layers,
                            onClick = onNavigateToMultiScreen
                        )
                        AdvancedFeatureCard(
                            modifier = Modifier.weight(1f),
                            title = "Responsive Preview",
                            icon = Icons.Default.Devices,
                            onClick = onNavigateToResponsive
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        AdvancedFeatureCard(
                            modifier = Modifier.weight(1f),
                            title = "Quality Report",
                            icon = Icons.Default.Verified,
                            onClick = onNavigateToQuality
                        )
                        AdvancedFeatureCard(
                            modifier = Modifier.weight(1f),
                            title = "Project History",
                            icon = Icons.Default.History,
                            onClick = onNavigateToHistory
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ActionCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = DarkNavyCard),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = MaterialTheme.shapes.medium,
                color = AccentBlue.copy(alpha = 0.15f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(icon, contentDescription = null, tint = AccentBlue, modifier = Modifier.size(24.dp))
                }
            }
            Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextPrimary)
            Text(subtitle, fontSize = 12.sp, color = TextSecondary)
        }
    }
}

@Composable
fun AdvancedFeatureCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = DarkNavyCard),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Surface(
                modifier = Modifier.size(36.dp),
                shape = MaterialTheme.shapes.small,
                color = AccentBlue.copy(alpha = 0.2f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(icon, contentDescription = null, tint = AccentBlue, modifier = Modifier.size(20.dp))
                }
            }
            Text(title, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = TextPrimary)
        }
    }
}

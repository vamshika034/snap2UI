package com.Snap2UI.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.Snap2UI.app.data.ProjectItem
import com.Snap2UI.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectHistoryScreen(
    onNavigateBack: () -> Unit
) {
    val projects = listOf(
        ProjectItem("1", "E-Commerce Checkout", "Today, 10:45 AM", "Screenshot Recon", ""),
        ProjectItem("2", "Banking Dashboard", "Yesterday, 4:20 PM", "AI Prompt UI", ""),
        ProjectItem("3", "Social Chat Screen", "Feb 18, 2025", "Screenshot Recon", ""),
        ProjectItem("4", "Fitness Tracker Profile", "Feb 15, 2025", "AI Prompt UI", "")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Project History") },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(projects) { project ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = DarkNavyCard),
                    shape = MaterialTheme.shapes.medium
                ) {
                    ListItem(
                        headlineContent = { Text(project.title, fontWeight = FontWeight.Bold, color = TextPrimary) },
                        supportingContent = { Text("${project.type} • ${project.date}", color = TextSecondary, fontSize = 12.sp) },
                        leadingContent = {
                            Surface(
                                modifier = Modifier.size(44.dp),
                                shape = MaterialTheme.shapes.small,
                                color = AccentBlue.copy(alpha = 0.2f)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.History, contentDescription = null, tint = AccentBlue)
                                }
                            }
                        },
                        trailingContent = {
                            IconButton(onClick = {}) {
                                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary)
                            }
                        },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent)
                    )
                }
            }
        }
    }
}

package com.Snap2UI.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

data class AppScreenInfo(val id: String, val title: String, val type: String, val componentsCount: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiScreenProjectScreen(
    onNavigateBack: () -> Unit,
    onNavigateToPreview: () -> Unit
) {
    val screens = listOf(
        AppScreenInfo("login", "Login Screen", "Authentication", 4),
        AppScreenInfo("register", "Registration Screen", "Authentication", 5),
        AppScreenInfo("home", "Home Dashboard", "Dashboard", 8),
        AppScreenInfo("listing", "Restaurant Listing", "Catalog", 6),
        AppScreenInfo("details", "Food Item Details", "Details", 7),
        AppScreenInfo("cart", "Shopping Cart", "Checkout", 5),
        AppScreenInfo("payment", "Payment Checkout", "Transaction", 6),
        AppScreenInfo("profile", "User Profile", "Settings", 5)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Multi-Screen Project Overview") },
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
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = DarkNavyCard),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Food Delivery App Project", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = TextPrimary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Shared Design System • Material 3 Dark • 8 Connected Screens", fontSize = 12.sp, color = TextSecondary)
                    }
                }
            }

            items(screens) { screen ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNavigateToPreview() },
                    colors = CardDefaults.cardColors(containerColor = DarkNavyCard),
                    shape = MaterialTheme.shapes.medium
                ) {
                    ListItem(
                        headlineContent = { Text(screen.title, fontWeight = FontWeight.Bold, color = TextPrimary) },
                        supportingContent = { Text("${screen.type} • ${screen.componentsCount} UI Components", color = TextSecondary, fontSize = 12.sp) },
                        leadingContent = {
                            Surface(
                                modifier = Modifier.size(40.dp),
                                shape = MaterialTheme.shapes.small,
                                color = AccentBlue.copy(alpha = 0.2f)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.PhoneAndroid, contentDescription = null, tint = AccentBlue)
                                }
                            }
                        },
                        trailingContent = {
                            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary)
                        },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent)
                    )
                }
            }
        }
    }
}

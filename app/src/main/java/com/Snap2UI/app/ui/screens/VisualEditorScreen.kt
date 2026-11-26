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
import com.Snap2UI.app.data.UIComponent
import com.Snap2UI.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisualEditorScreen(
    onNavigateBack: () -> Unit,
    onNavigateToPreview: () -> Unit
) {
    var components by remember {
        mutableStateOf(
            mutableListOf(
                UIComponent("comp_1", "button", 32, 100, 1016, 90, 0.99f, "Sign In Button"),
                UIComponent("comp_2", "text_field", 32, 220, 1016, 90, 0.95f, "Email Input"),
                UIComponent("comp_3", "text_view", 32, 340, 400, 50, 0.94f, "Header Title")
            )
        )
    }
    var selectedComponent by remember { mutableStateOf<UIComponent?>(components.firstOrNull()) }
    var buttonColor by remember { mutableStateOf("Blue (#3B82F6)") }
    var fontSize by remember { mutableStateOf(16f) }
    var paddingSize by remember { mutableStateOf(16f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Visual UI Editor") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToPreview) {
                        Icon(Icons.Default.Check, contentDescription = "Save", tint = SuccessGreen)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkNavyBackground)
            )
        },
        containerColor = DarkNavyBackground
    ) { padding ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Left: Component Tree
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(DarkNavyCard)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Layers & Components", fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(components) { comp ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedComponent = comp },
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedComponent?.id == comp.id) AccentBlue.copy(alpha = 0.3f) else SurfaceColor
                            )
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(comp.text, color = TextPrimary, fontWeight = FontWeight.Medium, fontSize = 13.sp)
                                Text(comp.type.uppercase(), color = AccentBlue, fontSize = 10.sp)
                            }
                        }
                    }
                }
            }

            // Right: Properties Panel
            Column(
                modifier = Modifier
                    .weight(1.2f)
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Properties Panel", fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 16.sp)

                if (selectedComponent != null) {
                    OutlinedTextField(
                        value = selectedComponent!!.text,
                        onValueChange = { newText ->
                            components = components.map { if (it.id == selectedComponent!!.id) it.copy(text = newText) else it }.toMutableList()
                            selectedComponent = selectedComponent!!.copy(text = newText)
                        },
                        label = { Text("Component Text Label") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text("Font Size: ${fontSize.toInt()}sp", color = TextSecondary, fontSize = 12.sp)
                    Slider(
                        value = fontSize,
                        onValueChange = { fontSize = it },
                        valueRange = 12f..32f,
                        colors = SliderDefaults.colors(thumbColor = AccentBlue, activeTrackColor = AccentBlue)
                    )

                    Text("Padding & Margins: ${paddingSize.toInt()}dp", color = TextSecondary, fontSize = 12.sp)
                    Slider(
                        value = paddingSize,
                        onValueChange = { paddingSize = it },
                        valueRange = 4f..32f,
                        colors = SliderDefaults.colors(thumbColor = AccentBlue, activeTrackColor = AccentBlue)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = { buttonColor = "Blue (#3B82F6)" },
                            colors = ButtonDefaults.buttonColors(containerColor = AccentBlue),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Blue")
                        }
                        Button(
                            onClick = { buttonColor = "Purple (#8B5CF6)" },
                            colors = ButtonDefaults.buttonColors(containerColor = AccentViolet),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Purple")
                        }
                    }

                    Button(
                        onClick = onNavigateToPreview,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = AccentBlue)
                    ) {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Apply & Preview Changes", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

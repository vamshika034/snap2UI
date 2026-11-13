package com.Snap2UI.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Snap2UI.app.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun ImageAnalysisScreen(
    sampleName: String,
    onAnalysisFinished: () -> Unit
) {
    var progressStep by remember { mutableStateOf(1) }

    LaunchedEffect(true) {
        delay(800)
        progressStep = 2
        delay(800)
        progressStep = 3
        delay(800)
        progressStep = 4
        delay(600)
        onAnalysisFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNavyBackground)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = AccentBlue,
                modifier = Modifier.size(64.dp),
                strokeWidth = 6.dp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Analyzing '$sampleName'",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))

            val statusText = when (progressStep) {
                1 -> "Running OpenCV Edge & Contour Detection..."
                2 -> "Performing OCR Text Extraction (Tesseract)..."
                3 -> "Classifying Components with ML Models..."
                else -> "Generating Layout Hierarchy & Code..."
            }

            Text(
                text = statusText,
                fontSize = 14.sp,
                color = AccentViolet
            )
        }
    }
}

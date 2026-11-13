package com.Snap2UI.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.Snap2UI.app.ui.navigation.Snap2UINavGraph
import com.Snap2UI.app.ui.theme.Snap2UITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Snap2UITheme {
                Snap2UINavGraph()
            }
        }
    }
}

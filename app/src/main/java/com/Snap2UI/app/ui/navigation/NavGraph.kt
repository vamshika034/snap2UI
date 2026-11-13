package com.Snap2UI.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.Snap2UI.app.ui.screens.*

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Upload : Screen("upload")
    object Analysis : Screen("analysis/{sampleName}") {
        fun createRoute(sampleName: String) = "analysis/$sampleName"
    }
    object Components : Screen("components")
    object Generator : Screen("generator")
    object Preview : Screen("preview")
    object CodeEditor : Screen("code_editor")
    object Comparison : Screen("comparison")
    object History : Screen("history")
    object Settings : Screen("settings")
}

@Composable
fun Snap2UINavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeDashboardScreen(
                onNavigateToUpload = { navController.navigate(Screen.Upload.route) },
                onNavigateToGenerator = { navController.navigate(Screen.Generator.route) },
                onNavigateToHistory = { navController.navigate(Screen.History.route) },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) }
            )
        }
        composable(Screen.Upload.route) {
            ScreenshotUploadScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAnalysis = { sampleName ->
                    navController.navigate(Screen.Analysis.createRoute(sampleName))
                }
            )
        }
        composable(
            route = Screen.Analysis.route,
            arguments = listOf(navArgument("sampleName") { type = NavType.StringType })
        ) { backStackEntry ->
            val sampleName = backStackEntry.arguments?.getString("sampleName") ?: "Sample"
            ImageAnalysisScreen(
                sampleName = sampleName,
                onAnalysisFinished = {
                    navController.navigate(Screen.Components.route) {
                        popUpTo(Screen.Upload.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Components.route) {
            DetectedComponentsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToPreview = { navController.navigate(Screen.Preview.route) }
            )
        }
        composable(Screen.Generator.route) {
            AiDesignGeneratorScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToPreview = { navController.navigate(Screen.Preview.route) }
            )
        }
        composable(Screen.Preview.route) {
            GeneratedUiPreviewScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToCode = { navController.navigate(Screen.CodeEditor.route) },
                onNavigateToComparison = { navController.navigate(Screen.Comparison.route) }
            )
        }
        composable(Screen.CodeEditor.route) {
            CodeEditorScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Comparison.route) {
            ComparisonSimilarityScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.History.route) {
            ProjectHistoryScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

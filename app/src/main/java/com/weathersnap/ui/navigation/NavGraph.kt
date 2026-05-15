package com.weathersnap.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.weathersnap.ui.camera.CameraScreen
import com.weathersnap.ui.createreport.CreateReportScreen
import com.weathersnap.ui.reports.ReportsScreen
import com.weathersnap.ui.weather.WeatherScreen

sealed class Screen(val route: String) {
    object Weather : Screen("weather")
    object CreateReport : Screen("create_report/{weatherJson}") {
        fun createRoute(weatherJson: String) = "create_report/$weatherJson"
    }
    object Camera : Screen("camera")
    object Reports : Screen("reports")
}

@Composable
fun WeatherSnapNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Weather.route
    ) {
        composable(
            route = Screen.Weather.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(500)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(500)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(500)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(500)) }
        ) {
            WeatherScreen(
                onNavigateToCreateReport = { weatherJson ->
                    navController.navigate(Screen.CreateReport.createRoute(weatherJson))
                },
                onNavigateToReports = {
                    navController.navigate(Screen.Reports.route)
                }
            )
        }

        composable(
            route = Screen.CreateReport.route,
            arguments = listOf(navArgument("weatherJson") { type = NavType.StringType }),
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(500)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(500)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(500)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(500)) }
        ) { backStackEntry ->
            val weatherJson = backStackEntry.arguments?.getString("weatherJson") ?: ""
            CreateReportScreen(
                weatherJson = weatherJson,
                onNavigateToCamera = {
                    navController.navigate(Screen.Camera.route)
                },
                onNavigateToReports = {
                    navController.navigate(Screen.Reports.route) {
                        popUpTo(Screen.Weather.route)
                    }
                },
                onBack = { navController.popBackStack() },
                navController = navController
            )
        }

        composable(
            route = Screen.Camera.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(500)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(500)) }
        ) {
            CameraScreen(
                onImageCaptured = { filePath ->
                    navController.previousBackStackEntry?.savedStateHandle?.set("captured_image_path", filePath)
                    navController.popBackStack()
                },
                onClose = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.Reports.route,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(500)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(500)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(500)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(500)) }
        ) {
            ReportsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}

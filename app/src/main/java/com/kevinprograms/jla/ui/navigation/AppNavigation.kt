package com.kevinprograms.jla.ui.navigation

import android.view.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kevinprograms.jla.ui.screens.home.HomeScreen
import com.kevinprograms.jla.ui.screens.learnSessionOverviewScreen.LearnSessionOverviewScreen
import com.kevinprograms.jla.ui.screens.learningScreen.LearningScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    )
    {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { HomeScreen(navController) }
            composable("LearnSession") { LearningScreen(navController) }
            composable("LearnSessionOverview") { LearnSessionOverviewScreen(navController) }

        }
    }
}
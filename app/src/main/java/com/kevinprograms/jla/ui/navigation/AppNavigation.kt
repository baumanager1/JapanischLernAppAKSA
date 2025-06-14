package com.kevinprograms.jla.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kevinprograms.jla.ui.screens.home.HomeScreen
import com.kevinprograms.jla.ui.screens.learningScreen.LearningScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController)  }
        composable("LearnSession") { LearningScreen(navController)  }
    }
}
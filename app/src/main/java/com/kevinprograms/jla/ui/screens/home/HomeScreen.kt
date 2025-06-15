package com.kevinprograms.jla.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kevinprograms.jla.ui.theme.primaryButtonColor

@Composable
fun HomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center

        ) {
            Column {
                StartLearnSessionButton(navController)
                Spacer(modifier = Modifier.height(40.dp))
                SettingsButton()
            }
        }
    }
}

@Composable
fun StartLearnSessionButton(navController: NavController) {
    Button(
        onClick = { startLearnSession(navController) },
        colors = ButtonDefaults.buttonColors(containerColor = primaryButtonColor),
        modifier = Modifier
            .size(width = 300.dp, height = 60.dp)
    ) {
        Text("Start Learn Session")
    }
}

private fun startLearnSession(navController: NavController) {
    navController.navigate("LearnSession")
}

@Composable
@Preview
fun SettingsButton() {
    Button(
        onClick = { settingsButton() },
        colors = ButtonDefaults.buttonColors(containerColor = primaryButtonColor ),
        modifier = Modifier
            .size(width = 300.dp, height = 60.dp)

    ) {
        Text("Settings")
    }
}

private fun settingsButton() {
   // val context = applicationContext
    // val dbName = "kanjidatabase.db" // e.g., "kanjidatabase.db"
    // val deleted = context.deleteDatabase(dbName)
    // Log.d("DB_DELETE", "Database deleted: $deleted")
}
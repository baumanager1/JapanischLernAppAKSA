package com.kevinprograms.jla.ui.screens.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jla.ui.theme.JapaneseLearningApplicationTheme
import com.kevinprograms.jla.MyApp
import com.kevinprograms.jla.ui.theme.primaryButtonColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JapaneseLearningApplicationTheme  {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment =Alignment.Center

                ) {
                    Column {
                        StartLearnSessionButton()
                        Spacer(modifier = Modifier.height(40.dp))
                        SettingsButton()
                    }
                }
            }
        }
    }
    @Composable
    @Preview
    fun StartLearnSessionButton() {
        Button(
            onClick = { startLearnSession() },
            colors = ButtonDefaults.buttonColors(containerColor = primaryButtonColor),
            modifier = Modifier
                .size(width = 300.dp, height = 60.dp)
        ) {
            Text("Start Learn Session")
        }
    }

    private fun startLearnSession() {
        CoroutineScope(Dispatchers.IO).launch {
        }
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
        val context = applicationContext
       // val dbName = "kanjidatabase.db" // e.g., "kanjidatabase.db"
       // val deleted = context.deleteDatabase(dbName)
       // Log.d("DB_DELETE", "Database deleted: $deleted")
    }
}


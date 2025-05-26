package com.kevinprograms.jla.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jla.ui.theme.JapaneseLearningApplicationTheme
import com.kevinprograms.jla.ui.theme.primaryButtonColor



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

fun startLearnSession() {

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

fun settingsButton() {
    TODO("ImplementSettingsButtonFunction")
}
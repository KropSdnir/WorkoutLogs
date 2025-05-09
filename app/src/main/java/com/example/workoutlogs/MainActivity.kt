package com.example.workoutlogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.workoutlogs.ui.navigation.NavGraph
import com.example.workoutlogs.ui.theme.WorkoutLogsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkoutLogsTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
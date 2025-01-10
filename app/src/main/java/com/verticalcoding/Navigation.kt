package com.verticalcoding

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.verticalcoding.dogs.ui.dogs.DogsScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            DogsScreen(
                modifier = Modifier.padding(16.dp),
                navigationController = navController
            )
        }
        composable("details") {
            Text("Here will be a doggo :)")
        }
    }
}

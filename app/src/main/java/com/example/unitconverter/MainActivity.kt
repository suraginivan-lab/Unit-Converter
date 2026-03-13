package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverterApp()
                }
            }
        }
    }
}

@Composable
fun UnitConverterApp() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { 
            HomeScreen(navController = navController) 
        }
        composable("length") { 
            LengthConverterScreen(navController = navController) 
        }
        composable("weight") { 
            WeightConverterScreen(navController = navController) 
        }
        composable("temperature") { 
            TemperatureConverterScreen(navController = navController) 
        }
        composable("volume") { 
            VolumeConverterScreen(navController = navController) 
        }
        composable("area") { 
            AreaConverterScreen(navController = navController) 
        }
        composable("speed") { 
            SpeedConverterScreen(navController = navController) 
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme {
        UnitConverterApp()
    }
}

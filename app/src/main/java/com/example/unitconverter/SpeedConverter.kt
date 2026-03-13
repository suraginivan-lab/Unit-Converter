package com.example.unitconverter

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun SpeedConverterScreen(navController: NavController) {
    val units = listOf(
        "м/с",
        "км/ч",
        "миля/ч (mph)",
        "фут/с",
        "узел",
        "Мах",
        "км/мин",
        "м/мин"
    )

    val conversionFunction: (Double, Int, Int) -> Double = { value, fromIndex, toIndex ->
        // Коэффициенты в м/с
        val coefficients = listOf(
            1.0,        // м/с
            0.277778,   // км/ч
            0.44704,    // миля/ч
            0.3048,     // фут/с
            0.514444,   // узел
            343.0,      // Мах (приблизительно)
            16.6667,    // км/мин
            0.0166667   // м/мин
        )
        
        value * coefficients[fromIndex] / coefficients[toIndex]
    }

    ConverterScreen(
        title = "Конвертер скорости",
        units = units,
        conversionFunction = conversionFunction,
        navController = navController
    )
}

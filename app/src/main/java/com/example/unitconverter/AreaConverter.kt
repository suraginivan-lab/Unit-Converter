package com.example.unitconverter

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun AreaConverterScreen(navController: NavController) {
    val units = listOf(
        "Кв. миллиметр (мм²)",
        "Кв. сантиметр (см²)",
        "Кв. метр (м²)",
        "Кв. километр (км²)",
        "Гектар (га)",
        "Акр",
        "Кв. дюйм (in²)",
        "Кв. фут (ft²)",
        "Кв. ярд (yd²)",
        "Кв. миля (mi²)"
    )

    val conversionFunction: (Double, Int, Int) -> Double = { value, fromIndex, toIndex ->
        // Коэффициенты в квадратных метрах
        val coefficients = listOf(
            0.000001,       // мм²
            0.0001,         // см²
            1.0,            // м²
            1000000.0,      // км²
            10000.0,        // га
            4046.86,        // акр
            0.00064516,     // дюйм²
            0.092903,       // фут²
            0.836127,       // ярд²
            2590000.0       // миля²
        )
        
        value * coefficients[fromIndex] / coefficients[toIndex]
    }

    ConverterScreen(
        title = "Конвертер площади",
        units = units,
        conversionFunction = conversionFunction,
        navController = navController
    )
}

package com.example.unitconverter

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun LengthConverterScreen(navController: NavController) {
    val units = listOf(
        "Миллиметр (мм)",
        "Сантиметр (см)",
        "Метр (м)",
        "Километр (км)",
        "Дюйм (in)",
        "Фут (ft)",
        "Ярд (yd)",
        "Миля (mi)"
    )

    val conversionFunction: (Double, Int, Int) -> Double = { value, fromIndex, toIndex ->
        // Коэффициенты в метрах
        val coefficients = listOf(
            0.001,      // мм
            0.01,       // см
            1.0,        // м
            1000.0,     // км
            0.0254,     // дюйм
            0.3048,     // фут
            0.9144,     // ярд
            1609.34     // миля
        )
        
        value * coefficients[fromIndex] / coefficients[toIndex]
    }

    ConverterScreen(
        title = "Конвертер длины",
        units = units,
        conversionFunction = conversionFunction,
        navController = navController
    )
}

package com.example.unitconverter

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun WeightConverterScreen(navController: NavController) {
    val units = listOf(
        "Миллиграмм (мг)",
        "Грамм (г)",
        "Килограмм (кг)",
        "Тонна (т)",
        "Унция (oz)",
        "Фунт (lb)",
        "Карат"
    )

    val conversionFunction: (Double, Int, Int) -> Double = { value, fromIndex, toIndex ->
        // Коэффициенты в граммах
        val coefficients = listOf(
            0.001,      // мг
            1.0,        // г
            1000.0,     // кг
            1000000.0,  // т
            28.3495,    // унция
            453.592,    // фунт
            0.2         // карат
        )
        
        value * coefficients[fromIndex] / coefficients[toIndex]
    }

    ConverterScreen(
        title = "Конвертер веса",
        units = units,
        conversionFunction = conversionFunction,
        navController = navController
    )
}

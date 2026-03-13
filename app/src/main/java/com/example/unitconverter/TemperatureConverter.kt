package com.example.unitconverter

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun TemperatureConverterScreen(navController: NavController) {
    val units = listOf(
        "Цельсий (°C)",
        "Фаренгейт (°F)",
        "Кельвин (K)",
        "Реомюр (°Ré)"
    )

    val conversionFunction: (Double, Int, Int) -> Double = { value, fromIndex, toIndex ->
        // Сначала конвертируем в Цельсий
        val celsius = when (fromIndex) {
            0 -> value // уже в Цельсии
            1 -> (value - 32) * 5.0 / 9.0 // Фаренгейт в Цельсий
            2 -> value - 273.15 // Кельвин в Цельсий
            3 -> value * 5.0 / 4.0 // Реомюр в Цельсий
            else -> value
        }
        
        // Затем из Цельсия в нужную единицу
        when (toIndex) {
            0 -> celsius // остается в Цельсии
            1 -> celsius * 9.0 / 5.0 + 32 // Цельсий в Фаренгейт
            2 -> celsius + 273.15 // Цельсий в Кельвин
            3 -> celsius * 4.0 / 5.0 // Цельсий в Реомюр
            else -> celsius
        }
    }

    ConverterScreen(
        title = "Конвертер температуры",
        units = units,
        conversionFunction = conversionFunction,
        navController = navController
    )
}

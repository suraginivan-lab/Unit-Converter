package com.example.unitconverter

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun VolumeConverterScreen(navController: NavController) {
    val units = listOf(
        "Миллилитр (мл)",
        "Литр (л)",
        "Кубический метр (м³)",
        "Галлон (амер.)",
        "Галлон (брит.)",
        "Пинта (амер.)",
        "Унция жидк. (амер.)",
        "Столовая ложка",
        "Чайная ложка"
    )

    val conversionFunction: (Double, Int, Int) -> Double = { value, fromIndex, toIndex ->
        // Коэффициенты в миллилитрах
        val coefficients = listOf(
            1.0,        // мл
            1000.0,     // л
            1000000.0,  // м³
            3785.41,    // галлон американский
            4546.09,    // галлон британский
            473.176,    // пинта американская
            29.5735,    // унция жидкая американская
            14.7868,    // столовая ложка
            4.92892     // чайная ложка
        )
        
        value * coefficients[fromIndex] / coefficients[toIndex]
    }

    ConverterScreen(
        title = "Конвертер объёма",
        units = units,
        conversionFunction = conversionFunction,
        navController = navController
    )
}

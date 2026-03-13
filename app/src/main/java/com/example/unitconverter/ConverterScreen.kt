package com.example.unitconverter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.unitconverter.ui.theme.PrimaryBlue
import kotlin.math.round

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterScreen(
    title: String,
    units: List<String>,
    conversionFunction: (Double, Int, Int) -> Double,
    navController: NavController
) {
    var inputValue by remember { mutableStateOf("") }
    var fromUnitIndex by remember { mutableStateOf(0) }
    var toUnitIndex by remember { mutableStateOf(1) }
    var isFromUnitExpanded by remember { mutableStateOf(false) }
    var isToUnitExpanded by remember { mutableStateOf(false) }
    
    val result = remember(inputValue, fromUnitIndex, toUnitIndex) {
        if (inputValue.isNotEmpty()) {
            try {
                val value = inputValue.toDouble()
                conversionFunction(value, fromUnitIndex, toUnitIndex)
            } catch (e: NumberFormatException) {
                0.0
            }
        } else {
            0.0
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Верхняя панель
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Назад",
                    tint = PrimaryBlue
                )
            }
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(48.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Карточка ввода
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Поле ввода
                OutlinedTextField(
                    value = inputValue,
                    onValueChange = { inputValue = it },
                    label = { Text("Введите значение") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )

                // Селекторы единиц
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Из единицы
                    Box(modifier = Modifier.weight(1f)) {
                        ExposedDropdownMenuBox(
                            expanded = isFromUnitExpanded,
                            onExpandedChange = { isFromUnitExpanded = !isFromUnitExpanded }
                        ) {
                            OutlinedTextField(
                                value = units[fromUnitIndex],
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Из") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),
                                shape = RoundedCornerShape(12.dp),
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isFromUnitExpanded) }
                            )
                            ExposedDropdownMenu(
                                expanded = isFromUnitExpanded,
                                onDismissRequest = { isFromUnitExpanded = false }
                            ) {
                                units.forEachIndexed { index, unit ->
                                    DropdownMenuItem(
                                        text = { Text(unit) },
                                        onClick = {
                                            fromUnitIndex = index
                                            isFromUnitExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // Кнопка переключения
                    IconButton(
                        onClick = {
                            val temp = fromUnitIndex
                            fromUnitIndex = toUnitIndex
                            toUnitIndex = temp
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.SwapVert,
                            contentDescription = "Переключить",
                            tint = PrimaryBlue
                        )
                    }

                    // В единицу
                    Box(modifier = Modifier.weight(1f)) {
                        ExposedDropdownMenuBox(
                            expanded = isToUnitExpanded,
                            onExpandedChange = { isToUnitExpanded = !isToUnitExpanded }
                        ) {
                            OutlinedTextField(
                                value = units[toUnitIndex],
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("В") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),
                                shape = RoundedCornerShape(12.dp),
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isToUnitExpanded) }
                            )
                            ExposedDropdownMenu(
                                expanded = isToUnitExpanded,
                                onDismissRequest = { isToUnitExpanded = false }
                            ) {
                                units.forEachIndexed { index, unit ->
                                    DropdownMenuItem(
                                        text = { Text(unit) },
                                        onClick = {
                                            toUnitIndex = index
                                            isToUnitExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Карточка результата
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = PrimaryBlue)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Результат",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (inputValue.isNotEmpty()) {
                        "${round(result * 100000) / 100000} ${units[toUnitIndex]}"
                    } else {
                        "0 ${units[toUnitIndex]}"
                    },
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка очистки
        Button(
            onClick = { 
                inputValue = ""
                fromUnitIndex = 0
                toUnitIndex = 1
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Очистить",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

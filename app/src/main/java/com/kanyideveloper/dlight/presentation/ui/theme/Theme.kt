package com.kanyideveloper.dlight.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Color.Gray,
    secondary = Color.White
)

@Composable
fun DlightTheme(content: @Composable () -> Unit) {
    val colors =
        LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
package com.zivansabilli3153.resepkost.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val BlueLightColorScheme = lightColorScheme(
    primary = Color(0xFF1565C0),
    onPrimary = Color.White,
    secondary = Color(0xFF0277BD),
    tertiary = Color(0xFF00838F),
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE)
)

private val BlueDarkColorScheme = darkColorScheme(
    primary = Color(0xFF90CAF9),
    onPrimary = Color.Black,
    secondary = Color(0xFF81D4FA),
    tertiary = Color(0xFF80DEEA)
)

private val GreenLightColorScheme = lightColorScheme(
    primary = Color(0xFF2E7D32),
    onPrimary = Color.White,
    secondary = Color(0xFF388E3C),
    tertiary = Color(0xFF689F38),
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE)
)

private val GreenDarkColorScheme = darkColorScheme(
    primary = Color(0xFFA5D6A7),
    onPrimary = Color.Black,
    secondary = Color(0xFFC5E1A5),
    tertiary = Color(0xFFDCEDC8)
)

private val OrangeLightColorScheme = lightColorScheme(
    primary = Color(0xFFE65100),
    onPrimary = Color.White,
    secondary = Color(0xFFF57C00),
    tertiary = Color(0xFFFF8F00),
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE)
)

private val OrangeDarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFCC80),
    onPrimary = Color.Black,
    secondary = Color(0xFFFFB74D),
    tertiary = Color(0xFFFFE0B2)
)

@Composable
fun ResepKostTheme(
    themeColor: String = "blue",
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeColor) {
        "green" -> if (darkTheme) GreenDarkColorScheme else GreenLightColorScheme
        "orange" -> if (darkTheme) OrangeDarkColorScheme else OrangeLightColorScheme
        else -> if (darkTheme) BlueDarkColorScheme else BlueLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
package com.example.apppasteleria.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

/*private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun AppPasteleriaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}*/


val RosaPastel = Color(0xFFFFC1CC)
val Crema = Color(0xFFFFF3E0)
val MarronSuave = Color(0xFFD7CCC8)
val Chocolate = Color(0xFF6D4C41)
val Blanco = Color(0xFFFFFFFF)
val Dorado = Color(0xFFFFD54F)

private val LightColorScheme = lightColorScheme(
    primary = RosaPastel,
    secondary = Crema,
    tertiary = Dorado,
    background = Blanco,
    surface = Crema,
    onPrimary = Chocolate,
    onSecondary = Chocolate,
    onTertiary = Chocolate,
    onBackground = Chocolate,
    onSurface = Chocolate
)

private val DarkColorScheme = darkColorScheme(
    primary = Chocolate,
    secondary = MarronSuave,
    tertiary = RosaPastel,
    background = Color(0xFF3E2723),
    surface = Color(0xFF4E342E),
    onPrimary = Blanco,
    onSecondary = Blanco,
    onTertiary = Blanco,
    onBackground = Blanco,
    onSurface = Blanco
)

@Composable
fun AppPasteleriaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

package com.indisparte.designsystem.theme

import androidx.annotation.VisibleForTesting
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@VisibleForTesting
val DarkDefaultColorScheme = darkColorScheme(
    background = backgroundColor,
    surface = surfaceColor,
    onSurface = onSurfaceColor ,
    error = errorColor,
    secondary = secondSurfaceColor,
    onSecondary = onSurfaceColor,
    tertiary = tertiaryColor
)

@Composable
fun CineMatesTheme(
    darkTheme: Boolean = true,
    androidTheme: Boolean = false,
    content: @Composable () -> Unit
){
    // Composition locals
    CompositionLocalProvider {
        MaterialTheme(
            colorScheme = DarkDefaultColorScheme,
            typography = CineMatesTypography,
            content = content,
        )
    }
}
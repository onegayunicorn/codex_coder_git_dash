package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme =
  lightColorScheme(
    primary = Indigo600,
    secondary = Indigo600,
    tertiary = Indigo600,
    background = BackgroundLight,
    surface = Surface,
    onBackground = Slate900,
    onSurface = Slate900,
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = false, // Force light theme for this design
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = false, // Disable dynamic for now to ensure theme consistency
  content: @Composable () -> Unit,
) {
  val colorScheme = LightColorScheme
  
  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}

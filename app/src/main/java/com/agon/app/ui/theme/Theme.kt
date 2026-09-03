package com.agon.app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val MusgoDarkColorScheme = darkColorScheme(
    primary = MusgoDarkPrimary,
    onPrimary = MusgoDarkOnPrimary,
    primaryContainer = MusgoDarkPrimaryContainer,
    onPrimaryContainer = MusgoDarkOnPrimaryContainer,
    secondary = MusgoDarkSecondary,
    onSecondary = MusgoDarkOnSecondary,
    secondaryContainer = MusgoDarkSecondaryContainer,
    onSecondaryContainer = MusgoDarkOnSecondaryContainer,
    tertiary = MusgoDarkTertiary,
    onTertiary = MusgoDarkOnTertiary,
    tertiaryContainer = MusgoDarkTertiaryContainer,
    onTertiaryContainer = MusgoDarkOnTertiaryContainer,
    background = SurfaceDark,
    onBackground = OnSurfaceDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceDarkVariant,
    onSurfaceVariant = OnSurfaceDarkVariant,
    outline = OutlineDark,
    error = CyberRed,
    onError = Color(0xFFFFFFFF),
)

private val MusgoLightColorScheme = lightColorScheme(
    primary = MusgoLightPrimary,
    onPrimary = MusgoLightOnPrimary,
    primaryContainer = MusgoLightPrimaryContainer,
    onPrimaryContainer = MusgoLightOnPrimaryContainer,
    secondary = MusgoLightSecondary,
    onSecondary = MusgoLightOnSecondary,
    secondaryContainer = MusgoLightSecondaryContainer,
    onSecondaryContainer = MusgoLightOnSecondaryContainer,
    tertiary = MusgoLightTertiary,
    onTertiary = MusgoLightOnTertiary,
    tertiaryContainer = MusgoLightTertiaryContainer,
    onTertiaryContainer = MusgoLightOnTertiaryContainer,
    background = SurfaceLight,
    onBackground = OnSurfaceLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceLightVariant,
    onSurfaceVariant = OnSurfaceLightVariant,
    outline = OutlineLight,
    error = CyberRed,
    onError = Color(0xFFFFFFFF),
)

@Composable
fun AgonAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> MusgoDarkColorScheme
        else -> MusgoLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}

package com.affan.editorai.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val StudioColorScheme = darkColorScheme(
    primary = StudioAccent,
    onPrimary = StudioTextPrimary,
    primaryContainer = StudioAccentDim,
    onPrimaryContainer = StudioTextPrimary,
    background = StudioBackground,
    onBackground = StudioTextPrimary,
    surface = StudioCard,
    onSurface = StudioTextPrimary,
    surfaceVariant = StudioCardElevated,
    onSurfaceVariant = StudioTextSecondary,
    outline = StudioBorder
)

@Composable
fun AffanEditorAITheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = StudioColorScheme,
        content = content
    )
}

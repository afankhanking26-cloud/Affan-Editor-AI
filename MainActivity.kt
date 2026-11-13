package com.affan.editorai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.affan.editorai.ui.screens.EditorScreen
import com.affan.editorai.ui.screens.HomeScreen
import com.affan.editorai.ui.theme.AffanEditorAITheme
import com.affan.editorai.ui.theme.StudioBackground
import kotlinx.coroutines.launch

private enum class AppScreen {
    HOME,
    EDITOR
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
        )
        setContent {
            AffanEditorAITheme {
                AffanEditorApp()
            }
        }
    }
}

@Composable
private fun AffanEditorApp() {
    var currentScreen by remember { mutableStateOf(AppScreen.HOME) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    fun showFeedback(message: String) {
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = StudioBackground,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Crossfade(
            targetState = currentScreen,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            label = "ScreenTransition"
        ) { screen ->
            when (screen) {
                AppScreen.HOME -> HomeScreen(
                    onNewProjectClick = { currentScreen = AppScreen.EDITOR },
                    onProfileClick = { showFeedback("Profile coming in a future step") },
                    onSettingsClick = { showFeedback("Settings coming in a future step") },
                    onTemplateClick = { templateName ->
                        showFeedback("Selected $templateName template")
                    }
                )

                AppScreen.EDITOR -> EditorScreen(
                    onBackClick = { currentScreen = AppScreen.HOME },
                    onToolAction = { toolName ->
                        showFeedback("$toolName selected")
                    }
                )
            }
        }
    }
}

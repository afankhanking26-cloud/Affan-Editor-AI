package com.affan.editorai.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.TextFields
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material.icons.rounded.VideoLibrary
import androidx.compose.material.icons.rounded.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.affan.editorai.ui.components.EditorToolButton
import com.affan.editorai.ui.components.StudioIconButton
import com.affan.editorai.ui.theme.StudioAccent
import com.affan.editorai.ui.theme.StudioBackground
import com.affan.editorai.ui.theme.StudioBorder
import com.affan.editorai.ui.theme.StudioCard
import com.affan.editorai.ui.theme.StudioCardElevated
import com.affan.editorai.ui.theme.StudioTextPrimary
import com.affan.editorai.ui.theme.StudioTextSecondary

private enum class EditorTool(val label: String) {
    MEDIA("Media"),
    AUDIO("Audio"),
    TEXT("Text"),
    EFFECTS("Effects"),
    FILTERS("Filters")
}

@Composable
fun EditorScreen(
    onBackClick: () -> Unit,
    onToolAction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isPlaying by remember { mutableStateOf(false) }
    var selectedTool by remember { mutableStateOf<EditorTool?>(null) }

    BackHandler(onBack = onBackClick)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(StudioBackground)
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        // Top Navigation Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                StudioIconButton(
                    icon = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back to Home",
                    onClick = onBackClick
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Untitled Project",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = StudioTextPrimary
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Surface(
                shape = RoundedCornerShape(10.dp),
                color = StudioCard,
                border = BorderStroke(1.dp, StudioBorder)
            ) {
                Text(
                    text = "16:9",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = StudioTextSecondary,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Video Preview Placeholder (adapts cleanly to small screens)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(18.dp),
            color = StudioCard,
            border = BorderStroke(1.dp, StudioBorder)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(StudioCardElevated),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Videocam,
                            contentDescription = "Video preview placeholder",
                            tint = StudioAccent,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Video Preview",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = StudioTextPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "No media clip loaded yet",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = StudioTextSecondary
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Playback Controls Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "00:00",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = StudioTextSecondary
                )
            )

            StudioIconButton(
                icon = if (isPlaying) Icons.Rounded.Pause else Icons.Rounded.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play",
                isAccent = true,
                onClick = {
                    isPlaying = !isPlaying
                    onToolAction(if (isPlaying) "Preview play toggled" else "Preview paused")
                }
            )

            Text(
                text = "00:00",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = StudioTextSecondary
                )
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Empty Timeline Area
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(124.dp),
            shape = RoundedCornerShape(16.dp),
            color = StudioCard,
            border = BorderStroke(1.dp, StudioBorder)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 10.dp)
                ) {
                    // Timecode ruler
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        listOf("00:00", "00:05", "00:10", "00:15", "00:20").forEach { tick ->
                            Text(
                                text = tick,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = StudioTextSecondary
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Empty track slot
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .background(StudioCardElevated),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Empty Timeline — Tap Media to add clips",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = StudioTextSecondary
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }
                }

                // Center Playhead Indicator
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(2.dp)
                        .fillMaxHeight()
                        .background(StudioAccent)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Bottom Editing Toolbar (scrollable for small screens)
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = StudioCard,
            border = BorderStroke(1.dp, StudioBorder)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 6.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                EditorToolButton(
                    label = EditorTool.MEDIA.label,
                    icon = Icons.Rounded.VideoLibrary,
                    selected = selectedTool == EditorTool.MEDIA,
                    onClick = {
                        selectedTool = EditorTool.MEDIA
                        onToolAction("Media")
                    }
                )
                EditorToolButton(
                    label = EditorTool.AUDIO.label,
                    icon = Icons.Rounded.MusicNote,
                    selected = selectedTool == EditorTool.AUDIO,
                    onClick = {
                        selectedTool = EditorTool.AUDIO
                        onToolAction("Audio")
                    }
                )
                EditorToolButton(
                    label = EditorTool.TEXT.label,
                    icon = Icons.Rounded.TextFields,
                    selected = selectedTool == EditorTool.TEXT,
                    onClick = {
                        selectedTool = EditorTool.TEXT
                        onToolAction("Text")
                    }
                )
                EditorToolButton(
                    label = EditorTool.EFFECTS.label,
                    icon = Icons.Rounded.AutoAwesome,
                    selected = selectedTool == EditorTool.EFFECTS,
                    onClick = {
                        selectedTool = EditorTool.EFFECTS
                        onToolAction("Effects")
                    }
                )
                EditorToolButton(
                    label = EditorTool.FILTERS.label,
                    icon = Icons.Rounded.Tune,
                    selected = selectedTool == EditorTool.FILTERS,
                    onClick = {
                        selectedTool = EditorTool.FILTERS
                        onToolAction("Filters")
                    }
                )
            }
        }
    }
}

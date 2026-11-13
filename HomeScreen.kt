package com.affan.editorai.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Smartphone
import androidx.compose.material.icons.rounded.Videocam
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.affan.editorai.ui.components.PrimaryActionButton
import com.affan.editorai.ui.components.SectionHeader
import com.affan.editorai.ui.components.StudioIconButton
import com.affan.editorai.ui.components.StudioProjectPlaceholderCard
import com.affan.editorai.ui.components.TemplateCard
import com.affan.editorai.ui.theme.StudioAccent
import com.affan.editorai.ui.theme.StudioBackground
import com.affan.editorai.ui.theme.StudioTextPrimary
import com.affan.editorai.ui.theme.StudioTextSecondary

private data class StarterTemplate(
    val title: String,
    val aspectRatio: String
)

private val starterTemplates = listOf(
    StarterTemplate("Reels & Shorts", "9:16"),
    StarterTemplate("YouTube Intro", "16:9"),
    StarterTemplate("Gaming Highlight", "16:9"),
    StarterTemplate("Square Promo", "1:1")
)

@Composable
fun HomeScreen(
    onNewProjectClick: () -> Unit,
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onTemplateClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(StudioBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 18.dp, vertical = 16.dp)
    ) {
        // Top Header: App Name + Profile + Settings
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(StudioAccent)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Affan Editor AI",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = StudioTextPrimary,
                            fontSize = 20.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Mobile Video Studio",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = StudioTextSecondary
                        ),
                        maxLines = 1
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StudioIconButton(
                    icon = Icons.Rounded.AccountCircle,
                    contentDescription = "Profile",
                    onClick = onProfileClick
                )
                StudioIconButton(
                    icon = Icons.Rounded.Settings,
                    contentDescription = "Settings",
                    onClick = onSettingsClick
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Primary CTA: New Project
        PrimaryActionButton(
            text = "New Project",
            icon = Icons.Rounded.Add,
            onClick = onNewProjectClick
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Recent Projects Section
        SectionHeader(
            title = "Recent Projects",
            subtitle = "0 saved"
        )
        Spacer(modifier = Modifier.height(12.dp))
        StudioProjectPlaceholderCard(
            title = "No recent projects yet",
            subtitle = "Tap New Project above to open the editor canvas.",
            icon = Icons.Rounded.Videocam,
            onClick = onNewProjectClick
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Templates Section
        SectionHeader(
            title = "Templates",
            subtitle = "Starter layouts"
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            starterTemplates.forEachIndexed { index, template ->
                val icon = when (index) {
                    0 -> Icons.Rounded.Smartphone
                    1 -> Icons.Rounded.Movie
                    else -> Icons.Rounded.AutoAwesome
                }
                TemplateCard(
                    title = template.title,
                    aspectRatioLabel = template.aspectRatio,
                    icon = icon,
                    onClick = { onTemplateClick(template.title) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

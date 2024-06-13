package com.indisparte.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun MediaPoster(
    imageUrl: String,
    chipText: String?,
    titleText: String?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .width(80.dp)
    ) {
        // Image container
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "$titleText Poster",
                modifier = Modifier
                    .height(120.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 4.dp, end = 4.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray.copy(alpha = 0.2f))
            ) {
                if (chipText != null) {
                    Text(
                        text = chipText,
                        fontSize = 10.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    )
                }
            }
        }
        // Title
        if (titleText != null) {
            Text(
                text = titleText,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.bodyLarge,

                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview
@Composable
fun CustomComponentPreview() {
    MediaPoster(
        imageUrl = "https://placehold.co/600x400",
        chipText = "9.0",
        titleText = "Sample Title"
    )
}


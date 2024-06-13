package com.indisparte.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.indisparte.base.Media
import com.indisparte.designsystem.component.MediaPoster
import com.indisparte.home.util.Section
import com.indisparte.network.util.Result

@Composable
fun MediaSectionList(mediaSectionSet: Set<Section<Media>>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(mediaSectionSet.size) { index ->
            val section = mediaSectionSet.elementAt(index)
            MediaSectionItem(section = section, modifier = Modifier.fillMaxWidth())
        }
    }

}

@Composable
fun MediaSectionItem(section: Section<Media>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = section.titleResId),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(16.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            when (section.result) {
                is Result.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                is Result.Error -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = section.result.exception.messageRes),
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

                is Result.Success -> {
                    items(section.result.data.size) { item ->
                        val media = section.result.data[item]
                        MediaPoster(
                            imageUrl = media.posterPath.toString(),
                            titleText = media.mediaName,
                            chipText = media.voteAverage.toString(),
                        )
                    }
                }
            }
        }
    }
}


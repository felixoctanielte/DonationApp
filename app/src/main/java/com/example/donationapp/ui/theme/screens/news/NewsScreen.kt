package com.example.donationapp.ui.theme.screens.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.donationapp.data.DummyData
import com.example.donationapp.ui.theme.screens.home.NewsCard

@Composable
fun NewsScreen(
    onNewsClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(DummyData.newList) { news ->
            NewsCard(news = news, onClick = { onNewsClick(news.id) })
        }
    }
}

package com.example.donationapp.ui.theme.screens.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.donationapp.ui.components.SectionHeader
import com.example.donationapp.ui.theme.screens.home.NewsCard
import com.example.donationapp.ui.viewmodel.NewsViewModel

/** Data dari NewsViewModel (Dagger → Repository). */
@Composable
fun NewsScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onNewsClick: (Int) -> Unit,
    viewModel: NewsViewModel = viewModel(factory = viewModelFactory)
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            SectionHeader(
                title = "Berita & Update",
                subtitle = "Ikuti perkembangan penyaluran donasi"
            )
        }

        items(viewModel.news) { news ->
            NewsCard(news = news, onClick = { onNewsClick(news.id) })
        }
    }
}

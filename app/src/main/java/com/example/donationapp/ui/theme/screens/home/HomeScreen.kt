package com.example.donationapp.ui.theme.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.donationapp.data.model.DonationCampaign
import com.example.donationapp.data.model.News
import com.example.donationapp.ui.components.CategoryChip
import com.example.donationapp.ui.components.HomeHeroBanner
import com.example.donationapp.ui.components.SectionHeader
import com.example.donationapp.ui.viewmodel.HomeViewModel
import java.text.NumberFormat
import java.util.Locale

/**
 * HomeScreen tidak lagi baca DummyData langsung.
 *
 * Data datang dari HomeViewModel, yang di-create lewat viewModelFactory (Dagger).
 * viewModelFactory dioper dari: MainActivity → DonationNavHost → MainScreen → sini.
 */
@Composable
fun HomeScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onCampaignClick: () -> Unit,
    onNewsClick: (Int) -> Unit,
    // factory dari Dagger; ViewModelStore yang pegang instance-nya
    viewModel: HomeViewModel = viewModel(factory = viewModelFactory)
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            HomeHeroBanner(
                title = "Halo, Donatur",
                subtitle = "Salurkan kebaikan lewat campaign yang sedang berjalan"
            )
        }

        item {
            SectionHeader(
                title = "Campaign Donasi",
                subtitle = "Pilih program yang ingin kamu dukung"
            )
        }

        items(viewModel.campaigns) { campaign ->
            CampaignCard(campaign = campaign, onClick = onCampaignClick)
        }

        item {
            Spacer(modifier = Modifier.height(4.dp))
            SectionHeader(
                title = "Berita Terbaru",
                subtitle = "Update penyaluran dan kegiatan relawan"
            )
        }

        items(viewModel.news) { news ->
            NewsCard(news = news, onClick = { onNewsClick(news.id) })
        }
    }
}

@Composable
fun CampaignCard(
    campaign: DonationCampaign,
    onClick: () -> Unit
) {
    val progress = if (campaign.targetAmount == 0L) {
        0f
    } else {
        (campaign.collectedAmount.toFloat() / campaign.targetAmount.toFloat()).coerceIn(0f, 1f)
    }
    val percent = (progress * 100).toInt()

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CategoryChip(text = campaign.category)
                Text(
                    text = "$percent%",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = campaign.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = campaign.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(14.dp))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "${formatRupiah(campaign.collectedAmount)} terkumpul dari ${formatRupiah(campaign.targetAmount)}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun NewsCard(
    news: News,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CategoryChip(text = news.category)
                Text(
                    text = news.date,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = news.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = news.summary,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

fun formatRupiah(amount: Long): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"))
    return formatter.format(amount)
}

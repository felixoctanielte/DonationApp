package com.example.donationapp.ui.theme.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.donationapp.data.DummyData
import com.example.donationapp.data.model.DonationCampaign
import com.example.donationapp.data.model.News
import java.text.NumberFormat
import java.util.Locale

@Composable
fun HomeScreen(
    onCampaignClick: () -> Unit,
    onNewsClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Halo, Donatur",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Pilih campaign atau baca berita penyaluran",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Campaign Donasi",
                style = MaterialTheme.typography.titleMedium
            )
        }

        items(DummyData.campaigns) { campaign ->
            CampaignCard(campaign = campaign, onClick = onCampaignClick)
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Berita Terbaru",
                style = MaterialTheme.typography.titleMedium
            )
        }

        items(DummyData.newList) { news ->
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

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = campaign.category, style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = campaign.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = campaign.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(12.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${formatRupiah(campaign.collectedAmount)} / ${formatRupiah(campaign.targetAmount)}",
                style = MaterialTheme.typography.bodySmall
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
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = news.category, style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = news.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = news.summary, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = news.date, style = MaterialTheme.typography.bodySmall)
        }
    }
}

fun formatRupiah(amount: Long): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"))
    return formatter.format(amount)
}

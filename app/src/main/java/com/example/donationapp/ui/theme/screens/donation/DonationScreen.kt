package com.example.donationapp.ui.theme.screens.donation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.donationapp.data.DummyData
import com.example.donationapp.ui.theme.screens.home.CampaignCard

@Composable
fun DonationScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(DummyData.campaigns) { campaign ->
            CampaignCard(campaign = campaign, onClick = {})
        }
    }
}

package com.example.donationapp.ui.theme.screens.donation

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
import com.example.donationapp.ui.theme.screens.home.CampaignCard
import com.example.donationapp.ui.viewmodel.DonationViewModel

/** Data dari DonationViewModel (Dagger → Repository), bukan DummyData langsung. */
@Composable
fun DonationScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: DonationViewModel = viewModel(factory = viewModelFactory)
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            SectionHeader(
                title = "Semua Campaign",
                subtitle = "Dukung program yang paling dekat dengan hatimu"
            )
        }

        items(viewModel.campaigns) { campaign ->
            CampaignCard(campaign = campaign, onClick = {})
        }
    }
}

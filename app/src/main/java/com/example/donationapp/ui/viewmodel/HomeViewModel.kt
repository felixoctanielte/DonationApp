package com.example.donationapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.donationapp.data.DonationRepository
import com.example.donationapp.data.model.DonationCampaign
import com.example.donationapp.data.model.News
import javax.inject.Inject

/**
 * ViewModel untuk HomeScreen.
 *
 * DIGUNAKAN OLEH:
 * - HomeScreen lewat viewModel(factory = viewModelFactory)
 *
 * DIBUAT OLEH:
 * - DonationViewModelFactory (Provider<HomeViewModel>.get())
 * - Dagger isi repository lewat @Inject constructor
 *
 * TIDAK @Singleton: tiap kali factory diminta, bisa instance baru
 * (ViewModelStore Activity/NavBackStack yang menjaga lifecycle-nya).
 */
class HomeViewModel @Inject constructor(
    private val repository: DonationRepository
) : ViewModel() {

    val campaigns: List<DonationCampaign> = repository.getCampaigns()
    val news: List<News> = repository.getNews()
}

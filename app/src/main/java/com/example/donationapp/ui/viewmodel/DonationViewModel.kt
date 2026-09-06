package com.example.donationapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.donationapp.data.DonationRepository
import com.example.donationapp.data.model.DonationCampaign
import javax.inject.Inject

/**
 * ViewModel untuk DonationScreen.
 *
 * DIGUNAKAN OLEH: DonationScreen
 * DIBUAT OLEH: DonationViewModelFactory via Dagger Provider
 */
class DonationViewModel @Inject constructor(
    private val repository: DonationRepository
) : ViewModel() {

    val campaigns: List<DonationCampaign> = repository.getCampaigns()
}

package com.example.donationapp.data

import com.example.donationapp.data.model.DonationCampaign
import com.example.donationapp.data.model.News
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository = jembatan antara ViewModel dan sumber data.
 *
 * DIGUNAKAN OLEH (di-inject lewat constructor):
 * - HomeViewModel
 * - DonationViewModel
 * - NewsViewModel
 * - NewsDetailViewModel
 *
 * DIBUAT OLEH:
 * - Dagger, karena ada @Inject constructor(dataSource)
 * - dataSource sendiri datang dari DataModule (@Binds ke DummyDonationDataSource)
 *
 * DIPANGGIL DARI:
 * - ViewModel methods (getCampaigns / getNews / getNewsById)
 * - BUKAN dari composable secara langsung
 */
@Singleton
class DonationRepository @Inject constructor(
    private val dataSource: DonationDataSource
) {
    fun getCampaigns(): List<DonationCampaign> = dataSource.getCampaigns()

    fun getNews(): List<News> = dataSource.getNews()

    fun getNewsById(id: Int): News? = dataSource.getNewsById(id)
}

package com.example.donationapp.data

import com.example.donationapp.data.model.DonationCampaign
import com.example.donationapp.data.model.News
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementasi DonationDataSource yang membaca DummyData lokal.
 *
 * DIGUNAKAN OLEH:
 * - Dagger (via DataModule @Binds) sebagai implementasi DonationDataSource
 * - Tidak dipanggil langsung dari Screen — lewat Repository
 *
 * @Inject constructor:
 * Memberi tahu Dagger: "boleh buatkan instance class ini tanpa Module @Provides".
 *
 * @Singleton:
 * Satu instance seumur AppComponent (dibuat di DonationApplication).
 */
@Singleton
class DummyDonationDataSource @Inject constructor() : DonationDataSource {

    override fun getCampaigns(): List<DonationCampaign> = DummyData.campaigns

    override fun getNews(): List<News> = DummyData.newList

    override fun getNewsById(id: Int): News? = DummyData.newList.find { it.id == id }
}

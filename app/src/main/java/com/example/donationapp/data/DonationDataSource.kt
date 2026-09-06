package com.example.donationapp.data

import com.example.donationapp.data.model.DonationCampaign
import com.example.donationapp.data.model.News

/**
 * Kontrak sumber data donasi/berita.
 *
 * DIGUNAKAN OLEH:
 * - DonationRepository (lewat constructor injection)
 *
 * DISEDIAKAN OLEH:
 * - DataModule.bindDonationDataSource() → mengikat ke DummyDonationDataSource
 *
 * Kenapa pakai interface?
 * Nanti kalau ganti dummy → API/Room, cukup ganti implementasi, Repository & ViewModel tidak berubah.
 */
interface DonationDataSource {
    fun getCampaigns(): List<DonationCampaign>
    fun getNews(): List<News>
    fun getNewsById(id: Int): News?
}

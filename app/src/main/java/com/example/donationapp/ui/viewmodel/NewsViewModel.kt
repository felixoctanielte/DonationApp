package com.example.donationapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.donationapp.data.DonationRepository
import com.example.donationapp.data.model.News
import javax.inject.Inject

/**
 * ViewModel untuk NewsScreen (daftar berita).
 *
 * DIGUNAKAN OLEH: NewsScreen
 * DIBUAT OLEH: DonationViewModelFactory via Dagger Provider
 */
class NewsViewModel @Inject constructor(
    private val repository: DonationRepository
) : ViewModel() {

    val news: List<News> = repository.getNews()
}

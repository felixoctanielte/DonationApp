package com.example.donationapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.donationapp.data.DonationRepository
import com.example.donationapp.data.model.News
import javax.inject.Inject

/**
 * ViewModel untuk NewsDetailScreen.
 *
 * DIGUNAKAN OLEH: NewsDetailScreen
 * DIBUAT OLEH: DonationViewModelFactory via Dagger Provider
 *
 * newsId tidak di-inject Dagger (datang dari navigation argument),
 * jadi diambil lewat fungsi getNews(id) setelah screen punya id-nya.
 */
class NewsDetailViewModel @Inject constructor(
    private val repository: DonationRepository
) : ViewModel() {

    fun getNews(id: Int): News? = repository.getNewsById(id)
}

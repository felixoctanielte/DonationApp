package com.example.donationapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.donationapp.ui.viewmodel.DonationViewModel
import com.example.donationapp.ui.viewmodel.HomeViewModel
import com.example.donationapp.ui.viewmodel.NewsDetailViewModel
import com.example.donationapp.ui.viewmodel.NewsViewModel
import javax.inject.Inject
import javax.inject.Provider

/**
 * Factory supaya Compose/Activity bisa buat ViewModel yang dependency-nya dari Dagger.
 *
 * DIGUNAKAN OLEH:
 * - MainActivity: di-inject ke field viewModelFactory
 * - DonationNavHost / Screen: viewModel(factory = viewModelFactory)
 *
 * Kenapa perlu Factory?
 * Android yang create ViewModel (supaya survive rotation), bukan kita `new HomeViewModel()`.
 * Factory = jembatan: Android minta ViewModel → kita minta Dagger (Provider.get()).
 *
 * Provider<T>:
 * Lazy create. Jangan inject HomeViewModel langsung di factory sebagai instance tetap,
 * supaya tiap create() dapat instance yang tepat untuk ViewModelStore.
 *
 * @Inject constructor:
 * Dagger otomatis isi semua Provider<...> karena tiap ViewModel punya @Inject constructor.
 */
class DonationViewModelFactory @Inject constructor(
    private val homeViewModelProvider: Provider<HomeViewModel>,
    private val donationViewModelProvider: Provider<DonationViewModel>,
    private val newsViewModelProvider: Provider<NewsViewModel>,
    private val newsDetailViewModelProvider: Provider<NewsDetailViewModel>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->
                homeViewModelProvider.get() as T

            modelClass.isAssignableFrom(DonationViewModel::class.java) ->
                donationViewModelProvider.get() as T

            modelClass.isAssignableFrom(NewsViewModel::class.java) ->
                newsViewModelProvider.get() as T

            modelClass.isAssignableFrom(NewsDetailViewModel::class.java) ->
                newsDetailViewModelProvider.get() as T

            else -> throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
        }
    }
}

package com.example.donationapp.di

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Contoh Module yang memakai @Provides (bukan @Binds).
 *
 * DIGUNAKAN OLEH:
 * - AppComponent
 *
 * DIPANGGIL SAAT:
 * - Ada class yang minta @Named("app_display_name") String
 *   (saat ini dipakai di MainActivity sebagai contoh field injection)
 *
 * Kapan pakai @Provides?
 * - Object dari library pihak ketiga (Retrofit, OkHttp, Room.databaseBuilder, dll)
 * - Atau nilai sederhana seperti String/Int config
 * - Intinya: class yang TIDAK bisa (atau tidak ingin) kasih @Inject constructor
 */
@Module
object AppModule {

    @Provides
    @Singleton
    @Named("app_display_name")
    fun provideAppDisplayName(): String = "DonationApp"
}

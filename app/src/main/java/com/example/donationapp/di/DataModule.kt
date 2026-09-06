package com.example.donationapp.di

import com.example.donationapp.data.DonationDataSource
import com.example.donationapp.data.DummyDonationDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Module Dagger untuk layer data.
 *
 * DIGUNAKAN OLEH:
 * - AppComponent (modules = [DataModule::class, AppModule::class])
 *
 * DIPANGGIL / DIBACA SAAT:
 * - Compile time: Dagger generate kode wiring
 * - Runtime: saat DaggerAppComponent.create() di DonationApplication
 *
 * @Binds:
 * Memberi tahu Dagger: "kalau ada yang minta DonationDataSource,
 * berikan instance DummyDonationDataSource".
 * Lebih efisien dari @Provides untuk mapping interface → impl.
 *
 * Module harus abstract class kalau memakai @Binds.
 */
@Module
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindDonationDataSource(
        impl: DummyDonationDataSource
    ): DonationDataSource
}

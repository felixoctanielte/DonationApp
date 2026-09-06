package com.example.donationapp.di

import com.example.donationapp.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Component = "wadah" / graph dependency Dagger.
 *
 * DIGUNAKAN OLEH:
 * - DonationApplication: DaggerAppComponent.create() lalu disimpan di appComponent
 * - MainActivity: application.appComponent.inject(this)
 *
 * SETELAH BUILD, Dagger GENERATE class:
 * - DaggerAppComponent (di folder build/generated)
 *   Itulah yang dipanggil: DaggerAppComponent.create()
 *
 * modules = [...]
 * Daftar Module yang mengajarkan cara membuat object tertentu.
 *
 * fun inject(activity: MainActivity)
 * Pintu masuk field injection: mengisi semua @Inject di MainActivity.
 *
 * @Singleton di Component:
 * Scope default untuk dependency yang juga di-annotate @Singleton.
 */
@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent {

    /**
     * Dipanggil dari MainActivity.onCreate():
     *   (application as DonationApplication).appComponent.inject(this)
     *
     * Setelah ini, field @Inject di MainActivity terisi.
     */
    fun inject(activity: MainActivity)
}

package com.example.donationapp

import android.app.Application
import com.example.donationapp.di.AppComponent
import com.example.donationapp.di.DaggerAppComponent

/**
 * Application custom = tempat membuat AppComponent SEKALI seumur app.
 *
 * DIGUNAKAN OLEH:
 * - AndroidManifest: android:name=".DonationApplication"
 * - MainActivity: (application as DonationApplication).appComponent.inject(this)
 *
 * Kenapa di Application?
 * Component @Singleton harus hidup selama app hidup, bukan per Activity.
 *
 * Catatan:
 * DaggerAppComponent baru ada SETELAH project di-build (KSP generate).
 * Kalau IDE merah sebelum sync/build, itu normal.
 */
class DonationApplication : Application() {

    /**
     * Graph Dagger untuk seluruh app.
     * Diisi di onCreate, lalu dibaca MainActivity untuk inject.
     */
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        // create() → Dagger merakit Module + @Inject graph
        appComponent = DaggerAppComponent.create()
    }
}

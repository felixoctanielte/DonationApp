package com.example.donationapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.donationapp.di.DonationViewModelFactory
import com.example.donationapp.navigation.DonationNavHost
import com.example.donationapp.ui.theme.DonationAppTheme
import javax.inject.Inject
import javax.inject.Named

/**
 * Entry Activity.
 *
 * ALUR DAGGER DI SINI:
 * 1. DonationApplication sudah buat AppComponent di onCreate Application
 * 2. Kita panggil appComponent.inject(this) → field @Inject di bawah terisi
 * 3. viewModelFactory dikirim ke DonationNavHost → Screen → viewModel(factory = ...)
 */
class MainActivity : ComponentActivity() {

    /**
     * Field injection (bukan constructor), karena Android framework yang `new` Activity.
     *
     * DIISI OLEH: AppComponent.inject(this) di onCreate
     * DIPAKAI DI: setContent → DonationNavHost(viewModelFactory = viewModelFactory)
     */
    @Inject
    lateinit var viewModelFactory: DonationViewModelFactory

    /**
     * Contoh dependency dari AppModule.@Provides + @Named.
     * Hanya untuk belajar; ditampilkan di Logcat.
     */
    @Inject
    @Named("app_display_name")
    lateinit var appDisplayName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        // WAJIB sebelum pakai field @Inject di bawah
        (application as DonationApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)

        Log.d("DaggerDemo", "App name from Dagger @Provides: $appDisplayName")

        enableEdgeToEdge()
        setContent {
            DonationAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Factory diteruskan ke navigation/screens
                    DonationNavHost(viewModelFactory = viewModelFactory)
                }
            }
        }
    }
}

package com.example.donationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.donationapp.navigation.DonationNavHost
import com.example.donationapp.ui.theme.DonationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DonationAppTheme {
                Surface(modifier = Modifier.fillMaxSize().safeDrawingPadding()) {
                    DonationNavHost()
                }
            }
        }
    }
}

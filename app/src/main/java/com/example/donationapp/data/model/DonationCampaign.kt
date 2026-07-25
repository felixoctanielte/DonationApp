package com.example.donationapp.data.model

data class  DonationCampaign(
    val id: Int,
    val title: String,
    val description: String,
    val collectedAmount: Long,
    val targetAmount: Long,
    val category: String
)

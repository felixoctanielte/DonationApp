package com.example.donationapp.navigation

//import android.net.wifi.hotspot2.pps.HomeSp

object Routes {
    const val  REGISTER = "register"
    const val  LOGIN = "login"
    const val  HOME = "home"
    const val  DONATION = "donation"
    const val  NEWS = "news"

    const val  NEW_DETAIL = "new_detail/{newsId}"

    fun newDetail(newsId: Int): String{
        return  "news_detail/$newsId"
    }
}
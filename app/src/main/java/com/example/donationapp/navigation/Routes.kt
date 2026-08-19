package com.example.donationapp.navigation

object Routes {
    const val REGISTER = "register"
    const val LOGIN = "login"
    const val MAIN = "main"
    const val HOME = "home"
    const val DONATION = "donation"
    const val NEWS = "news"
    const val NEWS_DETAIL = "news_detail/{newsId}"

    fun newsDetail(newsId: Int): String {
        return "news_detail/$newsId"
    }
}

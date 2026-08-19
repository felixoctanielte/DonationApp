package com.example.donationapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.donationapp.ui.theme.screens.auth.LoginScreen
import com.example.donationapp.ui.theme.screens.auth.RegisterScreen
import com.example.donationapp.ui.theme.screens.donation.DonationScreen
import com.example.donationapp.ui.theme.screens.home.HomeScreen
import com.example.donationapp.ui.theme.screens.news.NewsDetailScreen
import com.example.donationapp.ui.theme.screens.news.NewsScreen

private data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

@Composable
fun DonationNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.REGISTER
    ) {
        composable(Routes.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Routes.MAIN) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.MAIN) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.MAIN) {
            MainScreen(
                onNewsClick = { newsId ->
                    navController.navigate(Routes.newsDetail(newsId))
                }
            )
        }

        composable(
            route = Routes.NEWS_DETAIL,
            arguments = listOf(navArgument("newsId") { type = NavType.IntType })
        ) { backStackEntry ->
            val newsId = backStackEntry.arguments?.getInt("newsId") ?: 0
            NewsDetailScreen(newsId = newsId)
        }
    }
}

@Composable
private fun MainScreen(
    onNewsClick: (Int) -> Unit
) {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem(Routes.HOME, "Beranda", Icons.Default.Home),
        BottomNavItem(Routes.DONATION, "Donasi", Icons.Default.Favorite),
        BottomNavItem(Routes.NEWS, "Berita", Icons.Default.Newspaper)
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME) {
                HomeScreen(
                    onCampaignClick = {
                        navController.navigate(Routes.DONATION)
                    },
                    onNewsClick = onNewsClick
                )
            }
            composable(Routes.DONATION) {
                DonationScreen()
            }
            composable(Routes.NEWS) {
                NewsScreen(onNewsClick = onNewsClick)
            }
        }
    }
}

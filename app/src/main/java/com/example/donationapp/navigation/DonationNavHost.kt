package com.example.donationapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
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
import com.example.donationapp.ui.theme.screens.landing.LandingScreen
import com.example.donationapp.ui.theme.screens.news.NewsDetailScreen
import com.example.donationapp.ui.theme.screens.news.NewsScreen

private data class BottomNavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

/**
 * Nav host utama.
 *
 * viewModelFactory berasal dari MainActivity (hasil Dagger inject),
 * lalu diteruskan ke screen yang butuh ViewModel.
 */
@Composable
fun DonationNavHost(
    viewModelFactory: ViewModelProvider.Factory,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.LANDING
    ) {
        composable(Routes.LANDING) {
            LandingScreen(
                onGetStartedClick = {
                    navController.navigate(Routes.REGISTER)
                },
                onLoginClick = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Routes.MAIN) {
                        popUpTo(Routes.LANDING) { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.LANDING)
                    }
                }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.MAIN) {
                        popUpTo(Routes.LANDING) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Routes.REGISTER) {
                        popUpTo(Routes.LANDING)
                    }
                }
            )
        }

        composable(Routes.MAIN) {
            MainScreen(
                viewModelFactory = viewModelFactory,
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
            NewsDetailScreen(
                newsId = newsId,
                viewModelFactory = viewModelFactory
            )
        }
    }
}

@Composable
private fun MainScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onNewsClick: (Int) -> Unit
) {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem(Routes.HOME, "Beranda", Icons.Filled.Home, Icons.Outlined.Home),
        BottomNavItem(Routes.DONATION, "Donasi", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder),
        BottomNavItem(Routes.NEWS, "Berita", Icons.Filled.Newspaper, Icons.Outlined.Newspaper)
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 3.dp
            ) {
                items.forEach { item ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == item.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.label
                            )
                        },
                        label = { Text(item.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
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
                    viewModelFactory = viewModelFactory,
                    onCampaignClick = {
                        navController.navigate(Routes.DONATION)
                    },
                    onNewsClick = onNewsClick
                )
            }
            composable(Routes.DONATION) {
                DonationScreen(viewModelFactory = viewModelFactory)
            }
            composable(Routes.NEWS) {
                NewsScreen(
                    viewModelFactory = viewModelFactory,
                    onNewsClick = onNewsClick
                )
            }
        }
    }
}

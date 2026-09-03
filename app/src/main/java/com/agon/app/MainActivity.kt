package com.agon.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.GppGood
import androidx.compose.material.icons.filled.HowToVote
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.agon.app.ui.screens.GovernanceScreen
import com.agon.app.ui.screens.GotongRoyongScreen
import com.agon.app.ui.screens.HomeScreen
import com.agon.app.ui.screens.MusyawarahScreen
import com.agon.app.ui.screens.SettingsScreen
import com.agon.app.ui.theme.AgonAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AgonAppTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MusgoBottomNav(navController) },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding),
        ) {
            composable("home") { HomeScreen() }
            composable("musyawarah") { MusyawarahScreen() }
            composable("gotong_royong") { GotongRoyongScreen() }
            composable("governance") { GovernanceScreen() }
            composable("settings") { SettingsScreen() }
        }
    }
}

@Composable
fun MusgoBottomNav(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navItems = listOf(
        NavItem("home", "Beranda", Icons.Default.Home),
        NavItem("musyawarah", "Musyawarah", Icons.Default.HowToVote),
        NavItem("gotong_royong", "GotongRoyong", Icons.Default.GppGood),
        NavItem("governance", "Governance", Icons.Default.Policy),
        NavItem("settings", "Pengaturan", Icons.Default.Settings),
    )

    NavigationBar {
        navItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp),
                    )
                },
                label = {
                    Text(
                        text = item.label,
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo("home") { inclusive = item.route == "home" }
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}

private data class NavItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
)

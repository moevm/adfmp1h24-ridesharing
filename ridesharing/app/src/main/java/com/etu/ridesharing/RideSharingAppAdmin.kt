package com.etu.ridesharing

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.etu.ridesharing.ui.screens.AdminPageCars
import com.etu.ridesharing.ui.screens.AdminPageStats
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.rememberNavController

data class BottomNavItem(
    val label: String,
    val icon: Int,
    val route: String,
)

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Statistics",
            icon = R.drawable.baseline_analytics_24,
            route = "stats"
        ),
        BottomNavItem(
            label = "Cars",
            icon = R.drawable.baseline_directions_car_24,
            route = "cars"
        )
    )
}

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,

        // set the start destination as stats
        startDestination = "stats",

        // Set the padding provided by scaffold
        modifier = Modifier.padding(paddingValues = padding),

        builder = {
            // route : stats
            composable("stats") {
                AdminPageStats()
            }
            // route : cars
            composable("cars") {
                AdminPageCars()
            }
        })
}

@SuppressLint("ResourceAsColor")
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        // set background color
        containerColor = Color(R.color.purple_200)
    ) {

        // observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        // Bottom nav items we declared
        Constants.BottomNavItems.forEach { navItem ->

            // Place the bottom nav items
            NavigationBarItem(

                // it currentRoute is equal then its selected route
                selected = currentRoute == navItem.route,

                // navigate on click
                onClick = {
                    navController.navigate(navItem.route)
                },

                // Icon of navItem
                icon = {
                    Icon(imageVector = ImageVector.vectorResource(navItem.icon), contentDescription = navItem.label)
                },

                // label
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false
            )
        }
    }
}

@Composable
fun RideSharingAppAdmin(){
    val navController = rememberNavController()

        // Scaffold Component
    Scaffold(
        // Bottom navigation
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }, content = { padding ->
            NavHostContainer(navController = navController, padding = padding)
        }
    )
}
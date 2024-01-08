package com.example.toeicfantasy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.People
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
){
    data object Mission : BottomNavItem(
            name = "Mission",
            route = "mission",
            badgeCount = 19,
            icon = Icons.Default.MenuBook
        )
    data object Duel : BottomNavItem(
        name = "Duel",
        route = "duel",
        icon = Icons.Default.AccountBalance
    )
    data object Friend : BottomNavItem(
        name = "Friend",
        route = "friend",
        icon = Icons.Default.People
    )
    data object Profile : BottomNavItem(
        name = "Profile",
        route = "profile",
        icon = Icons.Default.AccountBox
    )
}
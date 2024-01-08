package com.example.toeicfantasy.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.toeicfantasy.BottomNavItem

@Composable
fun ToeicFantasyApp(){
    ToeicFantasyScaffold()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToeicFantasyScaffold() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(items = listOf(
                BottomNavItem.Mission,
                BottomNavItem.Duel,
                BottomNavItem.Friend,
                BottomNavItem.Profile
                ),
                navController = navController ,
                onItemClick = {
                    navController.navigate(it.route)
                })
        }
    ){padding ->
        ToeicFantasyNavHost(navController = navController, modifier = Modifier.padding(padding))
    }
}
@Composable
fun ToeicFantasyNavHost(
    navController: NavHostController,
    startDestination: String = "mission",
    modifier: Modifier
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        composable("mission") {
            MissionScreen()
        }
        composable("duel") {
            DuelScreen()
        }
        composable("friend") {
            FriendScreen()
        }
        composable("profile") {
            ProfileScreen()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
        modifier = modifier,
        tonalElevation = 4.dp,
        containerColor = Color.Transparent,
    ) {
        items.forEach { item ->
            // 뷰의 활동 상태를 백스택에 담아 저장
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        // 뱃지 카운트가 1이상이면, 아이콘에 뱃지카운트 표시
                        if (item.badgeCount > 0){
                            BadgedBox(
                                badge = { Badge{ Text(item.badgeCount.toString()) } }
                            ){
                                Icon(
                                    item.icon,
                                    contentDescription = item.name
                                )
                            }
                        }
                        else
                            Icon(
                                item.icon,
                                contentDescription = item.name
                            )


                        /*// 아이콘이 선택될 시, 아이콘 밑에 텍스트 표시
                        if (selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }*/
                    }
                },
                label = { Text(text = item.name) },
                alwaysShowLabel = false
            )
        }
    }
}
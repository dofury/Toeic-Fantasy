package com.example.toeicfantasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.toeicfantasy.data.BottomNavItem
import com.example.toeicfantasy.ui.theme.ToeicFantasyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToeicFantasyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainUI()
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUI() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(items = listOf(
                BottomNavItem(
                    name = "Mission",
                    route = "mission",
                    icon = Icons.Default.MenuBook
                ),
                BottomNavItem(
                    name = "Duel",
                    route = "duel",
                    icon = Icons.Default.AccountBalance
                ),
                BottomNavItem(
                    name = "Friend",
                    route = "friend",
                    icon = Icons.Default.People
                ),
                BottomNavItem(
                    name = "Profile",
                    route = "profile",
                    icon = Icons.Default.AccountBox
                ),

            ),
                navController = navController ,
                onItemClick = {
                    navController.navigate(it.route)
                })
        }
    ){padding ->
        Navigation(navController = navController, modifier = Modifier.padding(padding))

    }
}
@Composable
fun Navigation(
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

@Composable
fun ProfileScreen(){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Profile")
    }
}

@Composable
fun MissionScreen(){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Mission")
    }
}

@Composable
fun DuelScreen(){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Duel")
    }
}
@Composable
fun FriendScreen(){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Friend")
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
        tonalElevation = 5.dp
    ) {
        items.forEach { item ->
            // 뷰의 활동 상태를 백스택에 담아 저장
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                icon = {
                       Column(horizontalAlignment = CenterHorizontally) {
                           // 뱃지 카운트가 1이상이면, 아이콘에 뱃지카운트 표시
                           if (item.badgeCount > 0){
                               BadgedBox(
                                   badge = { Badge{ Text(item.badgeCount.toString())}}
                               ){
                                   Icon(
                                       item.icon,
                                       contentDescription = item.name
                                   )
                               }
                           }
                           else {
                               Icon(
                                   item.icon,
                                   contentDescription = item.name
                               )
                           }

                           // 아이콘이 선택될 시, 아이콘 밑에 텍스트 표시
                           if (selected) {
                               Text(
                                   text = item.name,
                                   textAlign = TextAlign.Center,
                                   fontSize = 10.sp
                               )
                           }
                       }
                },
                label = {Text(text = item.name)},
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToeicFantasyTheme{
    }
}


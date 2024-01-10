package com.example.toeicfantasy

sealed class Screen(
    val name: String,
    val route: String
) {
    data object Test: Screen("Test","test")

}
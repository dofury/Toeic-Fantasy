package com.example.toeicfantasy.data
sealed class Mission(
    val id: String,
    val name: String,
    val point: Int,
    val exp: Int
)
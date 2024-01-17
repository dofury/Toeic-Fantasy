package com.example.toeicfantasy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordCard(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "english_meaning") val englishMeaning: String,
    @ColumnInfo(name = "korean_meaning") val koreanMeaning: String)

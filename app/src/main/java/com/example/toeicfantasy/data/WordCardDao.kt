package com.example.toeicfantasy.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface WordCardDao {

    @Insert
    fun insert(wordCard: WordCard)

    @Update
    fun update(wordCard: WordCard)

    @Delete
    fun delete(wordCard: WordCard)
}
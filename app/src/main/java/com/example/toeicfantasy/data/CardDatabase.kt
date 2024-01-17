package com.example.toeicfantasy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WordCard::class], version = 1)
abstract class CardDatabase : RoomDatabase() {
    abstract fun cardDao(): WordCardDao

    companion object {
        private var instance: CardDatabase? = null

        @Synchronized
        fun getInstance(context: Context): CardDatabase? {
            if(instance == null){
                synchronized(CardDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CardDatabase::class.java,
                        "card-database"
                    ).build()
                }
            }
            return instance
        }
    }
}
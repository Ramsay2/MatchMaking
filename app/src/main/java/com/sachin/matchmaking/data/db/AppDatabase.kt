package com.sachin.matchmaking.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MatchProfileDbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun matchProfileDao(): MatchProfileDao
}
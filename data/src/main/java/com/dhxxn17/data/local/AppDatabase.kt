package com.dhxxn17.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dhxxn17.data.model.LikeData

@Database(entities = [LikeData::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun likeDao(): LikeDao

    companion object {
        const val DB_NAME = "Database_Dogterest"
    }
}
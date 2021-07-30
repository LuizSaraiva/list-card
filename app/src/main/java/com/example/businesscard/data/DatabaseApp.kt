package com.example.businesscard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.businesscard.model.BusinessCard

@Database(entities = [BusinessCard::class], version = 1)
abstract class DatabaseApp : RoomDatabase() {

    abstract fun businessDao(): DaoBusinessCard

    companion object {

        private var DB_NAME = "businesscard_db"

        @Volatile
        private var INSTANCE: DatabaseApp? = null

        fun getDatabase(context: Context): DatabaseApp {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseApp::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}
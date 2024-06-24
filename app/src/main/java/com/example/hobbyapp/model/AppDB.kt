package com.example.hobbyapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hobbyapp.model.dao.NewsDao
import com.example.hobbyapp.model.dao.UserDao

@Database(entities = [User::class, News::class], version = 2)
abstract class AppDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun newsDao(): NewsDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE User ADD COLUMN tokenSession VARCHAR(250)"
                )
            }
        }

        @Volatile
        private var instance: AppDB? = null
        private val LOCK = Any()

        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDB::class.java, "app.db"
        )
            .addMigrations(MIGRATION_1_2)
            .build()

        operator fun invoke(context: Context): AppDB {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }
    }
}
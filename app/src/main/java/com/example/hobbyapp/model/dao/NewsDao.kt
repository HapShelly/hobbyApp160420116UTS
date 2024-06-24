package com.example.hobbyapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hobbyapp.model.News
import com.example.hobbyapp.model.User

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): List<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: News)
}
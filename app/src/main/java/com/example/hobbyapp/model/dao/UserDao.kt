package com.example.hobbyapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hobbyapp.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM `user` WHERE username = :username AND password = :password")
    fun loginUser(username:String,password:String): User?

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUserById(userId:Int):User
}
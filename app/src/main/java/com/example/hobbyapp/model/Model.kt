package com.example.hobbyapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class News(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    @SerializedName("image")
    var imgUrl: String,
    var preview: String?,
    var content: String?,
    var author: String,
    var createdAt: String?,
) : Parcelable

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var username: String,
    var password: String?,
    var firstname: String,
    var lastname: String,
    var tokenSession: String? = "",
)
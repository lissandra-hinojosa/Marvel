package com.example.apirequest.database.entities

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val email: String,
    var name: String,
    @ColumnInfo(name = "last_name") var lastName: String,
    val password: String,
    @ColumnInfo(name = "register_date") val registerDate: String,
    @ColumnInfo(name = "last_login_date") var lastLoginDate: String? = null,
    @ColumnInfo(name = "profile_image") var profileImage: String? = null

): Serializable